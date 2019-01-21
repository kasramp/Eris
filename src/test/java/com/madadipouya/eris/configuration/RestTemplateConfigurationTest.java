package com.madadipouya.eris.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;


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
public class RestTemplateConfigurationTest {
    @Spy
    @InjectMocks
    private RestTemplateConfiguration restTemplateConfiguration;

    @Test
    public void testConfiguringRestTemplate() {
        RestTemplate restTemplate = restTemplateConfiguration.restTemplate();
        assertNotNull(restTemplate);
        RestTemplate restTemplate1 = restTemplateConfiguration.restTemplate();
        assertNotNull(restTemplate1);
        assertNotSame(restTemplate, restTemplate1);
    }
}
