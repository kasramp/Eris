package com.madadipouya.eris.service.mcp.impl;

import com.madadipouya.eris.service.mcp.exception.InvalidArgumentException;
import com.madadipouya.eris.service.weather.Weather;
import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
 * © 2026 Kasra Madadipouya <kasra@madadipouya.com>
 */

@ExtendWith(MockitoExtension.class)
class McpToolsTest {

  @InjectMocks
  private DefaultMcpTools mcpTools;

  @Mock
  private Weather weather;

  @ParameterizedTest
  @CsvSource(value = {
      "'', ''",
      "'     ', null",
      "null, null",
      "'', '    '",
      "10, ''",
      "10, '  '",
      "10, null",
      "'', 179",
      "'   ', 10",
      "null, 120"
  }, nullValues = "null")
  void testGetWeatherThrowsErrorWhenLatitudeOrLongitudeIsNullOrBlank(String latitude, String longitude) {
    assertThrows(InvalidArgumentException.class, () -> mcpTools.getWeatherByLatitudeAndLongitude(latitude, longitude, false));
  }

  @ParameterizedTest
  @CsvSource(value = {
      "'', abc",
      "1@3, 10,11",
      "10'1, 120"
  }, nullValues = "null")
  void testGetWeatherThrowsErrorWhenLatitudeOrLongitudeIsNotNumber(String latitude, String longitude) {
    assertThrows(InvalidArgumentException.class, () -> mcpTools.getWeatherByLatitudeAndLongitude(latitude, longitude, false));
  }

  @ParameterizedTest
  @CsvSource(value = {
      "95, 200",
      "95.1, 180.2",
      "-95, -200",
      "80, 180.2",
      "-80, -180.2",
      "-95, -180",
      "90.1, 179.9"
  }, nullValues = "null")
  void testGetWeatherThrowsErrorWhenLatitudeOrLongitudeIsOutOfRange(String latitude, String longitude) {
    assertThrows(InvalidArgumentException.class, () -> mcpTools.getWeatherByLatitudeAndLongitude(latitude, longitude, false));
  }

  @Test
  void testGetWeatherByLatitudeAndLongitude() throws InvalidArgumentException {
    CurrentWeatherCondition expected = mock(CurrentWeatherCondition.class);
    when(weather.getCurrent("10", "180", false)).thenReturn(expected);

    CurrentWeatherCondition weatherCondition = mcpTools.getWeatherByLatitudeAndLongitude("10", "180", false);

    assertEquals(expected, weatherCondition);
  }

  @ParameterizedTest
  @CsvSource(value = {
      "''",
      "'     '",
      "null"
  }, nullValues = "null")
  void testGetWeatherThrowsErrorWhenIpAddressIsNullOrBlank(String ipAddress) {
    assertThrows(InvalidArgumentException.class, () -> mcpTools.getWeatherByIpAddress(ipAddress, false));
  }

  @Test
  void testGetWeatherByIpAddress() throws InvalidArgumentException {
    CurrentWeatherCondition expected = mock(CurrentWeatherCondition.class);
    when(weather.getCurrent("192.168.0.1", false)).thenReturn(expected);

    CurrentWeatherCondition weatherCondition = mcpTools.getWeatherByIpAddress("192.168.0.1", false);

    assertEquals(expected, weatherCondition);
  }
}