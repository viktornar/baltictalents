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
package lt.viktornar.currdiff.controller;

import lombok.Getter;
import lombok.Setter;
import lt.viktornar.currdiff.model.Item;
import lt.viktornar.currdiff.service.CurrencyRateService;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Used as controller to connect jsf view with service layer.
 *
 * @author v.nareiko
 */
@Component(value="indexController")
@Scope("session")
public class IndexController implements Serializable {
    private static final long serialVersionUID = 1L;

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Getter
    @Setter
    private Date date;

    private Date maxDate;

    @Setter
    @Getter
    private boolean dataExist;

    @Getter
    @Setter
    private List<Item> items;

    @Autowired
    private CurrencyRateService currencyRateService;

    @Autowired
    private SimpleDateFormat customSimpleDateFormat;

    /**
     * Updates date
     *
     * @param event prime faces component event.
     */
    public void onDateSelect(SelectEvent event) {
        logger.info(String.format("Date selected: %s", customSimpleDateFormat.format(event.getObject())));
        date = (Date)event.getObject();
    }

    /**
     * Restrict max date select in calendar component.
     *
     * @return 2014-12-31 date object.
     */
    public Date getMaxDate(){
        return new GregorianCalendar(2014, Calendar.DECEMBER, 31).getTime();
    }

    /**
     * Fill data table component with data.
     *
     */
    public void populateTable() {
        logger.info(String.format("Date to process: %s", customSimpleDateFormat.format(date)));
        items = currencyRateService.getChangesOfRatesByDate(date);
        // Show data table component only if data exists.
        if (items.size() > 0) {
            dataExist = true;
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Error: Error fetching data from remote service",
                            null
                    )
            );
        }
    }

    /**
     * Reset all view values.
     *
     */
    public void resetValues() {
        logger.info("Resetting all values");
        items.clear();
        dataExist = false;
        date = null;
    }
}
