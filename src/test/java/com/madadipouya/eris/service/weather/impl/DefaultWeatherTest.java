package com.madadipouya.eris.service.weather.impl;

import com.madadipouya.eris.integration.groupkt.GroupktCountryNameIntegration;
import com.madadipouya.eris.integration.openstreetmap.OpenStreetMapIntegration;
import com.madadipouya.eris.integration.openweathermap.OpenWeatherMapIntegration;
import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;
import com.madadipouya.eris.service.feelslike.FeelsLikeService;
import com.madadipouya.eris.service.ipgeolocation.IpGeoLocation;
import com.madadipouya.eris.service.ipgeolocation.model.Coordinates;
import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import com.madadipouya.eris.util.BeanUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
* © 2017-2018 Kasra Madadipouya <kasra@madadipouya.com>
*/

@ExtendWith(MockitoExtension.class)
public class DefaultWeatherTest {

    @Spy
    @InjectMocks
    private DefaultWeather weatherService;

    @Mock
    private IpGeoLocation ipGeoLocation;

    @Mock
    private GroupktCountryNameIntegration groupktCountryNameIntegration;

    @Mock
    private OpenStreetMapIntegration openStreetMapIntegration;

    @Mock
    private FeelsLikeService feelsLikeService;

    @Mock
    private OpenWeatherMapIntegration openWeatherMapIntegration;

