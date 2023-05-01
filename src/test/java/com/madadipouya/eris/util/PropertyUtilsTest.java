package com.madadipouya.eris.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * © 2017-2023 Kasra Madadipouya <kasra@madadipouya.com>
 */

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = {"classpath:test.properties"})
@SpringBootTest
class PropertyUtilsTest {

    @Autowired
    private PropertyUtils propertyUtils;

    @Test
    void testGetOpenWeatherMapApiKey() {
        assertEquals("testKey", propertyUtils.getOpenWeatherMapApiKey());
    }

    @Test
    void testGetSegmentIoWriteApiKey() {
        assertEquals("testKey", propertyUtils.getSegmentIoWriteApiKey());
    }

    @Test
    void testGetActuatorUsername() {
        assertEquals("testUsername", propertyUtils.getActuatorUsername());
    }

    @Test
    void testGetActuatorPassword() {
        assertEquals("testPassword", propertyUtils.getActuatorPassword());
    }
}