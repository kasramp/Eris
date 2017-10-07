package com.madadipouya.eris.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.madadipouya.eris.util.UnitConversionUtils.*;
import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UnitConversionUtilsTest {

    @Test
    public void testFahrenheitToCelsius() {
        assertEquals(FahrenheitToCelsius(104), 40, 0.0001);
    }

    @Test
    public void testCelsiusToFahrenheit() {
        assertEquals(CelsiusToFahrenheit(40), 104, 0.0001);
    }

    @Test
    public void testKiloMeterPerHourToMilePerHour() {
        assertEquals(KiloMeterPerHourToMilePerHour(100), 62.1371, 0.0001);
    }

    @Test
    public void testMeterToMile() {
        assertEquals(MeterToMile(100), 0.0621371, 0.0000001);
    }

    @Test
    public void testMeterPerSecondToKiloMeterPerHour() {
        assertEquals(MeterPerSecondToKiloMeterPerHour(100), 360, 0.0001);
    }

    @Test
    public void testMeterToKiloMeter() {
        assertEquals(MeterToKiloMeter(1000), 1, 0.0001);
    }
}
