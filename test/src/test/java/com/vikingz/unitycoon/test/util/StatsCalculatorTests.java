package com.vikingz.unitycoon.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.TimeUtil;
import org.junit.jupiter.api.Test;

public class StatsCalculatorTests extends AbstractHeadlessGdxTest {

    @Test
    public void TestgetFormattedSatisfaction(){
        assertEquals("0%", StatsCalculator.getFormattedSatisfaction(0).toString());
        assertEquals("100%", StatsCalculator.getFormattedSatisfaction(100000000).tostring());
        assertEquals("0.45", StatsCalculator.getFormattedSatisfaction(45000));
    }

    @Test
    public void TestcalculateSatisfaction(){
        assertEquals("0", StatsCalculator.calculateSatisfaction(0,4).toString());
        assertEquals("0", StatsCalculator.calculateSatisfaction(200,0).toString());
        assertEquals("3765", StatsCalculator.calculateSatisfaction(562, 6.7).toString());
    }
}
