package com.madadipouya.eris.service.weather;

import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import jakarta.servlet.http.HttpServletRequest;

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
 * © 2017-2023 Kasra Madadipouya <kasra@madadipouya.com>
 */

/**
 * This service is responsible to aggregates the results of multiple integration services:
 * <ul>
 *     <li>{@link com.madadipouya.eris.integration.groupkt.GroupktCountryNameIntegration}</li>
 *     <li>{@link com.madadipouya.eris.integration.ipapi.IpApiIntegration}</li>
 *     <li>{@link com.madadipouya.eris.integration.openweathermap.OpenWeatherMapIntegration}</li>
 *     <li>{@link com.madadipouya.eris.integration.openstreetmap.OpenStreetMapIntegration}</li>
 *     <li>{@link com.madadipouya.eris.service.feelslike.FeelsLikeService}</li>
 * </ul>
 * Then returns the data to the end user.
 */
public interface Weather {

    /**
     * Gets the current weather condition based on {@link HttpServletRequest}
     *
     * @param request    Http request that want to get the weather condition of
     * @param fahrenheit Unit of measurement of weather. {@code true} means Imperial, {@code false} means Metric
     * @return {@link CurrentWeatherCondition} The current weather condition data
     */
    CurrentWeatherCondition getCurrent(HttpServletRequest request, boolean fahrenheit);

    /**
     * Gets the current weather condition based on geographical points/coordinates
     *
     * @param latitude   Latitude of a geographical point that want to retrieve its weather condition
     * @param longitude  Longitude of a geographical point that want to retrieve its weather condition
     * @param fahrenheit Unit of measurement of weather. {@code true} means Imperial, {@code false} means Metric
     * @return {@link CurrentWeatherCondition} The current weather condition data
     */
    CurrentWeatherCondition getCurrent(String latitude, String longitude, boolean fahrenheit);
}
