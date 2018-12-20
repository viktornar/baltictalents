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

import lt.viktornar.currdiff.config.AppConfig;
import lt.viktornar.currdiff.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author v.nareiko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class CurrencyRateServiceTest {
    @Autowired
    private ApplicationContext applicationContext;

    private CurrencyRateService currencyRateService;

    @Before
    public void setUp() throws ParseException {
        currencyRateService = (CurrencyRateService) applicationContext.getBean("currencyRateService");
    }

    @Test
    public void getRatesByDate() throws Exception {
        Date date = new GregorianCalendar(2014, Calendar.SEPTEMBER, 15).getTime();
        List<Item> items = currencyRateService.getRatesByDate(date);

        Item item = items.get(0);

        Assert.assertTrue(item.getRate() > 0);
        Assert.assertFalse(item.getCurrency().isEmpty());
        Assert.assertTrue(item.getQuantity() > 0);
        Assert.assertFalse(item.getUnit().isEmpty());

        // Convert date as string to java Date object
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        Date result = df.parse(item.getDate());

        Assert.assertEquals(date.getTime(), result.getTime());
    }

    @Test
    public void subtractDaysFromDate() throws Exception {
        Date date = new GregorianCalendar(2014, Calendar.SEPTEMBER, 15).getTime();
        Date theDayBefore = new GregorianCalendar(2014, Calendar.SEPTEMBER, 14).getTime();

        Date subtractedDate = currencyRateService.subtractDaysFromDate(date, 1);

        Assert.assertEquals(theDayBefore.getTime(), subtractedDate.getTime());
    }

    @Test
    public void getChangesOfRatesByDate() throws Exception {
        Date date = new GregorianCalendar(2014, Calendar.SEPTEMBER, 15).getTime();
        List<Item> items = currencyRateService.getChangesOfRatesByDate(date);

        for (Item item : items) {
            if (item.getCurrency().contains("AED")) {
                Float val = item.getRateChange();
                System.out.println(val);
                // 0.0111 value calculated by hand for given date
                Assert.assertTrue(val == 0.0111f);
            }
        }
    }
}