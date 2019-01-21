package com.madadipouya.eris.integration.openweathermap;

import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;

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
* © 2017-2019 Kasra Madadipouya <kasra@madadipouya.com>
*/

/**
 * This service retrieves the current weather condition of a geographical location
 * */
public interface OpenWeatherMapIntegration {

    /**
     * Gets the current location of a geographical point
     * by calling @see <a href="api.openweathermap.org">OpenWeatherMap</a>
     * @param latitude Latitude of the point
     * @param longitude Longitude of the point
     * @param fahrenheit Unit of measurement of weather. {@code true} means Imperial, {@code false} means Metric
     * @return {@link OpenWeatherMapCurrentWeatherResponse} The current weather condition details
    * */
    OpenWeatherMapCurrentWeatherResponse getCurrentWeatherCondition(String latitude, String longitude, boolean fahrenheit);
}
