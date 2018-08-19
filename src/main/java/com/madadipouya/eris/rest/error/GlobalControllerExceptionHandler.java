package com.madadipouya.eris.rest.error;

import com.madadipouya.eris.service.weather.model.CurrentWeatherCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

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

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
    private static final String ERR_UNABLE_TO_PROCESS_REQUEST = "Unable to process the request!";

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CurrentWeatherCondition> unknownException(Exception ex) {
        logger.error(getStackTrace(ex));
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new CurrentWeatherCondition(List.of(ERR_UNABLE_TO_PROCESS_REQUEST)));
    }
}
