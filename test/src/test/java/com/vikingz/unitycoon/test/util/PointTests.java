package com.vikingz.unitycoon.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.Point;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link Point}.
 */
public class PointTests extends AbstractHeadlessGdxTest {

  @Test
  public void testPointGet() {
    Point thing = new Point(4, 10);
    assertEquals(4, thing.getX());
    assertEquals(10, thing.getY());
  }

  @Test
  public void testPointSet() {
    Point thing = new Point(0, 0);
    thing.setX(5);
    thing.setY(10);
    assertEquals(5, thing.getX());
    assertEquals(10, thing.getY());
    thing.setX(15);
    assertEquals(15, thing.getX());
    assertEquals(10, thing.getY());
  }

}
