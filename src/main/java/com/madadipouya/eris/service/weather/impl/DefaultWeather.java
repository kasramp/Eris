package com.madadipouya.eris.service.weather.impl;

import com.madadipouya.eris.integration.groupkt.GroupktCountryNameIntegration;
import com.madadipouya.eris.integration.openstreetmap.OpenStreetMapIntegration;
import com.madadipouya.eris.integration.openweathermap.OpenWeatherMapIntegration;
import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;
import com.madadipouya.eris.service.feelslike.FeelsLikeService;
import com.madadipouya.eris.service.ipgeolocation.IpGeoLocation;
import com.madadipouya.eris.service.ipgeolocation.model.Coordinates;
import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import com.madadipouya.eris.service.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
* © 2017-2019 Kasra Madadipouya <kasra@madadipouya.com>
*/

@Service("weather")
public class DefaultWeather implements Weather {
    
    @Autowired
    private OpenWeatherMapIntegration openWeatherMapIntegration;

    @Autowired
    private OpenStreetMapIntegration openStreetMapIntegration;

    @Autowired
    private GroupktCountryNameIntegration groupktCountryNameIntegration;

    @Autowired
    private IpGeoLocation ipGeoLocation;

    @Autowired
    private FeelsLikeService feelsLikeService;

    @Override
    public CurrentWeatherCondition getCurrent(HttpServletRequest request, boolean fahrenheit) {
        Coordinates coordinates = ipGeoLocation.getCoordinates(request);
        return getCurrent(coordinates.getLatitude(), coordinates.getLongitude(), fahrenheit);
    }

    @Override
    public CurrentWeatherCondition getCurrent(String latitude, String longitude, boolean fahrenheit) {
        return getCurrentWeatherCondition(latitude, longitude, fahrenheit);
    }

    CurrentWeatherCondition getCurrentWeatherCondition(String latitude, String longitude, boolean fahrenheit) {
        return setGeoLocation(
                setCountryFullName(
                        setFeelsLike(
                                convertBean(
                                        openWeatherMapIntegration.getCurrentWeatherCondition(
                                                latitude, longitude, fahrenheit))
                                , fahrenheit)
                )
        );
    }

    CurrentWeatherCondition convertBean(OpenWeatherMapCurrentWeatherResponse openWeatherMapCurrentWeatherResponse) {
        return copyProperties(openWeatherMapCurrentWeatherResponse, new CurrentWeatherCondition());
    }

    String getCountryCode(CurrentWeatherCondition currentWeatherCondition) {
        return currentWeatherCondition.getSys().getCountry();
    }

    CurrentWeatherCondition setCountryFullName(CurrentWeatherCondition currentWeatherCondition) {
        String countryFullName = groupktCountryNameIntegration.getCountryFullName(getCountryCode(currentWeatherCondition));
        currentWeatherCondition.getSys().setCountryNameFull(countryFullName);
        currentWeatherCondition.setCountry(countryFullName);
        return currentWeatherCondition;
    }

    CurrentWeatherCondition setGeoLocation(CurrentWeatherCondition currentWeatherCondition) {
        currentWeatherCondition.setGeoLocation(
                openStreetMapIntegration.getAddressByCoordinates(
                        currentWeatherCondition.getCoordinates().getLatitude(),
                        currentWeatherCondition.getCoordinates().getLongitude())
        );
        return currentWeatherCondition;
    }

    CurrentWeatherCondition setFeelsLike(CurrentWeatherCondition currentWeatherCondition, boolean fahrenheit) {
        currentWeatherCondition.setFeelsLike(feelsLikeService.getFeelsLike(
                currentWeatherCondition.getMain().getTemperature().doubleValue(),
                currentWeatherCondition.getWind().getSpeed().doubleValue(),
                currentWeatherCondition.getMain().getHumidity().doubleValue(),
                fahrenheit));
        return currentWeatherCondition;
    }
}
