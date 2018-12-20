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
package lt.viktornar.currdiff.comparator;

import lt.viktornar.currdiff.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author v.nareiko
 */
public class CompareTest {
    List<Item> items = new ArrayList<>();

    @Before
    public void setUp() throws ParseException {
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();

        item1.setCurrency("B");
        item1.setRateChangeInPercentage(2f);
        item1.setRate(2f);
        item2.setCurrency("A");
        item2.setRateChangeInPercentage(1f);
        item2.setRate(1f);
        item3.setCurrency("C");
        item3.setRateChangeInPercentage(3f);
        item3.setRate(3f);

        items = Arrays.asList(item1, item2, item3);
    }

    @Test
    public void sortCurrency() {
        Collections.sort(items, new CurrencyComparator());

        Assert.assertEquals("A", items.get(0).getCurrency());
        Assert.assertEquals("B", items.get(1).getCurrency());
        Assert.assertEquals("C", items.get(2).getCurrency());
    }

    @Test
    public void sortRateExchangeInPercentage() {
        Collections.sort(items, new RateChangeInPercentageComparator());

        Assert.assertTrue(3f == items.get(0).getRateChangeInPercentage());
        Assert.assertTrue(2f == items.get(1).getRateChangeInPercentage());
        Assert.assertTrue(1f == items.get(2).getRateChangeInPercentage());
    }

    @Test
    public void sortRate() {
        Collections.sort(items, new RateChangeInPercentageComparator());

        Assert.assertTrue(3f == items.get(0).getRateChangeInPercentage());
        Assert.assertTrue(2f == items.get(1).getRateChangeInPercentage());
        Assert.assertTrue(1f == items.get(2).getRateChangeInPercentage());
    }
}
