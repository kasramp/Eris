package com.madadipouya.eris.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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
class CacheConfigurationTest {

    @Spy
    @InjectMocks
    private CacheConfiguration cacheConfiguration;

    @Test
    void testCacheConfiguration() {
        CacheManager cacheManager = cacheConfiguration.cacheManager();
        assertNotNull(cacheManager);
        List<? extends Cache> caches = (List<? extends Cache>) ReflectionTestUtils.getField(cacheManager, "caches");
        assertNotNull(caches);
        assertEquals(4, caches.size());
        assertEquals("countryCodeCache", caches.get(0).getName());
        assertEquals("openStreetCache", caches.get(1).getName());
        assertEquals("ipApiCache", caches.get(2).getName());
        assertEquals("extremeIpLookup", caches.get(3).getName());
    }
}
