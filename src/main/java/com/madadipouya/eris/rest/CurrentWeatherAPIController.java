package com.madadipouya.eris.rest;

import com.google.common.collect.Lists;
import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import com.madadipouya.eris.service.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

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

@RestController
public class CurrentWeatherAPIController {

    private static final String ERR_NO_LATITUDE_LONGITUDE_PROVIDED = "No latitude and/or longitude provided!";

    private static final String ERR_INVALID_LATITUDE_LONGITUDE_PROVIDED = "Invalid latitude and/or longitude provided!";

    @Autowired
    private Weather weather;

    /*
    *
    * "/current" is kept for backward compatibility, will be deprecated soon
    *
    * */
    @RequestMapping(value = {"v1/weather/current", "/current"})
    public ResponseEntity<CurrentWeatherCondition> getCurrent(@RequestParam(value = "lat") String latitude, @RequestParam(value = "lon") String longitude,
                                                              @RequestParam(value = "fahrenheit", required = false, defaultValue = "false") boolean fahrenheit) {
        if (!isLatitudeLongitudeExist(latitude, longitude)) {
            return ResponseEntity.badRequest().body(createErrorResponse(ERR_NO_LATITUDE_LONGITUDE_PROVIDED));
        } else if (!isLatitudeLongitudeValid(latitude, longitude)) {
            return ResponseEntity.badRequest().body(createErrorResponse(ERR_INVALID_LATITUDE_LONGITUDE_PROVIDED));
        } else {
            return ResponseEntity.ok(weather.getCurrent(latitude, longitude, fahrenheit));
        }
    }

    @RequestMapping("v1/weather/currentbyip")
    public ResponseEntity<CurrentWeatherCondition> getCurrentByIp(@RequestParam(value = "fahrenheit",
            required = false, defaultValue = "false") boolean fahrenheit, HttpServletRequest request) {

        return ResponseEntity.ok(weather.getCurrent(request, fahrenheit));
    }

    private boolean isLatitudeLongitudeExist(String latitude, String longitude) {
        return (isNotBlank(latitude) || isNotBlank(longitude));
    }

    private boolean isLatitudeLongitudeValid(String latitude, String longitude) {
        return (isCreatable(latitude) && isCreatable(longitude));
    }

    private CurrentWeatherCondition createErrorResponse(String... errorMessage) {
        return new CurrentWeatherCondition(Lists.newArrayList(errorMessage));
    }
}