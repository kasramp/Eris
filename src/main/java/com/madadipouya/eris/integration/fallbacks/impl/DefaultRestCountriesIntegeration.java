package com.madadipouya.eris.integration.fallbacks.impl;

import com.madadipouya.eris.integration.fallbacks.RestCountriesIntegration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service("restCountriesIntegration")
public class DefaultRestCountriesIntegeration implements RestCountriesIntegration {

    private static final String API_URL = "https://restcountries.eu/rest/v2/alpha/%s";

    private final RestTemplate restTemplate;

    public DefaultRestCountriesIntegeration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getCountryFullName(String countryCode) {
        return isBlank(countryCode) ? EMPTY : (String) restTemplate.getForObject(String.format(API_URL, countryCode), Map.class).getOrDefault("name", EMPTY);
    }
}