    @Test
    public void testGetCurrentHttpRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        Coordinates coordinates = stubCoordinates();
        doReturn(coordinates).when(ipGeoLocation).getCoordinates(request);
        doReturn(mock(CurrentWeatherCondition.class)).when(weatherService).getCurrentWeatherCondition(anyString(), anyString(), anyBoolean());
        weatherService.getCurrent(request, false);
        verify(ipGeoLocation, times(1)).getCoordinates(isA(HttpServletRequest.class));
        verify(weatherService, times(1)).getCurrent("1.00", "2.00", false);
    }

    @Test
    public void testConvertBean() {
        OpenWeatherMapCurrentWeatherResponse weatherApiResponse = mock(OpenWeatherMapCurrentWeatherResponse.class);
        when(weatherApiResponse.getWeather())
                .thenReturn(List.of(new OpenWeatherMapCurrentWeatherResponse.Weather(1, "Main", "Description", "Icon")));
        when(weatherApiResponse.getMain()).thenReturn(mock(OpenWeatherMapCurrentWeatherResponse.Main.class));
        when(weatherApiResponse.getMain().getTemperature()).thenReturn(new BigDecimal("30.5"));
        CurrentWeatherCondition weatherCondition = weatherService.convertBean(weatherApiResponse);
        assertEquals("Icon", weatherCondition.getIconName());
        assertEquals(String.format(BeanUtils.ICON_URL, "Icon"), weatherCondition.getIcon());
        assertEquals(new BigDecimal("30.5"), weatherCondition.getTemperature());
    }

    @Test
    public void testGetCountryCode() {
        CurrentWeatherCondition currentWeatherCondition = mock(CurrentWeatherCondition.class);
        when(currentWeatherCondition.getSys()).thenReturn(mock(OpenWeatherMapCurrentWeatherResponse.Sys.class));
        when(currentWeatherCondition.getSys().getCountry()).thenReturn("AU");
        String countryName = weatherService.getCountryCode(currentWeatherCondition);
        assertEquals("AU", countryName);
    }

    @Test
    public void testSetCountryFullName() {
        String countryCode = "AU";
        String countryFullName = "Australia";
        CurrentWeatherCondition currentWeatherCondition = new CurrentWeatherCondition();
        currentWeatherCondition.setSys(new CurrentWeatherCondition.Sys());
        when(weatherService.getCountryCode(currentWeatherCondition)).thenReturn(countryCode);
        when(groupktCountryNameIntegration.getCountryFullName(countryCode)).thenReturn(countryFullName);
        CurrentWeatherCondition weatherConditionWithFullCountryName = weatherService.setCountryFullName(currentWeatherCondition);
        Mockito.verify(groupktCountryNameIntegration, times(1)).getCountryFullName(countryCode);
        assertEquals(countryFullName, weatherConditionWithFullCountryName.getSys().getCountryNameFull());
        assertEquals(countryFullName, weatherConditionWithFullCountryName.getCountry());
        assertEquals("v1.0", currentWeatherCondition.getApiVersion());
    }

    @Test
    public void testSetGeoLocation() {
        String fullGeoLocationAddress = "White House, 1600, Pennsylvania Avenue Northwest, Golden Triangle, Washington, "
                + "District of Columbia, 20500, United States of America";
        CurrentWeatherCondition weatherCondition = new CurrentWeatherCondition();
        weatherCondition.setCoordinates(new CurrentWeatherCondition.Coordinates("38.8977", "-77.0365"));
        when(openStreetMapIntegration.getAddressByCoordinates(weatherCondition.getCoordinates().getLatitude(),
                weatherCondition.getCoordinates().getLongitude())).thenReturn(fullGeoLocationAddress);
        CurrentWeatherCondition weatherConditionWithFullAddress = weatherService.setGeoLocation(weatherCondition);
        Mockito.verify(openStreetMapIntegration, times(1)).getAddressByCoordinates(anyString(), anyString());
        assertEquals(fullGeoLocationAddress, weatherConditionWithFullAddress.getGeoLocation());
        assertEquals("v1.0", weatherCondition.getApiVersion());
    }

    @Test
    public void testSetFeelsLike() {
        CurrentWeatherCondition currentWeather = new CurrentWeatherCondition();
        currentWeather.setWind(new CurrentWeatherCondition.Wind(new BigDecimal("5.45"), new BigDecimal("100.23")));
        CurrentWeatherCondition.Main weatherMain = new CurrentWeatherCondition.Main();
        weatherMain.setHumidity(new BigDecimal("75"));
        weatherMain.setTemperature(new BigDecimal("31.55"));
        currentWeather.setMain(weatherMain);
        when(feelsLikeService.getFeelsLike(weatherMain.getTemperature().doubleValue(), currentWeather.getWind().getSpeed().doubleValue()
                , weatherMain.getHumidity().doubleValue(), false)).thenReturn(37.123456);
        CurrentWeatherCondition weatherConditionWithFeelsLike = weatherService.setFeelsLike(currentWeather, false);
        Mockito.verify(feelsLikeService, times(1)).getFeelsLike(anyDouble(), anyDouble(), anyDouble(), anyBoolean());
        assertEquals(37.123456, weatherConditionWithFeelsLike.getFeelsLike(), 0.00001);
        assertEquals("v1.0", currentWeather.getApiVersion());
    }

    @Test
    public void testGetCurrentWeatherConditionByLatitudeAndLongitude() {
        String latitude = "37.987";
        String longitude = "20.22992";
        boolean isFahrenheit = false;
        OpenWeatherMapCurrentWeatherResponse response = new OpenWeatherMapCurrentWeatherResponse();
        response.setWeather(List.of(new OpenWeatherMapCurrentWeatherResponse.Weather()));
        response.setMain(new OpenWeatherMapCurrentWeatherResponse.Main());
        response.setWind(new OpenWeatherMapCurrentWeatherResponse.Wind());
        response.setSys(new OpenWeatherMapCurrentWeatherResponse.Sys());
        response.setCoordinates(new OpenWeatherMapCurrentWeatherResponse.Coordinates());
        when(openWeatherMapIntegration.getCurrentWeatherCondition(latitude, longitude, isFahrenheit))
                .thenReturn(response);
        weatherService.getCurrent(latitude, longitude, isFahrenheit);
        verify(openWeatherMapIntegration, times(1)).getCurrentWeatherCondition(latitude, longitude, isFahrenheit);
        verify(weatherService, times(1)).convertBean(isA(OpenWeatherMapCurrentWeatherResponse.class));
        verify(weatherService, times(1)).setFeelsLike(isA(CurrentWeatherCondition.class), anyBoolean());
        verify(weatherService, times(1)).setCountryFullName(isA(CurrentWeatherCondition.class));
        verify(weatherService, times(1)).setGeoLocation(isA(CurrentWeatherCondition.class));
    }

    private Coordinates stubCoordinates() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude("1.00");
        coordinates.setLongitude("2.00");
        return coordinates;
    }
}
