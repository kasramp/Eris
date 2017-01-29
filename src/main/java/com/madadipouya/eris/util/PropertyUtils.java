package com.madadipouya.eris.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by kixz on 1/29/17.
 */

@Component
@PropertySource(value = "apikey.properties")
public class PropertyUtils {

    @Value("${openweathermap.api.key}")
    private String openWeatherMapApiKey;

    public String getOpenWeatherMapApiKey() {
        return openWeatherMapApiKey;
    }

    public void setOpenWeatherMapApiKey(String openWeatherMapApiKey) {
        this.openWeatherMapApiKey = openWeatherMapApiKey;
    }
}
