package com.madadipouya.eris.integration.openstreetmap;

import com.madadipouya.eris.integration.openstreetmap.remote.response.OpenStreetMapLocationResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

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
public class DefaultOpenStreetMapIntegrationTest {

    @Spy
    @InjectMocks
    private DefaultOpenStreetMapIntegration openStreetMapIntegration;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetAddressByCoordinates() {
        String whiteHouseAddress = "White House, 1600, Pennsylvania Avenue Northwest, Golden Triangle, Washington, District of Columbia, 20500, United States of America";
        OpenStreetMapLocationResponse mockResult = mock(OpenStreetMapLocationResponse.class);
        when(mockResult.getDisplayName()).thenReturn(whiteHouseAddress);
        when(openStreetMapIntegration.getReverseGeocoding("38.8977", "-77.0365")).thenReturn(mockResult);
        String result = openStreetMapIntegration.getAddressByCoordinates("38.8977", "-77.0365");
        assertEquals(whiteHouseAddress, result);
    }

    @Test
    public void testGetReverseGeocoding() {
        String whiteHouseAddress = "White House, 1600, Pennsylvania Avenue Northwest, Golden Triangle, Washington, District of Columbia, 20500, United States of America";
        OpenStreetMapLocationResponse mockResult = mock(OpenStreetMapLocationResponse.class);
        when(mockResult.getDisplayName()).thenReturn(whiteHouseAddress);
        when(restTemplate.getForObject("http://nominatim.openstreetmap.org/reverse?format=json&lat=38.8977&lon=-77.0365&zoom=18&addressdetails=1", OpenStreetMapLocationResponse.class)).thenReturn(mockResult);
        OpenStreetMapLocationResponse result = openStreetMapIntegration.getReverseGeocoding("38.8977", "-77.0365");
        verify(restTemplate, times(1)).getForObject(anyString(), any());
        assertEquals(whiteHouseAddress, result.getDisplayName());
    }
}
