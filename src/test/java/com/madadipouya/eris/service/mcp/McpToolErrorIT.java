package com.madadipouya.eris.service.mcp;

import com.madadipouya.eris.service.weather.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
 * © 2026 Kasra Madadipouya <kasra@madadipouya.com>
 */

@TestPropertySource(locations = {"classpath:test.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class McpToolErrorIT {

    private static final String MCP_URL = "/mcp";

    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private Weather weather;

    @Test
    void testUnexpectedToolFailureIsReportedWithoutInternalDetails() {
        when(weather.getCurrent(anyString(), anyString(), anyBoolean()))
                .thenThrow(new IllegalStateException("upstream secret detail"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.TEXT_EVENT_STREAM, MediaType.APPLICATION_JSON));

        ResponseEntity<String> initializeResponse = restTemplate.exchange(MCP_URL, HttpMethod.POST, new HttpEntity<>("""
                {"jsonrpc":"2.0","id":1,"method":"initialize","params":{"protocolVersion":"2025-03-26","capabilities":{},"clientInfo":{"name":"test","version":"1.0"}}}
                """, headers), String.class);
        headers.set("Mcp-Session-Id", initializeResponse.getHeaders().getFirst("Mcp-Session-Id"));

        ResponseEntity<String> response = restTemplate.exchange(MCP_URL, HttpMethod.POST, new HttpEntity<>("""
                {"jsonrpc":"2.0","id":2,"method":"tools/call","params":{"name":"get_current_weather_by_latitude_and_longitude","arguments":{"latitude":"52.5","longitude":"13.4"}}}
                """, headers), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("\"isError\":true"), response.getBody());
        assertTrue(response.getBody().contains("Unable to process the request due to an internal error"), response.getBody());
        assertFalse(response.getBody().contains("upstream secret detail"), response.getBody());
    }
}
