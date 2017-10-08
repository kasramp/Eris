package com.madadipouya.eris.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
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
* © 2017 Kasra Madadipouya <kasra@madadipouya.com>
*/

@RunWith(MockitoJUnitRunner.class)
public class PropertyUtilsTest {

    @Mock
    PropertyUtils propertyUtils;

    @Test
    public void testGetOpenWeatherMapApiKey() {
        when(propertyUtils.getOpenWeatherMapApiKey()).thenReturn("OpenWeatherMapKey");
        assertEquals("OpenWeatherMapKey", propertyUtils.getOpenWeatherMapApiKey());
    }

    @Test
    public void testGetSegmentIoWriteApiKey() {
        when(propertyUtils.getSegmentIoWriteApiKey()).thenReturn("SegmentIoApiKey");
        assertEquals("SegmentIoApiKey", propertyUtils.getSegmentIoWriteApiKey());
    }
}