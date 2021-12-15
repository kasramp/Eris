package com.madadipouya.eris.service.weather.model;

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
* © 2017-2022 Kasra Madadipouya <kasra@madadipouya.com>
*/

public class CurrentWeatherCondition extends OpenWeatherMapCurrentWeatherResponse {

    public CurrentWeatherCondition(List<String> errors) {
        setErrors(errors);
        setApiVersion("v1.0");
    }

    public CurrentWeatherCondition() {
        this(List.of());
    }

    private String country;  //For backward compatibility

    private String geoLocation;  //For backward compatibility

    private BigDecimal temperature; //For backward compatibility

    private String icon;  //For backward compatibility

    private String iconName;  //For backward compatibility

    private double feelsLike;

    private List<String> errors;

    private String apiVersion;

    /**
     * @deprecated  As of /v1/ API, replaced by {@link Sys#getCountry()}
     * Available for backward compatibility only
     */
    @Deprecated
    public String getCountry() {
        return country;
    }

    /**
     * @deprecated  As of /v1/ API, replaced by {@link Sys#setCountry(String)}
     * Available for backward compatibility only
     */
    @Deprecated
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @deprecated  As of /v1/ API, replaced by {@link Coordinates#getCoordinates()}
     * Available for backward compatibility only
     */
    @Deprecated
    public String getGeoLocation() {
        return geoLocation;
    }

    /**
     * @deprecated  As of /v1/ API, replaced by {@link Coordinates#setCoordinates(Coordinates)}
     * Available for backward compatibility only
     */
    @Deprecated
    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    /**
     * @deprecated  As of /v1/ API, replaced by {@link Main#getTemperature()}
     * Available for backward compatibility only
     */
    @Deprecated
    public BigDecimal getTemperature() {
        return temperature;
    }

    /**
     * @deprecated  As of /v1/ API, replaced by {@link Main#setTemperature(BigDecimal)}
     * Available for backward compatibility only
     */
    @Deprecated
    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    /**
     * @deprecated  As of /v1/ API, functionality will be removed
     * Available for backward compatibility only
     */
    @Deprecated
    public String getIcon() {
        return icon;
    }

    /**
     * @deprecated  As of /v1/ API, functionality will be removed
     * Available for backward compatibility only
     */
    @Deprecated
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @deprecated  As of /v1/ API, replaced by {@link Weather#getIcon()}
     * Available for backward compatibility only
     */
    @Deprecated
    public String getIconName() {
        return iconName;
    }

    /**
     * @deprecated  As of /v1/ API, replaced by {@link Weather#setIcon(String)}
     * Available for backward compatibility only
     */
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