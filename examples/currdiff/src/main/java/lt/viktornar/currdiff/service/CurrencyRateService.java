/*
 This file is part of CurrDiff.
 CurrDiff is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 Subsonic is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 You should have received a copy of the GNU General Public License
 along with Subsonic.  If not, see <http://www.gnu.org/licenses/>.
 Copyright 2016 (C) Viktor Nareiko
 */
package lt.viktornar.currdiff.service;

import lt.viktornar.currdiff.comparator.CurrencyComparator;
import lt.viktornar.currdiff.comparator.RateChangeInPercentageComparator;
import lt.viktornar.currdiff.model.ExchangeRates;
import lt.viktornar.currdiff.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Used as service layer for:
 * 1. Getting data from service;
 * 2. Calculating currency rate change by given date;
 *
 * @author v.nareiko
 */
@Service("currencyRateService")
public class CurrencyRateService {
    @Autowired
    private SettingsService settingsService;

    Logger logger = LoggerFactory.getLogger(CurrencyRateService.class);

    @Autowired
    private SimpleDateFormat customSimpleDateFormat;

    /**
     * Returns currency rate from remote service by given date.
     *
     * @param date The date used in remote service query.
     * @return List of items fetched from remote service.
     */
    public List<Item> getRatesByDate(Date date) {
        List<Item> items = new ArrayList<>();
        String dateParameter = customSimpleDateFormat.format(date);

        logger.info(String.format("Trying to get rates by given date [%s]", dateParameter));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("Date", dateParameter);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ExchangeRates result = restTemplate.postForObject(settingsService.getServiceUrl(), map, ExchangeRates.class);
            items.addAll(result.getItems());
            logger.info(String.format("Got rates by given date [%s]", dateParameter));
        } catch (RestClientException | HttpMessageNotReadableException e) {
            logger.error(String.format("Could not fetch data from remote service: %s", settingsService.getServiceUrl()));
            e.printStackTrace();
        }

        return items;
    }

    /**
     * Returns list of currency rate with filled change property.
     *
     * @param date The date used in remote service query.
     * @return List of items that contains change of rate.
     */
    public List<Item> getChangesOfRatesByDate(Date date) {
        Date prevDayDate = subtractDaysFromDate(date, 1);

        final List<Item> itemsOfGivenDate = getRatesByDate(date);
        final List<Item> itemsOfPrevDate = getRatesByDate(prevDayDate);
        List<Item> diffItems = new ArrayList<>();

        if (!itemsOfGivenDate.isEmpty() && !itemsOfPrevDate.isEmpty()) {
            // Sort by currency just in case if service return items not in the same order
            Collections.sort(itemsOfGivenDate, new CurrencyComparator());
            Collections.sort(itemsOfPrevDate, new CurrencyComparator());

            // Some currency during time are abolished.
            // For example in 1998-12-31 BEF currency existed but in 1999-01-01 doesn't.
            // So in some cases itemsOfGivenDate.size() != itemsOfPrevDate.size()

            for (int i = 0; i < itemsOfGivenDate.size(); i++) {

                final Item givenDateItem = itemsOfGivenDate.get(i);
                Item itemToReturn = null;

                for (int j = 0; j < itemsOfPrevDate.size(); j++) {
                    final Item prevDateItem = itemsOfPrevDate.get(j);

                    if (givenDateItem.getCurrency().equals(prevDateItem.getCurrency())) {
                        itemToReturn = new Item();
                        BeanUtils.copyProperties(givenDateItem, itemToReturn);
                        Float diffRate = givenDateItem.getRate() - prevDateItem.getRate();
                        // The main algorithm for change of rate calculation in percentage (from school :))
                        float percentage = (diffRate / prevDateItem.getRate()) * 100f;
                        itemToReturn.setRateChangeInPercentage(roundRateChange(percentage));
                        itemToReturn.setRateChange(roundRateChange(diffRate));
                    }
                }

                if (itemToReturn != null) {
                    diffItems.add(itemToReturn);
                }
            }

            // The list of changes has to be ordered, biggest Exchange rate increase first
            Collections.sort(diffItems, new RateChangeInPercentageComparator());
        }

        return diffItems;
    }

    // Helper methods

    /**
     * Helper method to subtract days from given date.
     *
     * @param date The date.
     * @param days The days to subtract from given date.
     * @return List of items fetched from remote service.
     */
    // TODO: It would be great to place this peace of code to separate utils class.
    public Date subtractDaysFromDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if (days > 0) {
            days = -days;
        }

        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * Helper method to subtract days from given date.
     *
     * @param rateChange The value to be rounded.
     * @return Rounded value.
     */
    // TODO: It would be great to place this peace of code to separate utils class.
    // TODO: Improve this method (make it more generic and add possibility to round value to the given decimal place)
    private Float roundRateChange(Float rateChange) {
        rateChange = rateChange * 10000.0f;
        rateChange = (float) Math.round(rateChange);
        rateChange = rateChange / 10000.0f;
        return rateChange;
    }
}
