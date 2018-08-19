package com.madadipouya.eris.integration.groupkt;

import com.madadipouya.eris.integration.groupkt.remote.response.GroupktCountryNameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.madadipouya.eris.configuration.CacheConfiguration.COUNTRY_CODE_CACHE;
import static org.apache.commons.lang3.StringUtils.isBlank;

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

@Service("groupktCountryNameIntegration")
public class DefaultGroupktCountryNameIntegration implements GroupktCountryNameIntegration {

    private static final String API_URL = "http://services.groupkt.com/country/get/iso2code/%s";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Cacheable(COUNTRY_CODE_CACHE)
    public String getCountryFullName(String countryCode) {
        return getCountryDetails(countryCode).getRestResponse().getResult().getName();
    }

    private GroupktCountryNameResponse getCountryDetails(String countryCode) {
        return isBlank(countryCode) ? new GroupktCountryNameResponse() :
                restTemplate.getForObject(String.format(API_URL, countryCode), GroupktCountryNameResponse.class);
    }
}
