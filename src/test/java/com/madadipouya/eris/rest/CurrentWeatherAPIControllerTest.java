package com.madadipouya.eris.rest;

import com.madadipouya.eris.service.weather.impl.DefaultWeather;
import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
 * © 2017-2019 Kasra Madadipouya <kasra@madadipouya.com>
 */

@ExtendWith(MockitoExtension.class)
class CurrentWeatherAPIControllerTest {

    @Spy
    @InjectMocks
    private CurrentWeatherAPIController controller;

    @Mock
    private DefaultWeather weatherService;

    @Test
    void testGetCurrentWhenLatitudeLongitudeIsNull() throws Exception {
        ResponseEntity<CurrentWeatherCondition> response = controller.getCurrent(null, null, false, mock(HttpServletRequest.class)).call();

        verify(weatherService, never()).getCurrent(anyString(), anyString(), anyBoolean());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getErrors());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("No latitude and/or longitude provided!", response.getBody().getErrors().get(0));

        response = controller.getCurrent("1.00", " ", false, mock(HttpServletRequest.class)).call();
        verify(weatherService, never()).getCurrent(anyString(), anyString(), anyBoolean());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getErrors());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("No latitude and/or longitude provided!", response.getBody().getErrors().get(0));

        response = controller.getCurrent(" ", "2.00", false, mock(HttpServletRequest.class)).call();
        verify(weatherService, never()).getCurrent(anyString(), anyString(), anyBoolean());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getErrors());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("No latitude and/or longitude provided!", response.getBody().getErrors().get(0));

        response = controller.getCurrent(" ", " ", false, mock(HttpServletRequest.class)).call();
        verify(weatherService, never()).getCurrent(anyString(), anyString(), anyBoolean());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getErrors());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("No latitude and/or longitude provided!", response.getBody().getErrors().get(0));
    }

    @Test
    void testGetCurrentWhenLatitudeLongitudeIsNotNumber() throws Exception {
        ResponseEntity<CurrentWeatherCondition> response = controller.getCurrent("1.00", "2.00ABC", false, mock(HttpServletRequest.class)).call();
        verify(weatherService, never()).getCurrent(anyString(), anyString(), anyBoolean());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getErrors());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("Invalid latitude and/or longitude provided!", response.getBody().getErrors().get(0));

        response = controller.getCurrent("1DEF.00", "2.00", false, mock(HttpServletRequest.class)).call();
        verify(weatherService, never()).getCurrent(anyString(), anyString(), anyBoolean());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getErrors());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("Invalid latitude and/or longitude provided!", response.getBody().getErrors().get(0));

        response = controller.getCurrent("DEF", "ABC1", false, mock(HttpServletRequest.class)).call();
        verify(weatherService, never()).getCurrent(anyString(), anyString(), anyBoolean());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getErrors());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("Invalid latitude and/or longitude provided!", response.getBody().getErrors().get(0));
    }

    @Test
    void testGetCurrent() throws Exception {
        when(weatherService.getCurrent("1.00", "2.00", false)).thenReturn(mock(CurrentWeatherCondition.class));
        ResponseEntity<CurrentWeatherCondition> response = controller.getCurrent("1.00", "2.00", false, mock(HttpServletRequest.class)).call();
        verify(weatherService, times(1)).getCurrent("1.00", "2.00", false);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().getErrors().size());
    }

    @Test
    void testGetCurrentByIpAddress() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(weatherService.getCurrent(request, false)).thenReturn(mock(CurrentWeatherCondition.class));
        ResponseEntity<CurrentWeatherCondition> response = controller.getCurrentByIp(false, request).call();
        verify(weatherService, times(1)).getCurrent(request, false);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().getErrors().size());
    }
}
