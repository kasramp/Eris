package com.madadipouya.eris.util;

import com.madadipouya.eris.integration.ipapi.remote.response.IpApiResponse;
import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;
import com.madadipouya.eris.service.ipgeolocation.model.Coordinates;
import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;

public class BeanUtils extends org.springframework.beans.BeanUtils {

    private static final String ICON_URL = "http://openweathermap.org/img/w/%s.png";

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
