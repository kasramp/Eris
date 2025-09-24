package com.madadipouya.eris.integration.ipapi;

import com.madadipouya.eris.integration.ipapi.remote.response.IpApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
* © 2017-2025 Kasra Madadipouya <kasra@madadipouya.com>
*/

@SpringJUnitConfig(DefaultIpApiIntegration.class)
class DefaultIpApiIntegrationTest {

    @Autowired
    private DefaultIpApiIntegration ipApiIntegration;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testGetCoordinatesFromIp() {
        IpApiResponse mockIpApi = mock(IpApiResponse.class);
        when(mockIpApi.getCountry()).thenReturn("Australia");
        when(restTemplate.getForObject("http://ip-api.com/json/139.130.4.5", IpApiResponse.class)).thenReturn(mockIpApi);
        IpApiResponse response = ipApiIntegration.getCoordinatesFromIp("139.130.4.5");
        verify(restTemplate, times(1)).getForObject(anyString(), any());
        assertNotNull(response);
        assertEquals("Australia", response.getCountry());
    }
}
