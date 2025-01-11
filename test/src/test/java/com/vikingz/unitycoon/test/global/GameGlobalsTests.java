package com.vikingz.unitycoon.test.global;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link GameGlobals}.
 */
public class GameGlobalsTests extends AbstractHeadlessGdxTest {
  @Test
  public void testGameGlobals() {
    assertNotNull(GameGlobals.map1Texture);
    assertNotNull(GameGlobals.map2Texture);
    assertNotNull(GameGlobals.map3Texture);
    assertNotNull(GameGlobals.backGroundDrawable);
    assertNotNull(GameGlobals.map1Draw);
    assertNotNull(GameGlobals.map2Draw);
    assertNotNull(GameGlobals.map3Draw);
    assertNotNull(GameGlobals.mapArray);
  }

  @Test
  public void testResetGameGlobals() {
    GameGlobals.resetGlobals(700);

    assertEquals(700, GameGlobals.ELAPSED_TIME);
    assertEquals(0, GameGlobals.ACADEMIC_BUILDINGS_COUNT);
    assertEquals(0, GameGlobals.ACCOMMODATION_BUILDINGS_COUNT);
    assertEquals(0, GameGlobals.RECREATIONAL_BUILDINGS_COUNT);
    assertEquals(0, GameGlobals.FOOD_BUILDINGS_COUNT);
    assertEquals(0, GameGlobals.SATISFACTION);
    assertEquals(0, GameGlobals.STUDENTS);
    assertEquals(500, GameGlobals.BALANCE);
  }

}
