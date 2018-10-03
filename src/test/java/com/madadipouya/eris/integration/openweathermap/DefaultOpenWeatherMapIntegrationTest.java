package com.madadipouya.eris.integration.openweathermap;

import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;
import com.madadipouya.eris.util.PropertyUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class DefaultOpenWeatherMapIntegrationTest {

    @Spy
    @InjectMocks
    private DefaultOpenWeatherMapIntegration openWeatherMapIntegration;

    @Mock
    private PropertyUtils propertyUtils;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetCurrentWeatherConditionCelsius() {
        OpenWeatherMapCurrentWeatherResponse response = new OpenWeatherMapCurrentWeatherResponse();
        response.setCoordinates(new OpenWeatherMapCurrentWeatherResponse.Coordinates());
        response.setWind(new OpenWeatherMapCurrentWeatherResponse.Wind(new BigDecimal("1"), new BigDecimal("60")));
        response.setVisibility(10000);
        when(propertyUtils.getOpenWeatherMapApiKey()).thenReturn("OpenWeatherMapKey");
        when(restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?appid=OpenWeatherMapKey&units=metric&lat=1.00&lon=2.00",
                OpenWeatherMapCurrentWeatherResponse.class)).thenReturn(response);
        OpenWeatherMapCurrentWeatherResponse result = openWeatherMapIntegration.getCurrentWeatherCondition("1.00", "2.00", false);
        verify(restTemplate, times(1)).getForObject(anyString(), any());
        assertEquals(10.00, result.getVisibility(), 0.001);
        assertEquals("1.00", result.getCoordinates().getLatitude());
        assertEquals("2.00", result.getCoordinates().getLongitude());
        assertEquals(new BigDecimal("3.6"), result.getWind().getSpeed());
    }

    @Test
    public void testGetCurrentWeatherConditionFahrenheit() {
        OpenWeatherMapCurrentWeatherResponse response = new OpenWeatherMapCurrentWeatherResponse();
        response.setCoordinates(new OpenWeatherMapCurrentWeatherResponse.Coordinates());
        response.setWind(new OpenWeatherMapCurrentWeatherResponse.Wind(new BigDecimal("3.4"), new BigDecimal("20")));
        response.setVisibility(10000);
        when(propertyUtils.getOpenWeatherMapApiKey()).thenReturn("OpenWeatherMapKey");
        when(restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?appid=OpenWeatherMapKey&units=imperial&lat=7.00&lon=10.00",
                OpenWeatherMapCurrentWeatherResponse.class)).thenReturn(response);
        OpenWeatherMapCurrentWeatherResponse result = openWeatherMapIntegration.getCurrentWeatherCondition("7.00", "10.00", true);
        verify(restTemplate, times(1)).getForObject(anyString(), any());
        assertEquals(6.21, result.getVisibility());
        assertEquals("7.00", result.getCoordinates().getLatitude());
        assertEquals("10.00", result.getCoordinates().getLongitude());
        assertEquals(new BigDecimal("3.4"), result.getWind().getSpeed());
    }
}
