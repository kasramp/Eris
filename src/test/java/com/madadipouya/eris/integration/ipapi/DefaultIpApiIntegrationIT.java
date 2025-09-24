package com.madadipouya.eris.integration.ipapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.madadipouya.eris.WireMockTestConfig;
import com.madadipouya.eris.integration.ipapi.remote.response.IpApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;import static org.junit.jupiter.api.Assertions.assertEquals;

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
* © 2017-2025 Kasra Madadipouya <kasra@madadipouya.com>
*/

@Import({WireMockTestConfig.class})
@AutoConfigureWireMock(
    port = 0,
    stubs = "classpath:/wiremock/stubs/responses",
    files = "classpath:/wiremock/stubs")
@TestPropertySource(locations = {"classpath:test.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DefaultIpApiIntegrationIT {

    @Autowired
    private DefaultIpApiIntegration ipApiIntegration;

    @Test
    void shouldTranslateIpAddressToGeoLocation() {
        var response = ipApiIntegration.getCoordinatesFromIp("95.90.235.219");

        assertThat(response).isEqualTo(getExpectedResponse());
    }

    @Test
    void shouldReturnErrorResponseOnPrivateIpRange() {
        var expectedResponse = new IpApiResponse();
        expectedResponse.setStatus("fail");
        expectedResponse.setQuery("192.168.0.1");

        var response = ipApiIntegration.getCoordinatesFromIp("192.168.0.1");

        assertThat(response).isEqualTo(expectedResponse);
    }

    private IpApiResponse getExpectedResponse() {
        var ipApiResponse = new IpApiResponse();
        ipApiResponse.setStatus("success");
        ipApiResponse.setCountry("Germany");
        ipApiResponse.setCountryCode("DE");
        ipApiResponse.setRegion("BE");
        ipApiResponse.setRegionName("State of Berlin");
        ipApiResponse.setCity("Berlin");
        ipApiResponse.setZip("10999");
        ipApiResponse.setLatitude("10.4822");
        ipApiResponse.setLongitude("9.3392");
        ipApiResponse.setTimezone("Europe/Berlin");
        ipApiResponse.setIsp("Vodafone Kabel Deutschland");
        ipApiResponse.setOrganization("Vodafone Kabel Deutschland GmbH");
        ipApiResponse.setAs("AS3209 Vodafone GmbH");
        ipApiResponse.setQuery("95.90.235.219");
        return ipApiResponse;
    }
}
