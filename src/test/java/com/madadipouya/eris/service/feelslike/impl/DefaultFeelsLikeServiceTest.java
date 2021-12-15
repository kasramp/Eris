package com.madadipouya.eris.service.feelslike.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

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
* © 2017-2022 Kasra Madadipouya <kasra@madadipouya.com>
*/

@ExtendWith(MockitoExtension.class)
class DefaultFeelsLikeServiceTest {

    @Spy
    @InjectMocks
    private DefaultFeelsLikeService service;

    @Test
    void testGetFeelsLikeFahrenheitHeatIndex() {
        double result = service.getFeelsLike(84, 2.68, 74, true);
        assertEquals(91.81, result, 0.0001);
    }

    @Test
    void testGetFeelsLikeFahrenheitHeatIndexLessThanRealTemperature() {
        double result = service.getFeelsLike(80.6, 13.04, 10, true);
        assertEquals(80.6, result, 0.0001);
    }

    @Test
    void testGetFeelsLikeFahrenheitWindChill() {
        double result = service.getFeelsLike(50, 4, 82, true);
        assertEquals(48.87, result, 0.0001);
    }

    @Test
    void testGetFeelsLikeFahrenheitLinearRollOff() {
        double result = service.getFeelsLike(56, 9, 64, true);
        assertEquals(55.63, result, 0.0001);
    }

    @Test
    void testGetFeelsLikeCelsiusHeatIndex() {
        double result = service.getFeelsLike(29, 4.32, 74, false);
        assertEquals(33.49, result, 0.0001);
    }

    @Test
    void testGetFeelsLikeCelsiusHeatIndexLessThanRealTemperature() {
        double result = service.getFeelsLike(27, 21, 10, false);
        assertEquals(27, result, 0.0001);
    }

    @Test
    void testGetFeelsLikeCelsiusWindChill() {
        double result = service.getFeelsLike(10, 6, 82, false);
        assertEquals(9.48, result, 0.0001);
    }

    @Test
    void testGetFeelsLikeCelsiusLinearRollOff() {
        double result = service.getFeelsLike(14, 13, 63, false);
        assertEquals(14.00, result, 0.0001);
    }
}