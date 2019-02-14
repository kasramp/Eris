package com.madadipouya.eris.service.ipgeolocation.impl;

import com.madadipouya.eris.integration.ipapi.IpApiIntegration;
import com.madadipouya.eris.service.ipgeolocation.model.Coordinates;
import com.madadipouya.eris.service.ipgeolocation.IpGeoLocation;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.madadipouya.eris.util.BeanUtils.copyProperties;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.split;
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
* © 2017-2019 Kasra Madadipouya <kasra@madadipouya.com>
*/

@Service("ipGeoLocation")
public class DefaultIpGeoLocation implements IpGeoLocation {

    private final IpApiIntegration ipApiIntegration;

    public DefaultIpGeoLocation(IpApiIntegration ipApiIntegration) {
        this.ipApiIntegration = ipApiIntegration;
    }

    @Override
    public Coordinates getCoordinates(HttpServletRequest request) {
        return getCoordinates(getRequestIpAddress(request));
    }

    @Override
    public Coordinates getCoordinates(String ipAddress) {
        return copyProperties(ipApiIntegration.getCoordinatesFromIp(ipAddress), new Coordinates());
    }

    @Override
    public String getRequestIpAddress(HttpServletRequest request) {
        String ipAddress = getRequestIpAddressSimple(request);
        return ipAddress.contains(",") ? trim(split(trim(ipAddress), ",")[1]) : ipAddress;
    }

    @Override
    public String getRequestIpAddressSimple(HttpServletRequest request) {
        // Support Reverse Proxy
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        return isBlank(ipAddress) ? request.getRemoteAddr(): ipAddress;
    }
}