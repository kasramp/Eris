package com.madadipouya.eris.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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
 * © 2017-2023 Kasra Madadipouya <kasra@madadipouya.com>
 */

@ExtendWith(MockitoExtension.class)
class RedirectTest {

    @Spy
    @InjectMocks
    private Redirect redirectController;

    @Test
    void testRedirectToDocPage() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getScheme()).thenReturn("http");
        ModelAndView page = redirectController.redirectToDocPage(request);
        assertEquals("redirect:http://eris.madadipouya.com", page.getViewName());
    }

    @Test
    void testRedirectToApiDocs() {
        ModelAndView page = redirectController.redirectToApiPage();
        assertEquals("redirect:/swagger-ui/index.html", page.getViewName());
    }
}
