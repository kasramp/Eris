package com.madadipouya.eris.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
* © 2017-2018 Kasra Madadipouya <kasra@madadipouya.com>
*/

@ExtendWith(MockitoExtension.class)
public class PropertyUtilsTest {

    @Spy
    private PropertyUtils propertyUtils;

    @Test
    public void testGetOpenWeatherMapApiKey() {
        when(propertyUtils.getOpenWeatherMapApiKey()).thenReturn("OpenWeatherMapKey");
        assertEquals("OpenWeatherMapKey", propertyUtils.getOpenWeatherMapApiKey());
    }

    @Test
    public void testSetOpenWeatherMapApiKey() {
        propertyUtils.setOpenWeatherMapApiKey("OpenWeatherMapKey");
        assertEquals("OpenWeatherMapKey", propertyUtils.getOpenWeatherMapApiKey());
    }

    @Test
    public void testGetSegmentIoWriteApiKey() {
        when(propertyUtils.getSegmentIoWriteApiKey()).thenReturn("SegmentIoApiKey");
        assertEquals("SegmentIoApiKey", propertyUtils.getSegmentIoWriteApiKey());
    }

    @Test
    public void testSetSegmentIoWriteApiKey() {
        propertyUtils.setSegmentIoWriteApiKey("SegmentIoApiKey");
        assertEquals("SegmentIoApiKey", propertyUtils.getSegmentIoWriteApiKey());
    }
}