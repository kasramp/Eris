package com.madadipouya.eris.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static com.madadipouya.eris.util.UnitConversionUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
* This file is part of Eris Weather API.
*
* Eris Weather API is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License version 3
* as published by the Free Software Foundation.
*
* Eris Weather API is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.  <http://www.gnu.org/licenses/>
*
* Author(s):
*
* © 2017-2022 Kasra Madadipouya <kasra@madadipouya.com>
*/

class UnitConversionUtilsTest {

    @Test
    void testUnitConversionUtilsHasPrivateConstructor() throws NoSuchMethodException {
        Constructor<UnitConversionUtils> constructor = UnitConversionUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    void testFahrenheitToCelsius() {
        assertEquals(40, fahrenheitToCelsius(104), 0.0001);
    }

    @Test
    void testCelsiusToFahrenheit() {
        assertEquals(104, celsiusToFahrenheit(40), 0.0001);
    }

    @Test
    void testKiloMeterPerHourToMilePerHour() {
        assertEquals(62.1371, kiloMeterPerHourToMilePerHour(100), 0.0001);
    }

    @Test
    void testMeterToMile() {
        assertEquals(0.0621371, meterToMile(100), 0.0000001);
    }

    @Test
    void testMeterPerSecondToKiloMeterPerHour() {
        assertEquals(360, meterPerSecondToKiloMeterPerHour(100), 0.0001);
    }

    @Test
    void testMeterToKiloMeter() {
        assertEquals(1, meterToKiloMeter(1000), 0.0001);
    }
}
