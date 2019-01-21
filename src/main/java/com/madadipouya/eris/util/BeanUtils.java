package com.madadipouya.eris.util;

import com.madadipouya.eris.integration.ipapi.remote.response.IpApiResponse;
import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;
import com.madadipouya.eris.service.ipgeolocation.model.Coordinates;
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
* © 2017-2019 Kasra Madadipouya <kasra@madadipouya.com>
*/

public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static final String ICON_URL = "http://openweathermap.org/img/w/%s.png";

    private BeanUtils() {

    }

    public static CurrentWeatherCondition copyProperties(OpenWeatherMapCurrentWeatherResponse source, CurrentWeatherCondition target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
        return setBackwardCompatibilityProperties(target);
    }

    public static Coordinates copyProperties(IpApiResponse source, Coordinates target) {
        target.setLatitude(source.getLatitude());
        target.setLongitude(source.getLongitude());
        return target;
    }

    private static CurrentWeatherCondition setBackwardCompatibilityProperties(CurrentWeatherCondition target) {
        String iconName = target.getWeather().get(0).getIcon();
        target.setTemperature(target.getMain().getTemperature());
        target.setIconName(iconName);
        target.setIcon(String.format(ICON_URL, iconName));
        return target;
    }
}