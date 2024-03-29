package com.madadipouya.eris.integration.openstreetmap;

import com.madadipouya.eris.integration.openstreetmap.remote.response.OpenStreetMapLocationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
* © 2017-2022 Kasra Madadipouya <kasra@madadipouya.com>
*/

@ExtendWith(MockitoExtension.class)
class DefaultOpenStreetMapIntegrationTest {

    @Spy
    @InjectMocks
    private DefaultOpenStreetMapIntegration openStreetMapIntegration;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void testGetAddressByCoordinates() {
        String whiteHouseAddress = "White House, 1600, Pennsylvania Avenue Northwest, Golden Triangle, Washington, District of Columbia, 20500, United States of America";
        OpenStreetMapLocationResponse mockResult = mock(OpenStreetMapLocationResponse.class);
        ResponseEntity<OpenStreetMapLocationResponse> responseEntity = mock(ResponseEntity.class);
        when(mockResult.getDisplayName()).thenReturn(whiteHouseAddress);
        when(restTemplate.exchange(
                ArgumentMatchers.eq("https://nominatim.openstreetmap.org/reverse?format=json&lat=38.8977&lon=-77.0365&zoom=18&addressdetails=1"),
                ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.<HttpEntity<List<Object>>>any(),
                ArgumentMatchers.<Class<OpenStreetMapLocationResponse>>any()
        )).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(mockResult);
        String result = openStreetMapIntegration.getAddressByCoordinates("38.8977", "-77.0365");
        verify(openStreetMapIntegration, times(1)).getReverseGeocoding("38.8977", "-77.0365");
        assertEquals(whiteHouseAddress, result);
    }

    @Test
    void testGetReverseGeocoding() {
        String whiteHouseAddress = "White House, 1600, Pennsylvania Avenue Northwest, Golden Triangle, Washington, District of Columbia, 20500, United States of America";
        OpenStreetMapLocationResponse mockResult = mock(OpenStreetMapLocationResponse.class);
        ResponseEntity<OpenStreetMapLocationResponse> responseEntity = mock(ResponseEntity.class);
        when(mockResult.getDisplayName()).thenReturn(whiteHouseAddress);
        when(restTemplate.exchange(
                ArgumentMatchers.eq("https://nominatim.openstreetmap.org/reverse?format=json&lat=38.8977&lon=-77.0365&zoom=18&addressdetails=1"),
                ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.<HttpEntity<List<Object>>>any(),
                ArgumentMatchers.<Class<OpenStreetMapLocationResponse>>any()
        )).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(mockResult);
        OpenStreetMapLocationResponse result = openStreetMapIntegration.getReverseGeocoding("38.8977", "-77.0365");
        assertEquals(whiteHouseAddress, result.getDisplayName());
    }
}
