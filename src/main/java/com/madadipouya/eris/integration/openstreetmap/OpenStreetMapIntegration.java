package com.madadipouya.eris.integration.openstreetmap;

import com.madadipouya.eris.integration.openstreetmap.remote.response.OpenStreetMapLocationResponse;

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

/**
 * This service is responsible to get physical address of a geographical location
* */
public interface OpenStreetMapIntegration {

    /**
     * Gets physical address of a geographical location
     * @param latitude Latitude of location to get the address for
     * @param longitude Longitude of location to get the address for
     * @return Physical address of the location contains the full address
    * */
    String getAddressByCoordinates(String latitude, String longitude);

    /**
     * Gets physical address of a geographical location
     * @param latitude Latitude of location to get the address for
     * @param longitude Longitude of location to get the address for
     * @return {@link OpenStreetMapLocationResponse} The physical address of the location
     * */
    OpenStreetMapLocationResponse getReverseGeocoding(String latitude, String longitude);
}
