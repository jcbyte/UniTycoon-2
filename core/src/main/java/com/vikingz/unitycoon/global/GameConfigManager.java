package com.vikingz.unitycoon.global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;

/**
 * This class allows us to save the GameConfig to a json file in player prefs.
 * It contains methods for saving and loading a game configuration.
 */
public class GameConfigManager {
  /**
   * Sets game to fullscreen.
   */
  public static void setFullScreen() {
    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
  }

  /**
   * Sets game to a windowed screen.
   */
  public static void setWindowScreen() {
    Gdx.graphics.setWindowedMode(
        GameConfig.getInstance().getWindowWidth(), GameConfig.getInstance().getWindowHeight()
    );
  }

  /**
   * Returns the display mode string output of the fullScreen or gets current windowed resolution.
   *
   * @return String WIDTH x HEIGHT
   */
  public static String getFriendlyWindowSize() {
    Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
    if (Gdx.graphics.isFullscreen()) {
      return displayMode.toString();
    }
    return String.format(
        "%dx%d",
        GameConfig.getInstance().getWindowWidth(),
        GameConfig.getInstance().getWindowHeight()
    );
  }

  /**
   * Saves GameConfig Object to prefs as JSON,
   * to save settings and leaderboard.
   */
  public static void saveGameConfig() {
    Json json = new Json();
    String configString = json.toJson(GameConfig.getInstance());
    Preferences prefs = Gdx.app.getPreferences("prefs");
    prefs.putString("config", configString);

    prefs.flush();
  }

  /**
   * Loads GameConfig Object from JSON in prefs,
   * to load existing settings and leaderboard.
   */
  public static void loadGameConfig() {
    GameConfig conf;

    Json json = new Json();
    Preferences prefs = Gdx.app.getPreferences("prefs");

    prefs.clear(); // todo THIS NEEDS TO BE REMOVED BUT CURRENTLY THE JSON IS INVALID

    String configString = prefs.getString("config");
    if (configString.isEmpty()) {
      System.out.println("Saved config not found");
      return;
    }
    conf = json.fromJson(GameConfig.class, configString);
    if (conf == null) {
      System.err.println("Error demoralising config");
      return;
    }

    GameConfig.getInstance().setInstance(conf);
  }
}
