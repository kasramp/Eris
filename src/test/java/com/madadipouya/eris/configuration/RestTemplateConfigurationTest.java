package com.madadipouya.eris.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertNotNull;

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

@RunWith(MockitoJUnitRunner.class)
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
