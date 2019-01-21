package com.madadipouya.eris.listener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import static com.madadipouya.eris.configuration.CacheConfiguration.COUNTRY_CODE_CACHE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.*;
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
public class CountryCodeCacheInitializationListenerTest {

    @Spy
    @InjectMocks
    private CountryCodeCacheInitializationListener listener;

    @Mock
    private CacheManager cacheManager;

    private Resource resource = mock(Resource.class);

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(listener, "countryCodeFile", resource);
    }

    @Test
    public void testOnApplicationEvent() {
        Cache cache = spy(new ConcurrentMapCache(COUNTRY_CODE_CACHE));
        Map<String, String> fileContent = Map.of("DE", "Germany", "AU", "Australia");
        doReturn(cache).when(cacheManager).getCache(COUNTRY_CODE_CACHE);
        doReturn(fileContent).when(listener).getCountryCodeFileContent();
        listener.onApplicationEvent(mock(ApplicationReadyEvent.class));
        verify(cacheManager, times(1)).getCache(COUNTRY_CODE_CACHE);
        verify(listener, times(1)).getCountryCodeFileContent();
        verify(listener, times(1)).populateCountryCodeCache(cache, fileContent);
        verify(cache, times(2)).put(any(), any());
        assertEquals(COUNTRY_CODE_CACHE, cache.getName());
        assertEquals("Germany", cache.get("DE", String.class));
        assertEquals("Australia", cache.get("AU", String.class));
        assertNull(cache.get("NZ"));
    }

    @Test
    public void testPopulateCountryCodeCache() {
        Cache cache = spy(new ConcurrentMapCache(COUNTRY_CODE_CACHE));
        Map<String, String> fileContent = Map.of("DE", "Germany", "AU", "Australia");
        listener.populateCountryCodeCache(cache, fileContent);
        verify(cache, times(2)).put(any(), any());
        assertEquals(COUNTRY_CODE_CACHE, cache.getName());
        assertEquals("Germany", cache.get("DE", String.class));
        assertEquals("Australia", cache.get("AU", String.class));
        assertNull(cache.get("NZ"));
    }

    @Test
    public void testGetCountryCodeFileContent() throws IOException {
        when(resource.getInputStream()).thenReturn(new ByteArrayInputStream("DE, Germany \n AU, Australia".getBytes()));
        Map<String, String> fileContent = listener.getCountryCodeFileContent();
        verify(resource, times(1)).getInputStream();
        assertNotNull(fileContent);
        assertEquals(2, fileContent.size());
        assertThat(fileContent, hasKey("DE"));
        assertThat(fileContent, hasKey("AU"));
        assertEquals("Germany", fileContent.get("DE"));
        assertEquals("Australia", fileContent.get("AU"));
    }

    @Test
    public void testGetCountryCodeFileContentWhenEntriesNotCommaSeparated() throws IOException {
        when(resource.getInputStream()).thenReturn(new ByteArrayInputStream("DE-Germany \n AU-Australia".getBytes()));
        Map<String, String> fileContent = listener.getCountryCodeFileContent();
        verify(resource, times(1)).getInputStream();
        assertNotNull(fileContent);
        assertEquals(0, fileContent.size());
        assertNull(fileContent.get("DE"));
        assertNull(fileContent.get("AU"));
    }

    @Test
    public void testGetCountryCodeFileContentThrowException() throws IOException {
        when(resource.getInputStream()).thenThrow(IOException.class);
        Map<String, String> fileContent = listener.getCountryCodeFileContent();
        verify(resource, times(1)).getInputStream();
        assertNotNull(fileContent);
        assertEquals(0, fileContent.size());
    }
}
