package com.vikingz.unitycoon;

import com.badlogic.gdx.Game;
import com.vikingz.unitycoon.audio.GameMusic;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.screens.ScreenMultiplexer;
import com.vikingz.unitycoon.util.FileHandler;

/**
 * Main class.
 *
 * <p>Entry point of the game
 */
public class Main extends Game {
  @Override
  public void create() {
    GameConfigManager.loadGameConfig();

    new GameMusic().init();
    GameMusic.play();
    FileHandler.loadBuildings();
    ScreenMultiplexer.init(this);

    // If SKIP_MENUS is enabled in GameConfig, the game will load straight into the game.
    ScreenMultiplexer.switchScreens(ScreenMultiplexer.Screens.MENU);
  }
}
