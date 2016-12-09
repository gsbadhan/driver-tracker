package com.drivertracker.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class ValidationTest {

    @Test
    public void testIsValidDriverId() {
        assertTrue(Validation.isValidDriverId(101L));
        assertTrue(Validation.isValidDriverId(1000L));
        assertTrue(Validation.isValidDriverId(5000L));
        assertFalse(Validation.isValidDriverId(50001L));
        assertFalse(Validation.isValidDriverId(99890L));
    }

    @Test
    public void testIsValidLatitude() {
        assertTrue(Validation.isValidLatitude(-90D));
        assertTrue(Validation.isValidLatitude(-60D));
        assertTrue(Validation.isValidLatitude(+90D));
        assertTrue(Validation.isValidLatitude(+70D));
        assertFalse(Validation.isValidLatitude(-91D));
        assertFalse(Validation.isValidLatitude(+91D));
    }

    @Test
    public void testIsValidLongitude() {
        assertTrue(Validation.isValidLongitude(-180D));
        assertTrue(Validation.isValidLongitude(-170D));
        assertFalse(Validation.isValidLongitude(-181D));

        assertTrue(Validation.isValidLongitude(+180D));
        assertTrue(Validation.isValidLongitude(+170D));
        assertFalse(Validation.isValidLongitude(+181D));
    }

    @Test
    public void testIsValidRadius() {
        assertTrue(Validation.isValidRadius(200));
        assertFalse(Validation.isValidRadius(-100));
    }

    @Test
    public void testIsValidLimit() {
        assertTrue(Validation.isValidLimit((short) 10));
        assertFalse(Validation.isValidLimit((short) -10));
    }

}
