package com.madadipouya.eris.interceptor;

import com.madadipouya.eris.service.ipgeolocation.IpGeoLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

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

@Component
public class HttpRequestLoggerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestLoggerInterceptor.class);
    private static final String MESSAGE = "Serving request for IP: %s, with parameters: %s";
    @Autowired
    IpGeoLocation ipGeoLocation;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        try {
            logger.info(String.format(MESSAGE, getRequestIp(request), beautifyRequestParameters(request.getParameterMap())));
            return true;
        } catch(Exception ex) {}

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private String getRequestIp(HttpServletRequest httpServletRequest) {
        return ipGeoLocation.getRequestIpAddress(httpServletRequest);
    }

    private String beautifyRequestParameters(Map<String, String[]> map) {
        return map.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()[0]).collect(Collectors.joining(", "));
    }
}
