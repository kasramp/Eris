package com.madadipouya.eris.service.feelslike;

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

/**
 * This service calculates @see <a href="https://www.accuweather.com/en/weather-news/what-is-accuweather-realfeel/7198202">Feels Like</a>.
* */

@FunctionalInterface
public interface FeelsLikeService {

    /**
     * Gets feels like of the current weather based on temperature, wind speed, humidity
     * @param temperature The weather temperature, 100 F
     * @param windSpeed The wind speed, can be Mph, km/h
     * @param humidity The level of humidity, e.g., 75%
     * @param fahrenheit Unit of measurement of weather. {@code true} means Imperial, {@code false} means Metric
     * @return The feels like value
    * */
    double getFeelsLike(double temperature, double windSpeed, double humidity, boolean fahrenheit);
}
