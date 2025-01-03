package com.vikingz.unitycoon.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.StatsCalculator;
import com.vikingz.unitycoon.util.TimeUtil;
import org.junit.jupiter.api.Test;

public class StatsCalculatorTests extends AbstractHeadlessGdxTest {

    @Test
    public void TestgetFormattedSatisfaction(){
        assertEquals("0.00%", StatsCalculator.getFormattedSatisfaction(0));
        assertEquals("100.00%", StatsCalculator.getFormattedSatisfaction(100000000));
        assertEquals("0.45%", StatsCalculator.getFormattedSatisfaction(45000));
    }

    @Test
    public void TestcalculateSatisfaction(){
        assertEquals(0, StatsCalculator.calculateSatisfaction(0,4));
        assertEquals(0, StatsCalculator.calculateSatisfaction(200,0));
        assertEquals(3765, StatsCalculator.calculateSatisfaction(562, 6.7f));
    }
}
