package com.madadipouya.eris.configuration;

import com.madadipouya.eris.util.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
public class SecurityProvider {

    private final PropertyUtils propertyUtils;

    private final PasswordEncoder passwordEncoder;

    public SecurityProvider(PropertyUtils propertyUtils, PasswordEncoder passwordEncoder) {
        this.propertyUtils = propertyUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(propertyUtils.getActuatorUsername())
                .password(passwordEncoder.encode(propertyUtils.getActuatorPassword()))
                .roles("ADMIN");
    }
}
