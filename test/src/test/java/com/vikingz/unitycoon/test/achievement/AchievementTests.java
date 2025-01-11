package com.vikingz.unitycoon.test.achievement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.achievement.Achievement;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link Achievement}.
 */
public class AchievementTests extends AbstractHeadlessGdxTest {
  @Test
  public void testAchievement() {
    boolean aRes = false; // todo get reference
    Achievement a = new Achievement("a", () -> aRes, null, () -> {}, "ar");
    assertFalse(a.hasAchieved());
    assertFalse(a.calculate());
    assertFalse(a.hasAchieved());
    aRes = true;
    assertTrue(a.calculate());
    assertTrue(a.hasAchieved());
    assertFalse(a.calculate());
    assertTrue(a.hasAchieved());
  }
}
