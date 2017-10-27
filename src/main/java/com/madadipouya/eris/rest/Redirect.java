package com.madadipouya.eris.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
* © 2017 Kasra Madadipouya <kasra@madadipouya.com>
*/

@RestController
public class Redirect {

    private static final String DOC_URL = "://eris.madadipouya.com";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView redirectToDocPage(HttpServletRequest request) {
        return new ModelAndView("redirect:" + request.getScheme() + DOC_URL);
    }

    @RequestMapping(value = "/apidocs", method = RequestMethod.GET)
    public ModelAndView redirectToApiPage() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }
}