package com.madadipouya.eris.service.feelslike.impl;

import com.madadipouya.eris.service.feelslike.FeelsLikeService;
import org.springframework.stereotype.Service;

import static com.madadipouya.eris.util.UnitConversionUtils.CelsiusToFahrenheit;
import static com.madadipouya.eris.util.UnitConversionUtils.FahrenheitToCelsius;
import static com.madadipouya.eris.util.UnitConversionUtils.MeterPerSecondToMilePerHour;

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

@Service("feelsLikeService")
public class DefaultFeelsLikeService implements FeelsLikeService {

    private static final double WIND_CHILL = 50;

    private static final double HEAT_INDEX = 57.2;

    @Override
    public double getFeelsLike(double temperature, double windSpeed, double humidity, boolean fahrenheit) {
        return fahrenheit ? getFeelsLikeFahrenheit(temperature, windSpeed, humidity)
                : getFeelsLikeCelsius(temperature, windSpeed, humidity);
    }

    private double getFeelsLikeCelsius(double temperature, double windSpeed, double humidity) {
        return FahrenheitToCelsius(getFeelsLikeFahrenheit(CelsiusToFahrenheit(temperature),
                MeterPerSecondToMilePerHour(windSpeed), humidity));
    }

    private double getFeelsLikeFahrenheit(double temperature, double windSpeed, double humidity) {
        if(isWindChill(temperature)) {
            return getWindChill(temperature, windSpeed);
        } else if(isHeatIndex(temperature)) {
            return getHeatIndex(temperature, humidity);
        } else {
            return getLinearRollOff(temperature, getWindChill(temperature, windSpeed));
        }
    }

    private boolean isWindChill(double temperature) {
        return temperature <= WIND_CHILL;
    }

    private boolean isHeatIndex(double temperature) {
        return temperature >= HEAT_INDEX;
    }

    private double getWindChill(double temperature, double windspeed) {
        return 35.74 + 0.6215 * temperature - 35.75 * Math.pow(windspeed, 0.16)
                + 0.4275 * temperature * Math.pow(windspeed, 0.16);
    }

    private double getHeatIndex(double temperature, double humidity) {
        double heatIndex = -42.379 + 2.04901523 * temperature + 10.14333127 * humidity - .22475541 *
                temperature * humidity - 0.00683783 * Math.pow(temperature, 2) - 0.05481717 *
                Math.pow(humidity, 2) + 0.00122874 * Math.pow(temperature, 2) * humidity +
                0.00085282 * temperature * Math.pow(humidity, 2) - 0.00000199 * Math.pow(temperature, 2) *
                Math.pow(humidity, 2);
        return heatIndex > temperature ? heatIndex : temperature;
    }

    private double getLinearRollOff(double temperature, double windChill) {
        return temperature - (temperature - windChill) * (57.2 - temperature) / 7.2;
    }
}
