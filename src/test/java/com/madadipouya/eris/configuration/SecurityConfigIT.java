package com.madadipouya.eris.configuration;

import com.madadipouya.eris.util.PropertyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;
import java.util.List;

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


@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = {"classpath:test.properties"}
        , properties = {"spring.mvc.throw-exception-if-no-handler-found=true"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityConfigIT {

    private static final String BASE_ACTUATOR_URL = "/actuator/%s";

    private static final String HEALTH_URL = String.format(BASE_ACTUATOR_URL, "health");

    private static final String ENV_URL = String.format(BASE_ACTUATOR_URL, "env");

    private static final String AUDIT_EVENTS_URL = String.format(BASE_ACTUATOR_URL, "auditevents");

    private static final String BEANS_URL = String.format(BASE_ACTUATOR_URL, "beans");

    private static final String CACHES_URL = String.format(BASE_ACTUATOR_URL, "caches");

    private static final String CONDITIONS_URL = String.format(BASE_ACTUATOR_URL, "conditions");

    private static final String CONFIG_PROPS_URL = String.format(BASE_ACTUATOR_URL, "configprops");

    private static final String HTTP_TRACE_URL = String.format(BASE_ACTUATOR_URL, "httptrace");

    private static final String INFO_URL = String.format(BASE_ACTUATOR_URL, "info");

    private static final String LOGGERS_URL = String.format(BASE_ACTUATOR_URL, "loggers");

    private static final String METRICS_URL = String.format(BASE_ACTUATOR_URL, "metrics");

    private static final String MAPPINGS_URL = String.format(BASE_ACTUATOR_URL, "mappings");

    private static final String SCHEDULED_TASKS_URL = String.format(BASE_ACTUATOR_URL, "scheduledtasks");

    private static final String THREAD_DUMP_URL = String.format(BASE_ACTUATOR_URL, "threaddump");

    private static final String HEAP_DUMP_URL = String.format(BASE_ACTUATOR_URL, "heapdump");

    private BasicAuthenticationInterceptor bai;

    @Autowired
    private PropertyUtils propertyUtils;

    @Autowired
    private TestRestTemplate restTemplate;

    @PostConstruct
    void afterPropertiesSet() {
        bai = new BasicAuthenticationInterceptor(propertyUtils.getHealthUsername(), propertyUtils.getHealthPassword());
        restTemplate.getRestTemplate().setInterceptors(List.of(bai));
    }

    @Nested
    @DisplayName("Unauthorized access to Spring Actuators endpoints")
    class UnauthorizedAccessToActuatorEndpoints {

        @BeforeEach
        void beforeEach() {
            restTemplate.getRestTemplate().getInterceptors().remove(bai);
        }

        @Test
        void testUnauthorizedAccessToHealthEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(HEALTH_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToEnvEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(ENV_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToAuditEventEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(AUDIT_EVENTS_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToBeansEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(BEANS_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToCachesEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(CACHES_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToConditionsEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(CONDITIONS_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToConfigPropsEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(CONFIG_PROPS_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToHttpTraceEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(HTTP_TRACE_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToInfoEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(INFO_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToLoggersEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(LOGGERS_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToMetricsEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(METRICS_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToMappingsEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(MAPPINGS_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToScheduledTasksEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(SCHEDULED_TASKS_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToThreadDumpEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(THREAD_DUMP_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }

        @Test
        void testUnauthorizedAccessToHeapDumpEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(HEAP_DUMP_URL, String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        }
    }

    @Nested
    @DisplayName("Authorized access to Spring Actuators endpoints")
    class AuthorizedAccessToActuatorEndpoints {

        @BeforeEach
        void beforeEach() {
            restTemplate.getRestTemplate().getInterceptors().add(bai);
        }

        @Test
        void testAuthorizedAccessToHealthEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(HEALTH_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToEnvEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(ENV_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToAuditEventEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(AUDIT_EVENTS_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToBeansEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(BEANS_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToCachesEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(CACHES_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToConditionsEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(CONDITIONS_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToConfigPropsEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(CONFIG_PROPS_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToHttpTraceEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(HTTP_TRACE_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToInfoEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(INFO_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToLoggersEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(LOGGERS_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }


        @Test
        void testAuthorizedAccessToMetricsEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(METRICS_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToMappingsEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(MAPPINGS_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToScheduledTasksEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(SCHEDULED_TASKS_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToThreadDumpEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(THREAD_DUMP_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testAuthorizedAccessToHeapDumpEndpoint() {
            ResponseEntity<String> response = restTemplate.getForEntity(HEAP_DUMP_URL, String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }
}