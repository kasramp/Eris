package com.madadipouya.eris.integration.openweathermap;

import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

@Service("openWeatherMapIntegration")
public class DefaultOpenWeatherMapIntegration implements OpenWeatherMapIntegration {

    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?appid=%s"
            +"&units=%s&lat=%s&lon=%s";

    private static final String API_KEY = "c15e2598880e57fad011a64061948fac";

    private static final String TEMPERATURE_UNIT_METRIC = "metric";

    private static final String TEMPERATURE_UNIT_IMPERIAL = "imperial";

    @Override
    public OpenWeatherMapCurrentWeatherResponse getCurrentWeatherCondition(String latitude, String longitude, boolean fahrenheit) {
        return adjustCoordinates(new RestTemplate().getForObject(
                String.format(API_URL, API_KEY, getTemperatureUnit(fahrenheit), latitude, longitude),
                OpenWeatherMapCurrentWeatherResponse.class), latitude, longitude);
    }

    private String getTemperatureUnit(boolean fahrenheit) {
        return fahrenheit ? TEMPERATURE_UNIT_IMPERIAL : TEMPERATURE_UNIT_METRIC;
    }

    private OpenWeatherMapCurrentWeatherResponse adjustCoordinates(OpenWeatherMapCurrentWeatherResponse openWeatherMapCurrentWeatherResponse, String latitude, String longitude) {
        openWeatherMapCurrentWeatherResponse.getCoordinates().setLatitude(latitude);
        openWeatherMapCurrentWeatherResponse.getCoordinates().setLongitude(longitude);
        return openWeatherMapCurrentWeatherResponse;
    }
}
