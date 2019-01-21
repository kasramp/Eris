package com.madadipouya.eris.integration.openweathermap.remote.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

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
* © 2017-2019 Kasra Madadipouya <kasra@madadipouya.com>
*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherMapCurrentWeatherResponse {

    @JsonProperty("coord")
    private Coordinates coordinates;

    @JsonProperty("weather")
    private List<Weather> weather;

    @JsonProperty("base")
    private String base;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("visibility")
    private double visibility;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("dt")
    private BigDecimal dt;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("id")
    private BigDecimal id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cod")
    private BigDecimal cod;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public BigDecimal getDt() {
        return dt;
    }

    public void setDt(BigDecimal dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCod() {
        return cod;
    }

    public void setCod(BigDecimal cod) {
        this.cod = cod;
    }


    public static class Coordinates {
        public Coordinates() {
            this("0", "0");
        }

        public Coordinates(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @JsonProperty("lat")
        private String latitude;

        @JsonProperty("lon")
        private String longitude;

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }

    public static class Weather {
        public Weather() {
            this(0, "", "", "");
        }

        public Weather(Integer id, String main, String description, String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("main")
        private String main;

        @JsonProperty("description")
        private String description;

        @JsonProperty("icon")
        private String icon;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class Main {

        public Main() {
            temperature = new BigDecimal(0);
            pressure = new BigDecimal(0);
            humidity = new BigDecimal(0);
            temperatureMin = new BigDecimal(0);
            temperatureMax = new BigDecimal(0);
            seaLevel = new BigDecimal(0);
            groundLevel = new BigDecimal(0);
        }

        @JsonProperty("temp")
        private BigDecimal temperature;

        @JsonProperty("pressure")
        private BigDecimal pressure;

        @JsonProperty("humidity")
        private BigDecimal humidity;

        @JsonProperty("temp_min")
        private BigDecimal temperatureMin;

        @JsonProperty("temp_max")
        private BigDecimal temperatureMax;

        @JsonProperty("sea_level")
        private BigDecimal seaLevel;

        @JsonProperty("grnd_level")
        private BigDecimal groundLevel;

        public BigDecimal getTemperature() {
            return temperature;
        }

        public void setTemperature(BigDecimal temperature) {
            this.temperature = temperature;
        }

        public BigDecimal getPressure() {
            return pressure;
        }

        public void setPressure(BigDecimal pressure) {
            this.pressure = pressure;
        }

        public BigDecimal getHumidity() {
            return humidity;
        }

        public void setHumidity(BigDecimal humidity) {
            this.humidity = humidity;
        }

        public BigDecimal getTemperatureMin() {
            return temperatureMin;
        }

        public void setTemperatureMin(BigDecimal temperatureMin) {
            this.temperatureMin = temperatureMin;
        }

        public BigDecimal getTemperatureMax() {
            return temperatureMax;
        }

        public void setTemperatureMax(BigDecimal temperatureMax) {
            this.temperatureMax = temperatureMax;
        }

        public BigDecimal getSeaLevel() {
            return seaLevel;
        }

        public void setSeaLevel(BigDecimal seaLevel) {
            this.seaLevel = seaLevel;
        }

        public BigDecimal getGroundLevel() {
            return groundLevel;
        }

        public void setGroundLevel(BigDecimal groundLevel) {
            this.groundLevel = groundLevel;
        }
    }

    public static class Wind {

        public Wind() {
            this(new BigDecimal(0), new BigDecimal(0));
        }

        public Wind(BigDecimal speed, BigDecimal degree) {
            this.speed = speed;
            this.degree = degree;
        }

        @JsonProperty("speed")
        private BigDecimal speed;

        @JsonProperty("deg")
        private BigDecimal degree;

        public BigDecimal getSpeed() {
            return speed;
        }

        public void setSpeed(BigDecimal speed) {
            this.speed = speed;
        }

        public BigDecimal getDegree() {
            return degree;
        }

        public void setDegree(BigDecimal degree) {
            this.degree = degree;
        }
    }

    public static class Clouds {
        public Clouds() {
            this(new BigDecimal(0));
        }

        public Clouds(BigDecimal all) {
            this.all = all;
        }

        @JsonProperty("all")
        private BigDecimal all;

        public BigDecimal getAll() {
            return all;
        }

        public void setAll(BigDecimal all) {
            this.all = all;
        }
    }

    public static class Sys {

        public Sys() {
            type = new BigDecimal(0);
            id = new BigDecimal(0);
            message = new BigDecimal(0);
            country = "";
            countryNameFull = "";
            sunrise = new BigDecimal(0);
            sunset = new BigDecimal(0);
        }

        @JsonProperty("type")
        private BigDecimal type;

        @JsonProperty("id")
        private BigDecimal id;

        @JsonProperty("message")
        private BigDecimal message;

        @JsonProperty("country")
        private String country;

        private String countryNameFull;

        @JsonProperty("sunrise")
        private BigDecimal sunrise;

        @JsonProperty("sunset")
        private BigDecimal sunset;

        public BigDecimal getType() {
            return type;
        }

        public void setType(BigDecimal type) {
            this.type = type;
        }

        public BigDecimal getId() {
            return id;
        }

        public void setId(BigDecimal id) {
            this.id = id;
        }

        public BigDecimal getMessage() {
            return message;
        }

        public void setMessage(BigDecimal message) {
            this.message = message;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountryNameFull() {
            return countryNameFull;
        }

        public void setCountryNameFull(String countryNameFull) {
            this.countryNameFull = countryNameFull;
        }

        public BigDecimal getSunrise() {
            return sunrise;
        }

        public void setSunrise(BigDecimal sunrise) {
            this.sunrise = sunrise;
        }

        public BigDecimal getSunset() {
            return sunset;
        }

        public void setSunset(BigDecimal sunset) {
            this.sunset = sunset;
        }
    }
}
