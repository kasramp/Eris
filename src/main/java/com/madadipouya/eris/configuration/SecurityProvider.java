package com.madadipouya.eris.configuration;

import com.madadipouya.eris.util.PropertyUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
 * © 2017-2026 Kasra Madadipouya <kasra@madadipouya.com>
 */

@Configuration
public class SecurityProvider {

    private final PropertyUtils propertyUtils;
    private final PasswordEncoder passwordEncoder;

    public SecurityProvider(PropertyUtils propertyUtils, PasswordEncoder passwordEncoder) {
        this.propertyUtils = propertyUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username(propertyUtils.getActuatorUsername())
                .password(passwordEncoder.encode(propertyUtils.getActuatorPassword()))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}