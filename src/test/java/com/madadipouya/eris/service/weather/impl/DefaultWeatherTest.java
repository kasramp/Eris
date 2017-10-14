package com.madadipouya.eris.service.weather.impl;

import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;
import com.madadipouya.eris.service.ipgeolocation.IpGeoLocation;
import com.madadipouya.eris.service.ipgeolocation.model.Coordinates;
import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import com.madadipouya.eris.util.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;

import static com.google.common.collect.ImmutableList.of;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.isA;
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
* © 2017 Kasra Madadipouya <kasra@madadipouya.com>
*/

@RunWith(MockitoJUnitRunner.class)
public class DefaultWeatherTest {

    @Spy
    @InjectMocks
    private DefaultWeather weatherService = new DefaultWeather();

    @Mock
    private IpGeoLocation ipGeoLocation;

    @Test
    public void testGetCurrentHttpRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        Coordinates coordinates = mock(Coordinates.class);
        when(coordinates.getLatitude()).thenReturn("1.00");
        when(coordinates.getLongitude()).thenReturn("2.00");
        doReturn(coordinates).when(ipGeoLocation).getCoordinates(request);
        doReturn(mock(CurrentWeatherCondition.class)).when(weatherService)
                .getCurrent("1.00", "2.00", false);
        weatherService.getCurrent(request, false);
        Mockito.verify(ipGeoLocation, times(1)).getCoordinates(isA(HttpServletRequest.class));
        Mockito.verify(weatherService, times(1)).getCurrent("1.00", "2.00", false);

    }

    @Test
    public void testConvertBean() {
        OpenWeatherMapCurrentWeatherResponse weatherApiResponse = mock(OpenWeatherMapCurrentWeatherResponse.class);
        when(weatherApiResponse.getWeather())
                .thenReturn(new ArrayList<>(of(new OpenWeatherMapCurrentWeatherResponse.Weather(1, "Main", "Description", "Icon"))));
        when(weatherApiResponse.getMain()).thenReturn(mock(OpenWeatherMapCurrentWeatherResponse.Main.class));
        when(weatherApiResponse.getMain().getTemperature()).thenReturn(new BigDecimal(30));
        CurrentWeatherCondition weatherCondition = weatherService.convertBean(weatherApiResponse);
        assertEquals("Icon", weatherCondition.getIconName());
        assertEquals(String.format(BeanUtils.ICON_URL, "Icon"), weatherCondition.getIcon());
        assertEquals(new BigDecimal(30), weatherCondition.getTemperature());
    }


}
