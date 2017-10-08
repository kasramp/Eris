package com.madadipouya.eris.integration.groupkt;

import com.madadipouya.eris.integration.groupkt.remote.response.GroupktCountryNameResponse;

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
* This service is responsible to resolve a ISO 3166-1 alpha-2 country code to a full country name
* */
public interface GroupktCountryNameIntegration {

    /**
    * Retrieves a country full name based on the provided ISO 3166-1 alpha-2
    * First attempts to resolve the country name from cache {@link com.madadipouya.eris.configuration.CacheConfiguration}
    * @param countryCode ISO 3166-1 alpha-2 country code
    * @return a country full name
    * */
    String getCountryFullName(String countryCode);
}
