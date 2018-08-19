package com.madadipouya.eris.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class CacheConfigurationTest {

    @Spy
    @InjectMocks
    private CacheConfiguration cacheConfiguration;

    @Test
    public void testCacheConfiguration() {
        CacheManager cacheManager = cacheConfiguration.cacheManager();
        assertNotNull(cacheManager);
        List<? extends Cache> caches = (List<? extends Cache>) Whitebox.getInternalState(cacheManager, "caches");
        assertNotNull(caches);
        assertEquals(3, caches.size());
        assertEquals("countryCodeCache", caches.get(0).getName());
        assertEquals("openStreetCache", caches.get(1).getName());
        assertEquals("ipApiCache", caches.get(2).getName());
    }
}
