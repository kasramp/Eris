package com.madadipouya.eris.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
public class ApplicationConfigurationTest {
    @Spy
    @InjectMocks
    private ApplicationConfiguration applicationConfiguration;

    @Mock
    private HandlerInterceptor handlerInterceptor;

    @Test
    public void testAddInterceptors() {
        InterceptorRegistry interceptorRegistry = mock(InterceptorRegistry.class);
        applicationConfiguration.addInterceptors(interceptorRegistry);
        verify(interceptorRegistry, times(1)).addInterceptor(handlerInterceptor);
    }
}
