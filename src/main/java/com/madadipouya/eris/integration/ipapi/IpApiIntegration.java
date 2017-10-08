package com.madadipouya.eris.integration.ipapi;

import com.madadipouya.eris.integration.ipapi.remote.response.IpApiResponse;

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

/**
 * This service is responsible to approximately
 * retrieve latitude and longitude of a given ipAddress
 * with other information such as country code, see {@link IpApiResponse}
 * */
public interface IpApiIntegration {

    /**
     * Retrieves information related to a given IP Address
     * by calling @see <a href="ip-api.com">IP API</a> service
     * @param ipAddress IP Address that needed to extract related information
     * @return {@link IpApiResponse} contains information about the IP,
     * most importantly latitude and longitude
    * */
    IpApiResponse getCoordinatesFromIp(String ipAddress);
}
