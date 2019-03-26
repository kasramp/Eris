package com.madadipouya.eris.integration.fallbacks.iplookup.impl;


import com.madadipouya.eris.integration.ipapi.remote.response.IpApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
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
class DefaultExtremeIpLookupIntegrationTest {

    @InjectMocks
    private DefaultExtremeIpLookupIntegration defaultExtremeIpLookupIntegration;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void testGetCoordinatesFromIp() {
        IpApiResponse mockedResponse = mock(IpApiResponse.class);
        when(restTemplate.getForObject("http://extreme-ip-lookup.com/json/185.136.168.105",
                IpApiResponse.class)).thenReturn(mockedResponse);
        when(mockedResponse.getCountry()).thenReturn("Germany");
        IpApiResponse response = defaultExtremeIpLookupIntegration.getCoordinates("185.136.168.105");
        verify(restTemplate, times(1)).getForObject(anyString(), any());
        assertNotNull(response);
        assertEquals("Germany", response.getCountry());
    }
}
