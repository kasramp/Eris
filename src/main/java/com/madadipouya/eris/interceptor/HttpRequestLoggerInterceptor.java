package com.madadipouya.eris.interceptor;

import com.madadipouya.eris.service.ipgeolocation.IpGeoLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

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

@Component
public class HttpRequestLoggerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestLoggerInterceptor.class);
    private static final String MESSAGE = "Serving request for IP: %s with parameters: %s - @ %s";

    private final IpGeoLocation ipGeoLocation;

    public HttpRequestLoggerInterceptor(IpGeoLocation ipGeoLocation) {
        this.ipGeoLocation = ipGeoLocation;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        try {
            logger.info(String.format(MESSAGE, getRequestIp(request),
                    beautifyRequestParameters(request.getParameterMap()), trimToEmpty(request.getRequestURI())));
        } catch(Exception ex) {
            // Don't disturb the request, suppress any error occurs while logging the request
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        /*
        * No implementation needed at this stage
        * */
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        /*
        * No implementation needed at this stage
        * */
    }

    private String getRequestIp(HttpServletRequest httpServletRequest) {
        return ipGeoLocation.getRequestIpAddress(httpServletRequest);
    }

    private String beautifyRequestParameters(Map<String, String[]> map) {
        return map.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()[0]).collect(Collectors.joining(", "));
    }
}