package com.vikingz.unitycoon.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.Pair;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link Pair}.
 */
public class PairTests extends AbstractHeadlessGdxTest {
  @Test
  public void testPair() {
    Pair<Integer, Integer> pair = new Pair<>(11, 15);
    assertEquals(11, pair.first);
    assertEquals(15, pair.second);
  }
}
