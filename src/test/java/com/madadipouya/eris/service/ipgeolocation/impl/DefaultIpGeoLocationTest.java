package com.madadipouya.eris.service.ipgeolocation.impl;

import com.madadipouya.eris.integration.ipapi.IpApiIntegration;
import com.madadipouya.eris.integration.ipapi.remote.response.IpApiResponse;
import com.madadipouya.eris.service.ipgeolocation.model.Coordinates;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
class DefaultIpGeoLocationTest {

    @Spy
    @InjectMocks
    private DefaultIpGeoLocation ipGeoLocation;

    @Mock
    private IpApiIntegration ipApiIntegration;

    @Test
    void testGetCoordinatesIpAddress() {
        IpApiResponse ipApiResponse = mock(IpApiResponse.class);
        when(ipApiResponse.getLatitude()).thenReturn("1.00");
        when(ipApiResponse.getLongitude()).thenReturn("2.00");
        doReturn(ipApiResponse).when(ipApiIntegration).getCoordinatesFromIp("185.86.151.11");
        Coordinates result = ipGeoLocation.getCoordinates("185.86.151.11");
        verify(ipApiIntegration, times(1)).getCoordinatesFromIp(anyString());
        assertNotNull(result);
        assertEquals("1.00", result.getLatitude());
        assertEquals("2.00", result.getLongitude());
    }

    @Test
    void testGetRequestIpAddressNoSubnet() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        doReturn("185.86.151.11").when(ipGeoLocation).getRequestIpAddressSimple(request);
        String ipAddress = ipGeoLocation.getRequestIpAddress(request);
        verify(ipGeoLocation, times(1)).getRequestIpAddressSimple(any(HttpServletRequest.class));
        assertNotNull(ipAddress);
        assertEquals("185.86.151.11", ipAddress);
    }

    @Test
    void testGetRequestIpAddressWithOneSubnet() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        doReturn("192.168.0.1 , 185.86.151.11").when(ipGeoLocation).getRequestIpAddressSimple(request);
        String ipAddress = ipGeoLocation.getRequestIpAddress(request);
        verify(ipGeoLocation, times(1)).getRequestIpAddressSimple(any(HttpServletRequest.class));
        assertNotNull(ipAddress);
        assertEquals("185.86.151.11", ipAddress);
    }

    @Test
    void testGetRequestIpAddressSimpleNoProxy() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("X-FORWARDED-FOR")).thenReturn("185.86.151.11");
        String ipAddress = ipGeoLocation.getRequestIpAddressSimple(request);
        assertNotNull(ipAddress);
        assertEquals("185.86.151.11", ipAddress);
        verify(request, times(1)).getHeader(anyString());
    }

    @Test
    void testGetRequestIpAddressSimpleWithProxy() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("X-FORWARDED-FOR")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("185.86.151.11");
        String ipAddress = ipGeoLocation.getRequestIpAddressSimple(request);
        assertNotNull(ipAddress);
        assertEquals("185.86.151.11", ipAddress);
        verify(request, times(1)).getHeader(anyString());
        verify(request, times(1)).getRemoteAddr();
    }
}
