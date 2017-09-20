package com.madadipouya.eris.service.weather.model;

import com.google.common.collect.ImmutableList;
import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;

import java.math.BigDecimal;
import java.util.List;

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

public class CurrentWeatherCondition extends OpenWeatherMapCurrentWeatherResponse {

    public CurrentWeatherCondition(List<String> errors) {
        setErrors(errors);
        setApiVersion("v1.0");
    }

    public CurrentWeatherCondition() {
        this(ImmutableList.of());
    }

    private String country;  //For backward compatibility

    private String geoLocation;  //For backward compatibility

    private BigDecimal temperature; //For backward compatibility

    private String icon;  //For backward compatibility

    private String iconName;  //For backward compatibility

    private double feelsLike;

    private List<String> errors;

    private String apiVersion;

    @Deprecated
    public String getCountry() {
        return country;
    }

    @Deprecated
    public void setCountry(String country) {
        this.country = country;
    }

    @Deprecated
    public String getGeoLocation() {
        return geoLocation;
    }

    @Deprecated
    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    @Deprecated
    public BigDecimal getTemperature() {
        return temperature;
    }

    @Deprecated
    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    @Deprecated
    public String getIcon() {
        return icon;
    }

    @Deprecated
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Deprecated
    public String getIconName() {
        return iconName;
    }

    @Deprecated
    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }
}