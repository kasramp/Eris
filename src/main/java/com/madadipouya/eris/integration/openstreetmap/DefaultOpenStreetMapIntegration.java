package com.madadipouya.eris.integration.openstreetmap;

import com.madadipouya.eris.integration.openstreetmap.remote.response.OpenStreetMapLocationResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.madadipouya.eris.configuration.CacheConfiguration.OPEN_STREET_CACHE;
import static java.lang.String.format;
import static org.springframework.http.HttpHeaders.REFERER;
import static org.springframework.http.HttpHeaders.USER_AGENT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;

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

@Service("openStreetMapIntegration")
public class DefaultOpenStreetMapIntegration implements OpenStreetMapIntegration {
    private static final String API_URL = "https://nominatim.openstreetmap.org/reverse?format=json&lat=%s&lon=%s&zoom=18&addressdetails=1";
    private static final String USER_AGENT_NAME = "Eris weather API";
    private static final String REFERER_NAME = "https://eris.madadipouya.com";

    private final RestTemplate restTemplate;

    public DefaultOpenStreetMapIntegration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(value = OPEN_STREET_CACHE, key = "{ #latitude, #longitude }")
    public String getAddressByCoordinates(String latitude, String longitude) {
        return getReverseGeocoding(latitude, longitude).getDisplayName();
    }

    @Override
    public OpenStreetMapLocationResponse getReverseGeocoding(String latitude, String longitude) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(APPLICATION_JSON));
        headers.setContentType(APPLICATION_JSON);
        headers.set(USER_AGENT, USER_AGENT_NAME);
        headers.set(REFERER, REFERER_NAME);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.exchange(format(API_URL, latitude, longitude),
                GET, entity, OpenStreetMapLocationResponse.class).getBody();
    }
}