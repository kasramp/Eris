package com.madadipouya.eris.integration.ipapi.remote.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by kixz on 10/24/17.
 */
@RunWith(MockitoJUnitRunner.class)
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
