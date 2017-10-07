package com.madadipouya.eris.util;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UnitConversionUtilsTest {

    @Test
    public void testFahrenheitToCelsius() {
        assertEquals(UnitConversionUtils.FahrenheitToCelsius(104), 40, 0.001);
    }
    
}
