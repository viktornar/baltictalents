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

import java.io.Serializable;
import java.util.Comparator;

/**
 * Sort Item collection by currency in ascending order.
 *
 * @author v.nareiko
 */
public class CurrencyComparator implements Comparator<Item>, Serializable {
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getCurrency().compareTo(o2.getCurrency());
    }
}
