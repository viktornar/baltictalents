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
package lt.viktornar.currdiff.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Used as POJO for data exchange between remote service and prime faces components.
 *
 * @author v.nareiko
 */
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    public Item() {
    }

    @Getter
    @Setter
    private String date;

    @Getter
    @Setter
    private String currency;

    @Getter
    @Setter
    private Integer quantity;

    @Getter
    @Setter
    private Float rate;

    @Getter
    @Setter
    private String unit;

    // TODO: Change Float object to double primitive type
    @Getter
    @Setter
    private Float rateChange;

    // TODO: Change Float object to double primitive type
    @Getter
    @Setter
    private Float rateChangeInPercentage;
}
