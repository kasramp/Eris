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
* © 2017 Kasra Madadipouya <kasra@madadipouya.com>
*/

public interface OpenStreetMapIntegration {

    String getAddressByCoordinates(String latitude, String longitude);

    OpenStreetMapLocationResponse getReverseGeocoding(String latitude, String longitude);
}
