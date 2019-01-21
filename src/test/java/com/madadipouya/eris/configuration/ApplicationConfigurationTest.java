package com.madadipouya.eris.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

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
* © 2017-2019 Kasra Madadipouya <kasra@madadipouya.com>
*/

@ExtendWith(MockitoExtension.class)
public class ApplicationConfigurationTest {
    @Spy
    @InjectMocks
    private ApplicationConfiguration applicationConfiguration;

    @Mock
    private HandlerInterceptor handlerInterceptor;

    @Test
    public void testAddInterceptors() {
        InterceptorRegistry interceptorRegistry = mock(InterceptorRegistry.class);
        when(interceptorRegistry.addInterceptor(handlerInterceptor)).thenReturn(mock(InterceptorRegistration.class));
        applicationConfiguration.addInterceptors(interceptorRegistry);
        verify(interceptorRegistry, times(1)).addInterceptor(handlerInterceptor);
    }
}
