package com.madadipouya.eris.util;

import javax.measure.Measure;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

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
* © 2017 Kasra Madadipouya <kasra@madadipouya.com>
*/

public class UnitConversionUtils {

    public static double FahrenheitToCelsius(double fahrenheit) {
        return Measure.valueOf(fahrenheit, NonSI.FAHRENHEIT).to(SI.CELSIUS).getValue();
    }

    public static double CelsiusToFahrenheit(double celsius) {
        return Measure.valueOf(celsius, SI.CELSIUS).to(NonSI.FAHRENHEIT).getValue();
    }

    public static double KiloMeterPerHourToMilePerHour(double kiloMeterPerHour) {
        return Measure.valueOf(kiloMeterPerHour, NonSI.KILOMETERS_PER_HOUR).to(NonSI.MILES_PER_HOUR).getValue();
    }

    public static double MeterToMile(double meter) {
        return Measure.valueOf(meter, SI.METER).to(NonSI.MILE).getValue();
    }

    public static double MeterPerSecondToKiloMeterPerHour(double meterPerSecond) {
        return Measure.valueOf(meterPerSecond, SI.METERS_PER_SECOND).to(NonSI.KILOMETERS_PER_HOUR).getValue();
    }
}
