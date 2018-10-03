package com.madadipouya.eris;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
* © 2017-2018 Kasra Madadipouya <kasra@madadipouya.com>
*/

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = {"classpath:test.properties"}
        , properties = {"spring.mvc.throw-exception-if-no-handler-found=true"})
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @BeforeAll
    public static void setup() {
        System.setProperty("OPENWEATHERMAP_API_KEY", "testKey");
        System.setProperty("SEGMENTIO_WRITE_API_KEY", "testKey");
    }

    @Test
    public void testApplicationBootstrap() {
        Application.main(new String[]{"--spring.main.web-environment=false"});
    }
}