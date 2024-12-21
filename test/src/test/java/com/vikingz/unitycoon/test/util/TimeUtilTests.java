package com.vikingz.unitycoon.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.TimeUtil;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link TimeUtil}.
 */
public class TimeUtilTests extends AbstractHeadlessGdxTest {
  @Test
  public void testValidSecondsToMinutes() {
    assertEquals(new TimeUtil.Time(4, 2), TimeUtil.secondsToMinSecs(124));
    assertEquals(new TimeUtil.Time(0, 0), TimeUtil.secondsToMinSecs(0));
    assertEquals(new TimeUtil.Time(48, 157), TimeUtil.secondsToMinSecs(9468));
  }

  @Test
  public void testValidSecondsToMinutesString() {
    assertEquals("01:01", TimeUtil.secondsToMinSecs(61).toString());
    assertEquals("00:00", TimeUtil.secondsToMinSecs(0).toString());
    assertEquals("1315:16", TimeUtil.secondsToMinSecs(78916).toString());
  }

  @Test
  public void testInvalidSecondsToMinutes() {
    assertThrows(IllegalArgumentException.class, () -> TimeUtil.secondsToMinSecs(-7));
  }
}
