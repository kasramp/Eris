package com.madadipouya.eris.rest.error;

import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

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
* © 2017-2022 Kasra Madadipouya <kasra@madadipouya.com>
*/

@ExtendWith(MockitoExtension.class)
class GlobalControllerExceptionHandlerTest {

    @Spy
    @InjectMocks
    private GlobalControllerExceptionHandler exceptionHandler;

    @Test
    void testUnknownException() {
        ResponseEntity<CurrentWeatherCondition> response = exceptionHandler.unknownException(mock(RuntimeException.class));
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getErrors());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("Unable to process the request!", response.getBody().getErrors().get(0));
    }
}