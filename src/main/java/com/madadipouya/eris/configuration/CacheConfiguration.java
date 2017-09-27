package com.madadipouya.eris.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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

@Configuration
@ComponentScan("com.madadipouya.eris")
@EnableCaching
public class CacheConfiguration {
    public static final String COUNTRY_CODE_CACHE = "countryCodeCache";

    public static final String OPEN_STREET_CACHE = "openStreetCache";

    public static final String IP_API_CACHE = "ipApiCache";

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        CaffeineCache countryCodeCache = new CaffeineCache(COUNTRY_CODE_CACHE, Caffeine.newBuilder()
                .maximumSize(500)
                .build());

        CaffeineCache openStreetCache = new CaffeineCache(OPEN_STREET_CACHE, Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build());

        CaffeineCache ipApiCache = new CaffeineCache(IP_API_CACHE, Caffeine.newBuilder()
                .maximumSize(500)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build());

        cacheManager.setCaches(Arrays.asList(countryCodeCache, openStreetCache, ipApiCache));
        return cacheManager;
    }
}
