package com.madadipouya.eris.integration.ipapi;

import com.madadipouya.eris.integration.ipapi.remote.response.IpApiResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.madadipouya.eris.configuration.CacheConfiguration.IP_API_CACHE;
import static org.apache.commons.lang3.StringUtils.trim;

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

@Service("ipApiIntegration")
public class DefaultIpApiIntegration implements IpApiIntegration {

    private static final String API_URL = "http://ip-api.com/json/%s";

    private final RestTemplate restTemplate;

    public DefaultIpApiIntegration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(IP_API_CACHE)
    public IpApiResponse getCoordinatesFromIp(String ipAddress) {
        return restTemplate.getForObject(String.format(API_URL, trim(ipAddress)), IpApiResponse.class);
    }
}
