package com.madadipouya.eris.service.mcp.impl;

import com.madadipouya.eris.service.mcp.McpTools;
import com.madadipouya.eris.service.weather.Weather;
import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import org.apache.commons.lang3.StringUtils;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

@Service("mcpTools")
public class DefaultMcpTools implements McpTools {
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String LATITUDE_LONGITUDE_CAN_NOT_BE_BLANK = "Latitude and longitude cannot be null or blank";
    private static final String LATITUDE_OUT_OF_RANGE_ERROR = "Latitude must be between -90 and 90";
    private static final String LONGITUDE_OUT_OF_RANGE_ERROR = "Longitude must be between -180 and 180";
    private static final String MUST_BE_NUMBER_ERROR_TEMPLATE = "%s must be a valid number";

    private final Weather weather;

    public DefaultMcpTools(Weather weather) {
        this.weather = weather;
    }

    @McpTool(name = "get_current_weather_by_latitude_and_longitude", description = "Get the current weather condition by latitude and longitude")
    @Override
    public CurrentWeatherCondition getWeatherByLatitudeAndLongitude(
            @McpToolParam(description = "Latitude of a location in string format") String latitude,
            @McpToolParam(description = "Longitude of a location in string format") String longitude,
            @McpToolParam(description = "Unit of measurement of weather. True means Imperial (Fahrenheit), False means Metric (Celsius). Defaults to Metric when omitted.", required = false) Boolean displayInFahrenheit) {
        validateLatitudeAndLongitude(latitude, longitude);
        return weather.getCurrent(latitude, longitude, Boolean.TRUE.equals(displayInFahrenheit));
    }

    @McpTool(name = "get_current_weather_by_ip_address", description = "Get the current weather condition by IP address")
    @Override
    public CurrentWeatherCondition getWeatherByIpAddress(
            @McpToolParam(description = "IP Address of a user to translate to geolocation and then calculate weather of a location") String ipAddress,
            @McpToolParam(description = "Unit of measurement of weather. True means Imperial (Fahrenheit), False means Metric (Celsius). Defaults to Metric when omitted.", required = false) Boolean displayInFahrenheit) {
        if (StringUtils.isBlank(ipAddress)) {
            throw new IllegalArgumentException("IP Address cannot be null or blank");
        }
        return weather.getCurrent(ipAddress, Boolean.TRUE.equals(displayInFahrenheit));
    }

    private void validateLatitudeAndLongitude(String latitude, String longitude) {
        if (StringUtils.isBlank(latitude) || StringUtils.isBlank(longitude)) {
            throw new IllegalArgumentException(LATITUDE_LONGITUDE_CAN_NOT_BE_BLANK);
        }
        double lat = parseCoordinate(latitude, LATITUDE);
        double lon = parseCoordinate(longitude, LONGITUDE);
        if (lat < -90 || lat > 90) {
            throw new IllegalArgumentException(LATITUDE_OUT_OF_RANGE_ERROR);
        }
        if (lon < -180 || lon > 180) {
            throw new IllegalArgumentException(LONGITUDE_OUT_OF_RANGE_ERROR);
        }
    }

    private double parseCoordinate(String value, String name) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MUST_BE_NUMBER_ERROR_TEMPLATE.formatted(name));
        }
    }
    // TODO instead of IllegalArgumentException throw a custom exception
}