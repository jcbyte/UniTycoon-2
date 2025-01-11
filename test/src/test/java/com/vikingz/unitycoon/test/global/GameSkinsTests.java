package com.vikingz.unitycoon.test.global;

import static org.mockito.Mockito.mock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.global.GameSkins;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link GameSkins}.
 */
public class GameSkinsTests {
  /**
   * Initialise `Gdx.files` for loading assets.
   */
  @BeforeAll
  public static void setupAll() {
    // Initialise Gdx.files
    Gdx.files = new HeadlessFiles();
  }

  @Test
  public void testGetDefaultSkin() {
    GameSkins gs = new GameSkins();
    Skin d = gs.getDefaultSkin();

    // todo check this
  }
}
