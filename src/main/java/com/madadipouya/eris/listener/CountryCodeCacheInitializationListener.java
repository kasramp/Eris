package com.madadipouya.eris.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

import static com.madadipouya.eris.configuration.CacheConfiguration.COUNTRY_CODE_CACHE;

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

@Component
public class CountryCodeCacheInitializationListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(CountryCodeCacheInitializationListener.class);

    private static final String COUNTRY_CODE_FILE_NAME = "countryCode.csv";

    @Value("classpath:" + COUNTRY_CODE_FILE_NAME)
    private Resource countryCodeFile;

    @Autowired
    CacheManager cacheManager;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        populateCountryCodeCache(getCache(), getCountryCodeFileContent());
    }

    private Cache getCache() {
        return cacheManager.getCache(COUNTRY_CODE_CACHE);
    }

    Map<String, String> getCountryCodeFileContent() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(countryCodeFile.getInputStream()))) {
            return reader.lines()
                    .map(element -> element.split("[,]"))
                    .filter(element -> element.length == 2)
                    .collect(Collectors.toMap(element -> element[0].trim(), element -> element[1].trim()));
        } catch (IOException ioException) {
            logger.warn("Failed to open the country code file, the cache will be empty", ioException);
        }


        return Map.of();
    }

    void populateCountryCodeCache(Cache cache, Map<String, String> content) {
        content.forEach(cache::put);
    }
}