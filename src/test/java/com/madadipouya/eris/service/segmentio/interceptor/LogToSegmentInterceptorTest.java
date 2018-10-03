package com.madadipouya.eris.service.segmentio.interceptor;

import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;
import com.madadipouya.eris.service.ipgeolocation.IpGeoLocation;
import com.madadipouya.eris.service.segmentio.SegmentIoAnalytics;
import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.any;
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
* © 2017-2018 Kasra Madadipouya <kasra@madadipouya.com>
*/

@ExtendWith(MockitoExtension.class)
public class LogToSegmentInterceptorTest {

    @Spy
    @InjectMocks
    LogToSegmentInterceptor logToSegmentInterceptor;

    @Mock
    IpGeoLocation ipGeoLocation;

    @Mock
    SegmentIoAnalytics segmentIoAnalytics;

    @Test
    public void testGetIp() {
        JoinPoint joinPoint = mock(JoinPoint.class);
        when(joinPoint.getArgs()).thenReturn(new Object[]{new MockHttpServletResponse(), new MockHttpServletRequest()});
        when(ipGeoLocation.getRequestIpAddress(any(HttpServletRequest.class))).thenReturn("192.168.0.1");
        String ipAddress = logToSegmentInterceptor.getIp(joinPoint);
        verify(ipGeoLocation, times(1)).getRequestIpAddress(Mockito.isA(HttpServletRequest.class));
        assertEquals(2, joinPoint.getArgs().length);
        assertEquals("192.168.0.1", ipAddress);
    }

    @Test
    public void testGetEvent() {
        SegmentIoAnalytics.EventType eventType;
        eventType = logToSegmentInterceptor.getEvent("ByIp");
        assertEquals(SegmentIoAnalytics.EventType.CURRENT_BY_IP, eventType);
        eventType = logToSegmentInterceptor.getEvent("getCurrentByIp");
        assertEquals(SegmentIoAnalytics.EventType.CURRENT_BY_IP, eventType);
        eventType = logToSegmentInterceptor.getEvent("getCurrent");
        assertEquals(SegmentIoAnalytics.EventType.CURRENT, eventType);
        eventType = logToSegmentInterceptor.getEvent("abc");
        assertEquals(SegmentIoAnalytics.EventType.CURRENT, eventType);
    }

    @Test
    public void testConstructSegmentEventWhenHasError() {
        CurrentWeatherCondition currentWeatherCondition = mock(CurrentWeatherCondition.class);
        when(currentWeatherCondition.getErrors()).thenReturn(List.of("Error1", "Error2"));
        Map<String, String> result = logToSegmentInterceptor.constructSegmentEvent(currentWeatherCondition, "192.168.0.1");
        assertNotNull(result);
        assertEquals(2, result.size());
        assertThat(result, hasKey("ERROR"));
        assertThat(result, hasKey("IP"));
        assertEquals("Error1", result.get("ERROR"));
        assertEquals("192.168.0.1", result.get("IP"));
    }

    @Test
    public void testConstructSegmentEventWhenNoError() {
        CurrentWeatherCondition currentWeather = new CurrentWeatherCondition();
        OpenWeatherMapCurrentWeatherResponse.Sys sys = new OpenWeatherMapCurrentWeatherResponse.Sys();
        sys.setCountryNameFull("Germany");
        currentWeather.setSys(sys);
        currentWeather.setCoordinates(new OpenWeatherMapCurrentWeatherResponse.Coordinates("1.00", "2.3456"));
        OpenWeatherMapCurrentWeatherResponse.Main main = new OpenWeatherMapCurrentWeatherResponse.Main();
        main.setTemperature(new BigDecimal("27.50"));
        currentWeather.setMain(main);
        Map<String, String> result = logToSegmentInterceptor.constructSegmentEvent(currentWeather, "192.168.0.1");
        assertNotNull(result);
        assertEquals(6, result.size());
        assertThat(result, hasKey("COUNTRY"));
        assertThat(result, hasKey("LATITUDE"));
        assertThat(result, hasKey("LONGITUDE"));
        assertThat(result, hasKey("TEMPERATURE"));
        assertThat(result, hasKey("IP"));
        assertThat(result, hasKey("ERROR"));
        assertEquals("Germany", result.get("COUNTRY"));
        assertEquals("1.00", result.get("LATITUDE"));
        assertEquals("2.3456", result.get("LONGITUDE"));
        assertEquals("27.50", result.get("TEMPERATURE"));
        assertEquals("192.168.0.1", result.get("IP"));
        assertEquals("", result.get("ERROR"));
        assertEquals("v1.0", currentWeather.getApiVersion());
    }

    @Test
    public void testLogAnalyticsWhenReturnValueNotResponseEntity() {
        logToSegmentInterceptor.logAnalytics(mock(JoinPoint.class), "String");
        verify(segmentIoAnalytics, times(0)).fireEvent(any(), any(), any());
    }

    @Test
    public void testLogAnalytics() {
        CurrentWeatherCondition currentWeather = new CurrentWeatherCondition();
        OpenWeatherMapCurrentWeatherResponse.Sys sys = new OpenWeatherMapCurrentWeatherResponse.Sys();
        sys.setCountryNameFull("Germany");
        currentWeather.setSys(sys);
        currentWeather.setCoordinates(new OpenWeatherMapCurrentWeatherResponse.Coordinates("1.00", "2.3456"));
        OpenWeatherMapCurrentWeatherResponse.Main main = new OpenWeatherMapCurrentWeatherResponse.Main();
        main.setTemperature(new BigDecimal("27.50"));
        currentWeather.setMain(main);
        JoinPoint joinPoint = mock(JoinPoint.class);
        Signature singature = mock(Signature.class);
        ResponseEntity responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(currentWeather);
        when(singature.getName()).thenReturn("getCurrentByIp");
        when(joinPoint.getSignature()).thenReturn(singature);
        when(joinPoint.getArgs()).thenReturn(new Object[]{new MockHttpServletResponse(), new MockHttpServletRequest()});
        when(ipGeoLocation.getRequestIpAddress(any(HttpServletRequest.class))).thenReturn("192.168.0.1");
        logToSegmentInterceptor.logAnalytics(joinPoint, responseEntity);
        verify(ipGeoLocation, times(1)).getRequestIpAddress(any(HttpServletRequest.class));
        verify(segmentIoAnalytics, times(1)).fireEvent(any(SegmentIoAnalytics.EventType.class), anyString(), anyMapOf(String.class, String.class));
        assertEquals("v1.0", currentWeather.getApiVersion());
    }
}