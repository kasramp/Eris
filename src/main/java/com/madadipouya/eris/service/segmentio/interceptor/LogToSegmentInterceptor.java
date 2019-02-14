package com.madadipouya.eris.service.segmentio.interceptor;

import com.madadipouya.eris.service.ipgeolocation.IpGeoLocation;
import com.madadipouya.eris.service.segmentio.SegmentIoAnalytics;
import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

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

@Aspect
@Component
public class LogToSegmentInterceptor {

    private final SegmentIoAnalytics segmentIoAnalytics;

    private final IpGeoLocation ipGeoLocation;

    public LogToSegmentInterceptor(SegmentIoAnalytics segmentIoAnalytics, IpGeoLocation ipGeoLocation) {
        this.segmentIoAnalytics = segmentIoAnalytics;
        this.ipGeoLocation = ipGeoLocation;
    }

    @AfterReturning(
            value = "execution(* com.madadipouya.eris.rest.CurrentWeatherAPIController.getCurrent(..)) " +
                    " || execution(* com.madadipouya.eris.rest.CurrentWeatherAPIController.getCurrentByIp(..))"
            , returning = "returnValue")
    public void logAnalytics(JoinPoint joinPoint, Object returnValue) {
        if (returnValue instanceof ResponseEntity) {
            String ip = getIp(joinPoint);
            Map<String, String> data = constructSegmentEvent((CurrentWeatherCondition) ((ResponseEntity) returnValue).getBody(), ip);
            segmentIoAnalytics.fireEvent(getEvent(joinPoint.getSignature().getName()), ip, data);
        }
    }

    Map<String, String> constructSegmentEvent(CurrentWeatherCondition currentWeatherCondition, String ip) {
        List<String> errors = currentWeatherCondition.getErrors();
        var locationData = new TreeMap<>(Map.of("IP", ip, "ERROR", getError(errors)));

        if (!hasError(errors)) {
            locationData.putAll(
                    Map.of(
                    "COUNTRY", currentWeatherCondition.getSys().getCountryNameFull(),
                    "LATITUDE", currentWeatherCondition.getCoordinates().getLatitude(),
                    "LONGITUDE", currentWeatherCondition.getCoordinates().getLongitude(),
                    "TEMPERATURE", currentWeatherCondition.getMain().getTemperature().toString()
                    )
            );
        }
        return Map.copyOf(locationData);
    }

    SegmentIoAnalytics.EventType getEvent(String methodName) {
        return SegmentIoAnalytics.EventType.getEventType(methodName);
    }

    String getIp(JoinPoint joinPoint) {
        int argsNumber = joinPoint.getArgs().length;
        return ipGeoLocation.getRequestIpAddress((HttpServletRequest) joinPoint.getArgs()[argsNumber - 1]);
    }

    private String getError(List<String> errors) {
        return isEmpty(errors) ? "" : errors.get(0);
    }

    private boolean hasError(List<String> errors) {
        return isNotEmpty(errors);
    }
}