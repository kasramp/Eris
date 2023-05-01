package com.madadipouya.eris.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

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
 * © 2017-2023 Kasra Madadipouya <kasra@madadipouya.com>
 */

@Component
@PropertySource(value = "classpath:apikey.properties")
public class PropertyUtils {

    @Value("${openweathermap.api.key}")
    private String openWeatherMapApiKey;

    @Value("${segmentio.write.api.key}")
    private String segmentIoWriteApiKey;

    @Value("${actuator.username}")
    private String actuatorUsername;

    @Value("${actuator.password}")
    private String actuatorPassword;

    public String getOpenWeatherMapApiKey() {
        return openWeatherMapApiKey;
    }

    public String getSegmentIoWriteApiKey() {
        return segmentIoWriteApiKey;
    }

    public String getActuatorUsername() {
        return actuatorUsername;
    }

    public String getActuatorPassword() {
        return actuatorPassword;
    }
}