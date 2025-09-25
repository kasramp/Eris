package com.madadipouya.eris;

import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.wiremock.WireMockConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@TestConfiguration

public class WireMockTestConfig {
  @Bean
  WireMockConfigurationCustomizer optionsCustomizer() {
    return config -> config.notifier(new ConsoleNotifier(true));
  }
}