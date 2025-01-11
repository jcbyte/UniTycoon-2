package com.vikingz.unitycoon.test.global;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link GameConfig}.
 */
public class GameConfigTests extends AbstractHeadlessGdxTest {

  @BeforeEach
  public void setup() {
    GameConfig.resetInstance();
  }

  @Test
  public void testWindowSize() {
    assertEquals(1792, GameConfig.getInstance().getWindowWidth());
    assertEquals(1008, GameConfig.getInstance().getWindowHeight());
  }

  @Test
  public void testVolume() {
    assertEquals(1f, GameConfig.getInstance().getSoundVolumeValue());
    GameConfig.getInstance().setSoundVolumeValue(0.2f);
    assertEquals(0.2f, GameConfig.getInstance().getSoundVolumeValue());

    assertEquals(1f, GameConfig.getInstance().getMusicVolumeValue());
    GameConfig.getInstance().setMusicVolumeValue(0.78f);
    assertEquals(0.78f, GameConfig.getInstance().getMusicVolumeValue());
  }
}
