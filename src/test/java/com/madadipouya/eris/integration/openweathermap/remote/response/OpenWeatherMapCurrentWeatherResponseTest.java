package com.madadipouya.eris.integration.openweathermap.remote.response;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

public class OpenWeatherMapCurrentWeatherResponseTest {

    @Test
    public void testOpenWeatherMapCurrentWeatherResponse() {
        OpenWeatherMapCurrentWeatherResponse weatherResponse = new OpenWeatherMapCurrentWeatherResponse();
        weatherResponse.setCoordinates(mockCoordinates());
        weatherResponse.setWeather(List.of(mockWeather(), mockWeather()));
        weatherResponse.setBase("Base");
        weatherResponse.setMain(mockMain());
        weatherResponse.setVisibility(1000);
        weatherResponse.setWind(mockWind());
        weatherResponse.setClouds(mockClouds());
        weatherResponse.setDt(new BigDecimal("10.1"));
        weatherResponse.setSys(mockSys());
        weatherResponse.setId(new BigDecimal(10));
        weatherResponse.setName("Weather Condition");
        weatherResponse.setCod(new BigDecimal("0.5"));
        assertNotNull(weatherResponse.getCoordinates());
        assertNotNull(weatherResponse.getWeather());
        assertEquals(2, weatherResponse.getWeather().size());
        assertEquals("Base", weatherResponse.getBase());
        assertNotNull(weatherResponse.getMain());
        assertEquals(1000, weatherResponse.getVisibility(), 0.001);
        assertNotNull(weatherResponse.getClouds());
        assertEquals("10.1", weatherResponse.getDt().toString());
        assertNotNull(weatherResponse.getSys());
        assertEquals("10", weatherResponse.getId().toString());
        assertEquals("Weather Condition", weatherResponse.getName());
        assertEquals("0.5", weatherResponse.getCod().toString());
    }

    @Test
    public void testSys() {
        OpenWeatherMapCurrentWeatherResponse.Sys sys = mockSys();
        assertNotNull(sys);
        assertEquals("0", sys.getType().toString());
        assertEquals("10", sys.getId().toString());
        assertEquals("0", sys.getMessage().toString());
        assertEquals("Singapore", sys.getCountry());
        assertEquals("Singapore", sys.getCountryNameFull());
        assertEquals("6.10", sys.getSunrise().toString());
        assertEquals("6.30", sys.getSunset().toString());
    }

    @Test
    public void testClouds() {
        OpenWeatherMapCurrentWeatherResponse.Clouds clouds = mockClouds();
        assertNotNull(clouds);
        assertEquals("10", clouds.getAll().toString());
    }

    @Test
    public void testWind() {
        OpenWeatherMapCurrentWeatherResponse.Wind wind = mockWind();
        assertNotNull(wind);
        assertEquals("10", wind.getSpeed().toString());
        assertEquals("60", wind.getDegree().toString());
    }

    @Test
    public void testMain() {
        OpenWeatherMapCurrentWeatherResponse.Main main = mockMain();
        assertNotNull(main);
        assertEquals("27.50", main.getTemperature().toString());
        assertEquals("20", main.getPressure().toString());
        assertEquals("70", main.getHumidity().toString());
        assertEquals("27", main.getTemperatureMin().toString());
        assertEquals("28", main.getTemperatureMax().toString());
        assertEquals("100", main.getSeaLevel().toString());
        assertEquals("50", main.getGroundLevel().toString());
    }

    @Test
    public void testWeather() {
        OpenWeatherMapCurrentWeatherResponse.Weather weather = mockWeather();
        assertNotNull(weather);
        assertEquals(new Integer(0), weather.getId());
        assertEquals("Main", weather.getMain());
        assertEquals("Description", weather.getDescription());
        assertEquals("Icon", weather.getIcon());
    }

    @Test
    public void testCoordinates() {
        OpenWeatherMapCurrentWeatherResponse.Coordinates coordinates = mockCoordinates();
        assertNotNull(coordinates);
        assertEquals("1.00", coordinates.getLatitude());
        assertEquals("2.00", coordinates.getLongitude());
    }

    private OpenWeatherMapCurrentWeatherResponse.Sys mockSys() {
        OpenWeatherMapCurrentWeatherResponse.Sys sys = new OpenWeatherMapCurrentWeatherResponse.Sys();
        sys.setType(new BigDecimal("0"));
        sys.setId(new BigDecimal("10"));
        sys.setMessage(new BigDecimal("0"));
        sys.setCountry("Singapore");
        sys.setCountryNameFull("Singapore");
        sys.setSunrise(new BigDecimal("6.10"));
        sys.setSunset(new BigDecimal("6.30"));
        return sys;
    }

    private OpenWeatherMapCurrentWeatherResponse.Clouds mockClouds() {
        OpenWeatherMapCurrentWeatherResponse.Clouds clouds = new OpenWeatherMapCurrentWeatherResponse.Clouds();
        clouds.setAll(new BigDecimal("10"));
        return clouds;
    }

    private OpenWeatherMapCurrentWeatherResponse.Wind mockWind() {
        OpenWeatherMapCurrentWeatherResponse.Wind wind = new OpenWeatherMapCurrentWeatherResponse.Wind();
        wind.setSpeed(new BigDecimal("10"));
        wind.setDegree(new BigDecimal("60"));
        return wind;
    }

    private OpenWeatherMapCurrentWeatherResponse.Main mockMain() {
        OpenWeatherMapCurrentWeatherResponse.Main main = new OpenWeatherMapCurrentWeatherResponse.Main();
        main.setTemperature(new BigDecimal("27.50"));
        main.setPressure(new BigDecimal("20"));
        main.setHumidity(new BigDecimal("70"));
        main.setTemperatureMin(new BigDecimal("27"));
        main.setTemperatureMax(new BigDecimal("28"));
        main.setSeaLevel(new BigDecimal("100"));
        main.setGroundLevel(new BigDecimal("50"));
        return main;
    }

    private OpenWeatherMapCurrentWeatherResponse.Weather mockWeather() {
        OpenWeatherMapCurrentWeatherResponse.Weather weather = new OpenWeatherMapCurrentWeatherResponse.Weather();
        weather.setId(0);
        weather.setMain("Main");
        weather.setDescription("Description");
        weather.setIcon("Icon");
        return weather;
    }

    private OpenWeatherMapCurrentWeatherResponse.Coordinates mockCoordinates() {
        OpenWeatherMapCurrentWeatherResponse.Coordinates coordinates = new OpenWeatherMapCurrentWeatherResponse.Coordinates();
        coordinates.setLatitude("1.00");
        coordinates.setLongitude("2.00");
        return coordinates;
    }
}
