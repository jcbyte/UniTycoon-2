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
  public void testIntPair() {
    Pair<Integer, Integer> pair = new Pair<>(11, 15);
    assertEquals(11, pair.first);
    assertEquals(15, pair.second);
    pair.first = 45;
    assertEquals(45, pair.first);
    assertEquals(15, pair.second);
  }

  @Test
  public void testIntStringPair() {
    Pair<Integer, String> pair = new Pair<>(11, "Hello");
    assertEquals(11, pair.first);
    assertEquals("Hello", pair.second);
    pair.second = "World!";
    assertEquals(11, pair.first);
    assertEquals("World!", pair.second);
  }
}
