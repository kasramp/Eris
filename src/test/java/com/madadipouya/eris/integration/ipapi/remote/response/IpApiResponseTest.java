package com.madadipouya.eris.integration.ipapi.remote.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


public class IpApiResponseTest {

    @Test
    public void testIpApiResponseGetterSetter() {
        IpApiResponse ipApiResponse = new IpApiResponse();
        ipApiResponse.setLatitude("1.00");
        ipApiResponse.setLongitude("2.00");
        ipApiResponse.setAs("as");
        ipApiResponse.setCity("London");
        ipApiResponse.setCountry("UK");
        ipApiResponse.setCountryCode("+44");
        ipApiResponse.setIsp("BT");
        ipApiResponse.setOrganization("BT");
        ipApiResponse.setQuery("Query");
        ipApiResponse.setRegion("EU");
        ipApiResponse.setRegionName("Europe");
        ipApiResponse.setStatus("Success");
        ipApiResponse.setTimezone("UTC+00:00");
        ipApiResponse.setZip("UK Zip Code");
        assertEquals("1.00", ipApiResponse.getLatitude());
        assertEquals("2.00", ipApiResponse.getLongitude());
        assertEquals("as", ipApiResponse.getAs());
        assertEquals("London", ipApiResponse.getCity());
        assertEquals("UK", ipApiResponse.getCountry());
        assertEquals("+44", ipApiResponse.getCountryCode());
        assertEquals("BT", ipApiResponse.getIsp());
        assertEquals("BT", ipApiResponse.getOrganization());
        assertEquals("Query", ipApiResponse.getQuery());
        assertEquals("EU", ipApiResponse.getRegion());
        assertEquals("Europe", ipApiResponse.getRegionName());
        assertEquals("Success", ipApiResponse.getStatus());
        assertEquals("UTC+00:00", ipApiResponse.getTimezone());
        assertEquals("UK Zip Code", ipApiResponse.getZip());
    }
}
