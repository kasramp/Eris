package com.madadipouya.eris.util;

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

import com.google.common.collect.ImmutableList;
import com.madadipouya.eris.integration.ipapi.remote.response.IpApiResponse;
import com.madadipouya.eris.integration.openweathermap.remote.response.OpenWeatherMapCurrentWeatherResponse;
import com.madadipouya.eris.service.ipgeolocation.model.Coordinates;
import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class BeanUtilsTest {

    @Test
    public void testCopyPropertiesForOpenWeather() {
        CurrentWeatherCondition target = new CurrentWeatherCondition();
        BeanUtils.copyProperties(stubOpenWeatherObject(), target);
        assertEquals(target.getCoordinates().getLatitude(), "1");
        assertEquals(target.getCoordinates().getLongitude(), "2");
        assertEquals(target.getWeather().size(), 1);
        assertEquals(target.getWeather().get(0).getId(), new Integer(10));
        assertEquals(target.getWeather().get(0).getMain(), "Main");
        assertEquals(target.getWeather().get(0).getDescription(), "Description");
        assertEquals(target.getWeather().get(0).getIcon(), "Icon");
        assertEquals(target.getBase(), "Base");
        assertEquals(target.getMain().getGroundLevel(), new BigDecimal(10));
        assertEquals(target.getMain().getHumidity(), new BigDecimal(60));
        assertEquals(target.getMain().getPressure(), new BigDecimal(700));
        assertEquals(target.getMain().getSeaLevel(), new BigDecimal(500));
        assertEquals(target.getMain().getTemperature(), new BigDecimal(29.5));
        assertEquals(target.getMain().getTemperatureMax(), new BigDecimal(31.12));
        assertEquals(target.getMain().getTemperatureMin(), new BigDecimal(27.56));
        assertEquals(target.getVisibility(), 1000, 0.001);
        assertEquals(target.getWind().getSpeed(), new BigDecimal(4.5));
        assertEquals(target.getWind().getDegree(), new BigDecimal(2.7));
        assertEquals(target.getClouds().getAll(), new BigDecimal(30));
        assertEquals(target.getSys().getCountry(), "MY");
        assertEquals(target.getSys().getCountryNameFull(), "Malaysia");
        assertEquals(target.getSys().getId(), new BigDecimal(60));
        assertEquals(target.getSys().getMessage(), new BigDecimal(-1));
        assertEquals(target.getSys().getSunrise(), new BigDecimal(60));
        assertEquals(target.getSys().getSunset(), new BigDecimal(70));
        assertEquals(target.getSys().getType(), new BigDecimal(0));
        assertEquals(target.getIcon(), String.format(BeanUtils.ICON_URL, target.getWeather().get(0).getIcon()));
        assertEquals(target.getIconName(), target.getWeather().get(0).getIcon());
        assertEquals(target.getTemperature(), target.getMain().getTemperature());
    }

    @Test
    public void testCopyPropertiesForCoordinateObject() {
        Coordinates target = new Coordinates();
        IpApiResponse source = new IpApiResponse();
        source.setLatitude("10.11");
        source.setLongitude("12.222");
        BeanUtils.copyProperties(source, target);
        assertEquals(target.getLatitude(), source.getLatitude());
        assertEquals(target.getLongitude(), source.getLongitude());
    }

    private OpenWeatherMapCurrentWeatherResponse stubOpenWeatherObject() {
        OpenWeatherMapCurrentWeatherResponse weatherObject =
                new OpenWeatherMapCurrentWeatherResponse();
        weatherObject.setCoordinates(stubCoordinates("1", "2"));
        weatherObject.setWeather(ImmutableList.of(stubWeather(10, "Main", "Description", "Icon")));
        weatherObject.setBase("Base");
        weatherObject.setMain(stubMain());
        weatherObject.setVisibility(1000);
        weatherObject.setWind(stubWind());
        weatherObject.setClouds(stubCloud());
        weatherObject.setDt(new BigDecimal(100));
        weatherObject.setSys(stubSys());
        weatherObject.setId(new BigDecimal(1));
        weatherObject.setName("Name");
        weatherObject.setCod(new BigDecimal(10));
        return weatherObject;
    }

    private OpenWeatherMapCurrentWeatherResponse.Coordinates stubCoordinates(String latitude, String longitude) {
        return new OpenWeatherMapCurrentWeatherResponse.Coordinates(latitude, longitude);
    }

    public OpenWeatherMapCurrentWeatherResponse.Weather stubWeather(Integer id, String main, String description, String icon) {
        return new OpenWeatherMapCurrentWeatherResponse.Weather(id, main, description, icon);
    }

    public OpenWeatherMapCurrentWeatherResponse.Main stubMain() {
        OpenWeatherMapCurrentWeatherResponse.Main main = new OpenWeatherMapCurrentWeatherResponse.Main();
        main.setGroundLevel(new BigDecimal(10));
        main.setHumidity(new BigDecimal(60));
        main.setPressure(new BigDecimal(700));
        main.setSeaLevel(new BigDecimal(500));
        main.setTemperature(new BigDecimal(29.5));
        main.setTemperatureMax(new BigDecimal(31.12));
        main.setTemperatureMin(new BigDecimal(27.56));
        return main;
    }

    public OpenWeatherMapCurrentWeatherResponse.Wind stubWind() {
        return new OpenWeatherMapCurrentWeatherResponse.Wind(new BigDecimal(4.5), new BigDecimal(2.7));
    }

    public OpenWeatherMapCurrentWeatherResponse.Clouds stubCloud() {
        return new OpenWeatherMapCurrentWeatherResponse.Clouds(new BigDecimal(30));
    }

    public OpenWeatherMapCurrentWeatherResponse.Sys stubSys() {
        OpenWeatherMapCurrentWeatherResponse.Sys sys = new OpenWeatherMapCurrentWeatherResponse.Sys();
        sys.setCountry("MY");
        sys.setCountryNameFull("Malaysia");
        sys.setId(new BigDecimal(60));
        sys.setMessage(new BigDecimal(-1));
        sys.setSunrise(new BigDecimal(60));
        sys.setSunset(new BigDecimal(70));
        sys.setType(new BigDecimal(0));
        return sys;
    }
}