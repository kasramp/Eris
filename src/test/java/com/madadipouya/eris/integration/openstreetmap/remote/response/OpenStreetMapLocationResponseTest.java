package com.madadipouya.eris.integration.openstreetmap.remote.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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

class OpenStreetMapLocationResponseTest {

    @Test
    void testOpenStreetMapLocationResponse() {
        OpenStreetMapLocationResponse openStreetResponse = new OpenStreetMapLocationResponse();
        openStreetResponse.setPlaceId("10");
        openStreetResponse.setLicense("License");
        openStreetResponse.setOsmType("OSMType");
        openStreetResponse.setOsmId("20");
        openStreetResponse.setLatitude("1.00");
        openStreetResponse.setLongitude("2.00");
        openStreetResponse.setDisplayName("DisplayName");
        openStreetResponse.setBoundingBox(List.of("BoundingBox1", "BoundingBox2"));
        OpenStreetMapLocationResponse.Address address = new OpenStreetMapLocationResponse.Address();
        address.setCountry("Australia");
        address.setCountryCode("AU");
        address.setRoad("Random Road");
        address.setState("Sydney");
        openStreetResponse.setAddress(address);
        assertEquals("10", openStreetResponse.getPlaceId());
        assertEquals("License", openStreetResponse.getLicense());
        assertEquals("OSMType", openStreetResponse.getOsmType());
        assertEquals("20", openStreetResponse.getOsmId());
        assertEquals("1.00", openStreetResponse.getLatitude());
        assertEquals("2.00", openStreetResponse.getLongitude());
        assertEquals("DisplayName", openStreetResponse.getDisplayName());
        assertNotNull(openStreetResponse.getBoundingBox());
        assertEquals(2, openStreetResponse.getBoundingBox().size());
        assertEquals("BoundingBox1", openStreetResponse.getBoundingBox().get(0));
        assertEquals("BoundingBox2", openStreetResponse.getBoundingBox().get(1));
        assertNotNull(openStreetResponse.getAddress());
        assertEquals("Australia", openStreetResponse.getAddress().getCountry());
        assertEquals("AU", openStreetResponse.getAddress().getCountryCode());
        assertEquals("Random Road", openStreetResponse.getAddress().getRoad());
        assertEquals("Sydney", openStreetResponse.getAddress().getState());
    }
}
