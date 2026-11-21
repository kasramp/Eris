package com.madadipouya.eris.interceptor;

import com.madadipouya.eris.service.ipgeolocation.IpGeoLocation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
 * © 2017-2023 Kasra Madadipouya <kasra@madadipouya.com>
 */

@Component
public class HttpRequestLoggerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestLoggerInterceptor.class);

    private final IpGeoLocation ipGeoLocation;

    public HttpRequestLoggerInterceptor(IpGeoLocation ipGeoLocation) {
        this.ipGeoLocation = ipGeoLocation;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Serving request for IP: {} with parameters: {} - @ {}", sanitize(getRequestIp(request)),
                        sanitize(beautifyRequestParameters(request.getParameterMap())), sanitize(trimToEmpty(request.getRequestURI())));
            }
        } catch (Exception _) {
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

    // client-controlled values: strip line breaks so they can't forge log lines
    private static String sanitize(String value) {
        return value == null ? null : value.replaceAll("[\n\r\t]", "_");
    }
}