package com.madadipouya.eris.integration.groupkt;


import com.madadipouya.eris.integration.groupkt.remote.response.GroupktCountryNameResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
public class DefaultGroupktCountryNameIntegrationTest {

    @Spy
    @InjectMocks
    private DefaultGroupktCountryNameIntegration groupktIntegration;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetCountryFullName() {
        GroupktCountryNameResponse response = new GroupktCountryNameResponse();
        response.getRestResponse().setResult(new GroupktCountryNameResponse.Result("Germany", "DE", "DEU"));
        when(restTemplate.getForObject("http://services.groupkt.com/country/get/iso2code/DE", GroupktCountryNameResponse.class)).thenReturn(response);
        String countryFullName = groupktIntegration.getCountryFullName("DE");
        verify(restTemplate, times(1)).getForObject(anyString(), any());
        assertNotNull(countryFullName);
        assertEquals("Germany", countryFullName);
    }

    @Test
    public void testGetCountryFullNameWhenCountryCodeIsEmpty() {
        String countryFullName = groupktIntegration.getCountryFullName(" ");
        verify(restTemplate, times(0)).getForObject(anyString(), any());
        assertEquals(isBlank(countryFullName), true);
    }
}
