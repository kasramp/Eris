package com.madadipouya.eris.integration.openstreetmap;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.madadipouya.eris.integration.openstreetmap.remote.response.OpenStreetMapLocationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.split;

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

@Service("openStreetMapIntegration")
public class DefaultOpenStreetMapIntegration implements OpenStreetMapIntegration {

    private static final String API_URL = "http://nominatim.openstreetmap.org/reverse?format=json&lat=%s&lon=%s&zoom=18&addressdetails=1";

    private static final Logger logger = LoggerFactory.getLogger(DefaultOpenStreetMapIntegration.class);

    private CacheMechanism cacheMechanism;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        cacheMechanism = new CacheMechanism();
    }

    @Override
    public String getAddressByCoordinates(String latitude, String longitude) {
        return getReverseGeocoding(latitude, longitude).getDisplayName();
    }

    @Override
    public OpenStreetMapLocationResponse getReverseGeocoding(String latitude, String longitude) {
        try {
            return cacheMechanism.get(latitude, longitude);
        } catch (ExecutionException cacheException) {
            logger.debug(format("Failed to get data from cache for latitude : %s, longitude : %s"), latitude, longitude);
            return getRemote(latitude, longitude);
        }
    }

    private OpenStreetMapLocationResponse getRemote(String latitude, String longitude) {
        return new RestTemplate().getForObject(
                format(API_URL, latitude, longitude), OpenStreetMapLocationResponse.class);
    }

    private class CacheMechanism {
        private LoadingCache<String, OpenStreetMapLocationResponse> cache = CacheBuilder.newBuilder().maximumSize(1000)
                .expireAfterAccess(30, TimeUnit.MINUTES).build(
                        new CacheLoader<String, OpenStreetMapLocationResponse>() {
                            @Override
                            public OpenStreetMapLocationResponse load(String key) throws Exception {
                                String[] coordinates = keyToCoordinate(key);
                                return getRemote(coordinates[0], coordinates[1]);
                            }
                        }
                );

        public OpenStreetMapLocationResponse get(String latitude, String longitude) throws ExecutionException {
            return cache.get(constructKey(latitude, longitude));
        }

        private String constructKey(String latitude, String longitude) {
            return format("%s|%s", latitude, longitude);
        }

        private String[] keyToCoordinate(String key) {
            return split(key, "|");
        }
    }
}