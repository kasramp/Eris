package com.madadipouya.eris.integration.ipapi;

import com.madadipouya.eris.integration.ipapi.remote.response.IpApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

@Service("openWeatherMapIntegration")
public class DefaultIpApiIntegration implements IpApiIntegration {

    private static final String API_URL = "http://ip-api.com/json/%s";

    @Override
    public IpApiResponse getCoordinatesFromIp(String ipAddress) {
        return new RestTemplate().getForObject(
                String.format(API_URL, ipAddress), IpApiResponse.class);
    }
}
