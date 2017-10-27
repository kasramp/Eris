package com.madadipouya.eris.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
public class SwaggerConfigurationTest {

    @Spy
    private SwaggerConfiguration swaggerConfiguration;

    @Test
    public void testGetSwaggerApiInfo() {
        ApiInfo apiInfo = swaggerConfiguration.metaData();
        Contact contact = apiInfo.getContact();
        assertNotNull(apiInfo);
        assertNotNull(contact);
        assertEquals("Eris API documentation", apiInfo.getTitle());
        assertEquals("Documentation for Eris weather API", apiInfo.getDescription());
        assertEquals("v1", apiInfo.getVersion());
        assertEquals("Free of charge", apiInfo.getTermsOfServiceUrl());
        assertEquals("GNU General Public License v3.0", apiInfo.getLicense());
        assertEquals("https://github.com/kasramp/Eris/blob/develop/LICENSE", apiInfo.getLicenseUrl());
        assertEquals(0, apiInfo.getVendorExtensions().size());
        assertEquals("Kasra Madadipouya", contact.getName());
        assertEquals("http://eris.madadipouya.com", contact.getUrl());
        assertEquals("kasra@madadipouya.com", contact.getEmail());
    }

    @Test
    public void testProductApi() {
        Docket docket = swaggerConfiguration.productApi();
        ApiSelectorBuilder builder = docket.select();
        assertNotNull(docket);
        assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
        assertNotNull(builder);
    }
}
