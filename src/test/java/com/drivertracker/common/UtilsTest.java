package com.drivertracker.common;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class UtilsTest {

    @Test
    public void testDistance() {
        assertTrue(Utils.distance(28.57294, 28.57115, 77.32601, 77.32580) == 200);
        assertTrue(Utils.distance(28.57294, 28.57121, 77.32601, 77.32524) > 200);
    }

}
