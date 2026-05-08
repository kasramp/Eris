package com.madadipouya.eris.service.mcp;

import com.madadipouya.eris.service.mcp.exception.InvalidArgumentException;
import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;

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
 * © 2026 Kasra Madadipouya <kasra@madadipouya.com>
 */

/**
 * This service is responsible to expose MCP tools for weather information retrieval.
 * It's built as a thin layer on top of {@link com.madadipouya.eris.service.weather.Weather}
 * to facilitate MCP integration and yet at the same time keep the business logic separate.
 *
 */
public interface McpTools {


    /**
     * Get current weather condition by latitude and longitude. It does a basic validation
     * and then proxy the call to {@link com.madadipouya.eris.service.weather.Weather}
     *
     * @param latitude            Latitude of a geographical point that want to retrieve its weather condition
     * @param longitude           Longitude of a geographical point that want to retrieve its weather condition
     * @param displayInFahrenheit Unit of measurement of weather. {@code true} means Imperial, {@code false} means Metric.
     *                            May be {@code null}, in which case Metric is used.
     * @return {@link CurrentWeatherCondition} The current weather condition data
     * @throws InvalidArgumentException When latitude or longitude is invalid or out of range
     */
    CurrentWeatherCondition getWeatherByLatitudeAndLongitude(String latitude, String longitude, Boolean displayInFahrenheit) throws InvalidArgumentException;

    /**
     * Get current weather condition by IP address. It does a basic validation
     * and then proxy the call to {@link com.madadipouya.eris.service.weather.Weather}
     *
     * @param ipAddress           IP address of a geographical point that want to retrieve its weather condition
     * @param displayInFahrenheit Unit of measurement of weather. {@code true} means Imperial, {@code false} means Metric.
     *                            May be {@code null}, in which case Metric is used.
     * @return {@link CurrentWeatherCondition} The current weather condition data
     */
    CurrentWeatherCondition getWeatherByIpAddress(String ipAddress, Boolean displayInFahrenheit) throws InvalidArgumentException;
}
