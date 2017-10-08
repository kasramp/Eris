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
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class BeanUtilsTest {

    @Test
    public void testBeanUtilsHasPrivateConstructor() throws NoSuchMethodException {
        Constructor<BeanUtils> constructor = BeanUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    public void testCopyPropertiesForOpenWeather() {
        CurrentWeatherCondition target = new CurrentWeatherCondition();
        BeanUtils.copyProperties(stubOpenWeatherObject(), target);
        assertEquals("1", target.getCoordinates().getLatitude());
        assertEquals("2", target.getCoordinates().getLongitude());
        assertEquals(1, target.getWeather().size());
        assertEquals(new Integer(10), target.getWeather().get(0).getId());
        assertEquals("Main", target.getWeather().get(0).getMain());
        assertEquals("Description", target.getWeather().get(0).getDescription());
        assertEquals("Icon", target.getWeather().get(0).getIcon());
        assertEquals("Base", target.getBase());
        assertEquals(new BigDecimal(10), target.getMain().getGroundLevel());
        assertEquals(new BigDecimal(60), target.getMain().getHumidity());
        assertEquals(new BigDecimal(700), target.getMain().getPressure());
        assertEquals(new BigDecimal(500), target.getMain().getSeaLevel());
        assertEquals(new BigDecimal(29.5), target.getMain().getTemperature());
        assertEquals(new BigDecimal(31.12), target.getMain().getTemperatureMax());
        assertEquals(new BigDecimal(27.56), target.getMain().getTemperatureMin());
        assertEquals(1000.000, target.getVisibility(), 0.001);
        assertEquals(new BigDecimal(4.5), target.getWind().getSpeed());
        assertEquals(new BigDecimal(2.7), target.getWind().getDegree());
        assertEquals(new BigDecimal(30), target.getClouds().getAll());
        assertEquals("MY", target.getSys().getCountry());
        assertEquals("Malaysia", target.getSys().getCountryNameFull());
        assertEquals(new BigDecimal(60), target.getSys().getId());
        assertEquals(new BigDecimal(-1), target.getSys().getMessage());
        assertEquals(new BigDecimal(60), target.getSys().getSunrise());
        assertEquals(new BigDecimal(70), target.getSys().getSunset());
        assertEquals(new BigDecimal(0), target.getSys().getType());
        assertEquals(String.format(BeanUtils.ICON_URL, target.getWeather().get(0).getIcon()), target.getIcon());
        assertEquals(target.getWeather().get(0).getIcon(), target.getIconName());
        assertEquals(target.getMain().getTemperature(), target.getTemperature());
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