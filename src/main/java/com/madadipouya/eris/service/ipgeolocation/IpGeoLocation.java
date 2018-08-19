package com.madadipouya.eris.service.ipgeolocation;

import com.madadipouya.eris.service.ipgeolocation.model.Coordinates;

import javax.servlet.http.HttpServletRequest;

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

/**
 * This service is responsible to get geographical coordinates (latitude, longitude) which
 * uses {@link com.madadipouya.eris.integration.ipapi.IpApiIntegration} service.
 * The service is also capable of resolving IP Address of {@link HttpServletRequest}
 */
public interface IpGeoLocation {

    /**
     * Gets geographical coordinates based on {@link HttpServletRequest}
     *
     * @param request Http request passed from a controller
     * @return {@link Coordinates} Coordinates of a location
     */
    Coordinates getCoordinates(HttpServletRequest request);

    /**
     * Gets geographical coordinates based on passed IP Address
     *
     * @param ipAddress IP Address that want to get coordinates for
     * @return {@link Coordinates} Coordinates of a location
     */
    Coordinates getCoordinates(String ipAddress);

    /**
     * Resolves IP Address of a {@link HttpServletRequest}.
     * Supports reverse proxy and subnet
     *
     * @param request Http Request that want to get IP address of it.
     * @return IP Address of the request
     */
    String getRequestIpAddress(HttpServletRequest request);

    /**
     * Resolves IP Address of a {@link HttpServletRequest}.
     * Only supports reverse proxy
     *
     * @param request Http Request that want to get IP address of it.
     * @return IP Address of the request
     */
    String getRequestIpAddressSimple(HttpServletRequest request);
}