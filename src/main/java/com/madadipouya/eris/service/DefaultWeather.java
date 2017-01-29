package com.madadipouya.eris.service;

import com.madadipouya.eris.integration.groupkt.GroupktCountryNameIntegration;
import com.madadipouya.eris.integration.openstreetmap.OpenStreetMapIntegration;
import com.madadipouya.eris.integration.openweathermap.OpenWeatherMapIntegration;
import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.madadipouya.eris.util.BeanUtils.copyProperties;

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

@Service("weather")
public class DefaultWeather implements Weather {

    @Autowired
    private OpenWeatherMapIntegration openWeatherMapIntegration;

    @Autowired
    private OpenStreetMapIntegration openStreetMapIntegration;

    @Autowired
    private GroupktCountryNameIntegration groupktCountryNameIntegration;

    @Override
    public CurrentWeatherCondition getCurrent(String latitude, String longitude, boolean fahrenheit) {
        return getCurrentWeatherCondition(latitude, longitude, fahrenheit);
    }

    private CurrentWeatherCondition getCurrentWeatherCondition(String latitude, String longitude, boolean fahrenheit) {
        return setGeoLocation(
                setCountryFullName(
                        convertBean(openWeatherMapIntegration.getCurrentWeatherCondition(latitude,longitude,fahrenheit))
                ));
    }

    private CurrentWeatherCondition convertBean(OpenWeatherMapCurrentWeatherResponse openWeatherMapCurrentWeatherResponse) {
        return copyProperties(openWeatherMapCurrentWeatherResponse, new CurrentWeatherCondition());
    }

    private String getCountryCode(CurrentWeatherCondition currentWeatherCondition) {
        return currentWeatherCondition.getSys().getCountry();
    }

    private CurrentWeatherCondition setCountryFullName(CurrentWeatherCondition currentWeatherCondition) {
        String countryFullName = groupktCountryNameIntegration.getCountryFullName(getCountryCode(currentWeatherCondition));
        currentWeatherCondition.getSys().setCountryNameFull(countryFullName);
        currentWeatherCondition.setCountry(countryFullName);
        return currentWeatherCondition;
    }

    private CurrentWeatherCondition setGeoLocation(CurrentWeatherCondition currentWeatherCondition) {
        currentWeatherCondition.setGeoLocation(
                openStreetMapIntegration.getAddressByCoordinates(
                        currentWeatherCondition.getCoordinates().getLatitude(),
                        currentWeatherCondition.getCoordinates().getLongitude())
        );
        return currentWeatherCondition;
    }
}
