package com.madadipouya.eris.integration.openweathermap;

import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;
import com.madadipouya.eris.util.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static com.madadipouya.eris.util.UnitConversionUtils.MeterPerSecondToKiloMeterPerHour;
import static com.madadipouya.eris.util.UnitConversionUtils.MeterToMile;

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
            + "&units=%s&lat=%s&lon=%s";

    private static final String TEMPERATURE_UNIT_METRIC = "metric";

    private static final String TEMPERATURE_UNIT_IMPERIAL = "imperial";

    @Autowired
    PropertyUtils propertyUtils;

    @Override
    public OpenWeatherMapCurrentWeatherResponse getCurrentWeatherCondition(String latitude, String longitude, boolean fahrenheit) {
        return adjustResult(new RestTemplate().getForObject(
                String.format(API_URL, propertyUtils.getOpenWeatherMapApiKey(), getTemperatureUnit(fahrenheit), latitude, longitude),
                OpenWeatherMapCurrentWeatherResponse.class), latitude, longitude, fahrenheit);
    }

    private String getTemperatureUnit(boolean fahrenheit) {
        return fahrenheit ? TEMPERATURE_UNIT_IMPERIAL : TEMPERATURE_UNIT_METRIC;
    }

    private OpenWeatherMapCurrentWeatherResponse adjustResult(OpenWeatherMapCurrentWeatherResponse openWeatherMapCurrentWeatherResponse,
                                                              String latitude, String longitude, boolean fahrenheit) {
        openWeatherMapCurrentWeatherResponse.getCoordinates().setLatitude(latitude);
        openWeatherMapCurrentWeatherResponse.getCoordinates().setLongitude(longitude);
        if (fahrenheit) {
            openWeatherMapCurrentWeatherResponse
                    .setVisibility(Math.round(MeterToMile(openWeatherMapCurrentWeatherResponse.getVisibility()) * 100.0) / 100.0);
            
        } else {
            openWeatherMapCurrentWeatherResponse.getWind().setSpeed(
                    new BigDecimal(MeterPerSecondToKiloMeterPerHour(openWeatherMapCurrentWeatherResponse
                            .getWind()
                            .getSpeed()
                            .doubleValue())));
        }
        return openWeatherMapCurrentWeatherResponse;
    }
}
