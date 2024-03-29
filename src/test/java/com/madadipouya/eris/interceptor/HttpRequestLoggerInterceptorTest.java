package com.madadipouya.eris.interceptor;

import com.madadipouya.eris.service.ipgeolocation.impl.DefaultIpGeoLocation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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

@ExtendWith(MockitoExtension.class)
class HttpRequestLoggerInterceptorTest {

    @Spy
    @InjectMocks
    private HttpRequestLoggerInterceptor interceptor;

    @Mock
    private DefaultIpGeoLocation ipGeoLocation;

    @Test
    void testPreHandle() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameterMap()).thenReturn(Map.of("latitude", new String[]{"1.00"}, "longitude", new String[]{"2.00"}));
        when(request.getRequestURI()).thenReturn("/v1/weather/current");
        doReturn("185.86.151.11").when(ipGeoLocation).getRequestIpAddress(request);
        boolean result = interceptor.preHandle(request, mock(HttpServletResponse.class), mock(Object.class));
        assertTrue(result);
        verify(request, times(1)).getParameterMap();
        verify(request, times(1)).getRequestURI();
        verify(ipGeoLocation, times(1)).getRequestIpAddress(any(HttpServletRequest.class));
    }

    @Test
    void testPreHandleNotThrowsException() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameterMap()).thenReturn(null);
        doReturn("185.86.151.11").when(ipGeoLocation).getRequestIpAddress(request);
        boolean result = interceptor.preHandle(request, mock(HttpServletResponse.class), mock(Object.class));
        assertTrue(result);
        verify(request, times(1)).getParameterMap();
        verify(request, times(0)).getRequestURI();
        verify(ipGeoLocation, times(1)).getRequestIpAddress(any(HttpServletRequest.class));
    }
}
