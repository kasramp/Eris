package com.madadipouya.eris.configuration;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;
import static java.time.Duration.ofSeconds;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

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

@Configuration
@ComponentScan("com.madadipouya.eris")
public class CircuitBreakerConfiguration {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCircuitBreakerCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id).circuitBreakerConfig(CircuitBreakerConfig.custom()
                .failureRateThreshold(30)
                .minimumNumberOfCalls(5)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .waitDurationInOpenState(ofSeconds(5))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowSize(10)
                .slidingWindowType(COUNT_BASED)
                .build())
            .timeLimiterConfig(TimeLimiterConfig.custom()
                .timeoutDuration(ofSeconds(2))
                .build())
            .build());
    }
}
