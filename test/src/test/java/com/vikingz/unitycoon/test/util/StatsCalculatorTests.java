package com.vikingz.unitycoon.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.StatsCalculator;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link StatsCalculator}.
 */
public class StatsCalculatorTests extends AbstractHeadlessGdxTest {

  @Test
  public void testGetFormattedSatisfaction() {
    assertEquals("0.00%", StatsCalculator.getFormattedSatisfaction(0));
    assertEquals("100.00%", StatsCalculator.getFormattedSatisfaction(100000000));
    assertEquals("0.45%", StatsCalculator.getFormattedSatisfaction(45000));
    assertEquals("0.00%", StatsCalculator.getFormattedSatisfaction(-9000));
    assertEquals("100.00%", StatsCalculator.getFormattedSatisfaction(123400000));
  }

  @Test
  public void testCalculateSatisfaction() {
    assertEquals(0, StatsCalculator.calculateSatisfaction(0, 4));
    assertEquals(0, StatsCalculator.calculateSatisfaction(200, 0));
    assertEquals(3765, StatsCalculator.calculateSatisfaction(562, 6.7f));
  }

  @Test
  public void testCalculateProfitMade() {
    assertEquals(50, StatsCalculator.calculateProfitMade(50));
    assertEquals(0, StatsCalculator.calculateProfitMade(0));
    assertEquals(-42, StatsCalculator.calculateProfitMade(-42));
  }
}
