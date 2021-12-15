package com.madadipouya.eris.integration.fallbacks.iplookup.impl;

import com.madadipouya.eris.integration.fallbacks.iplookup.ExtremeIpLookupIntegration;
import com.madadipouya.eris.integration.ipapi.remote.response.IpApiResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.madadipouya.eris.configuration.CacheConfiguration.EXTREME_IP_LOOKUP_CACHE;

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

@Service("extremeIpLookupIntegration")
public class DefaultExtremeIpLookupIntegration implements ExtremeIpLookupIntegration {

    private static final String API_URL = "http://extreme-ip-lookup.com/json/%s";

    private final RestTemplate restTemplate;

    public DefaultExtremeIpLookupIntegration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(EXTREME_IP_LOOKUP_CACHE)
    @Override
    public IpApiResponse getCoordinates(String ipAddress) {
        return restTemplate.getForObject(String.format(API_URL, ipAddress), IpApiResponse.class);
    }
}
