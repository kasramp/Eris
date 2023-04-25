package com.madadipouya.eris.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

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
 * © 2017-2023 Kasra Madadipouya <kasra@madadipouya.com>
 */


@ExtendWith(MockitoExtension.class)
class SwaggerConfigurationTest {

    @Spy
    private SwaggerConfiguration swaggerConfiguration;

    @Test
    void testOpenApiContactDetails() {
        OpenAPI openAPI = swaggerConfiguration.apiInfo();
        Info apiInfo = openAPI.getInfo();
        Contact contactDetails = openAPI.getInfo().getContact();
        License licenseDetails = openAPI.getInfo().getLicense();

        assertNotNull(apiInfo);
        assertNotNull(contactDetails);
        assertNotNull(licenseDetails);
        assertEquals("Eris API documentation", apiInfo.getTitle());
        assertEquals("Documentation for Eris weather API", apiInfo.getDescription());
        assertEquals("v1", apiInfo.getVersion());
        assertEquals("Kasra Madadipouya", contactDetails.getName());
        assertEquals("kasra@madadipouya.com", contactDetails.getEmail());
        assertEquals("https://eris.madadipouya.com", contactDetails.getUrl());
        assertEquals("GNU General Public License v3.0", licenseDetails.getName());
        assertEquals("https://github.com/kasramp/Eris/blob/develop/LICENSE", licenseDetails.getUrl());
    }
}
