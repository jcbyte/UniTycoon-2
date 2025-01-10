package com.vikingz.unitycoon.global;

import com.vikingz.unitycoon.util.LeaderboardUtil;
import java.io.Serializable;

/**
 * This class contains all the config for the game, things such as width, height, sounds volume etc.
 * This class is also used by the GameConfig Manager to save the current config so that when the
 * user reloads the game their settings are still present.
 *
 * <p>Singleton class, meaning we instantiate it once and then that instance can be fetched
 * statically by calling GameConfig.getInstance() and then from this instance we can retrieve
 * settings. We have chose to do this because by not making it static we are able to implement
 * Serializable which lets us save the GameConfig class as is without any other logic.
 */
public class GameConfig implements Serializable {

  // Constants must be public to serialise
  private int windowWidth;
  private int windowHeight;

  public float soundVolumeValue;
  public float musicVolumeValue;

  public LeaderboardUtil.LeaderboardRecord[] leaderboard;

  // 31.5 rows
  // 56 cols

  // The single instance of GameConfig (eager initialization)
  private static final GameConfig DEFAULT_GAME_CONFIG = new GameConfig(
      1792, 1008, 1f, 1f, LeaderboardUtil.generateBlankLeaderboard(5)
  );
  private static GameConfig INSTANCE = DEFAULT_GAME_CONFIG;

  /**
   * Reset the single instance of GameConfig.
   */
  public static void resetInstance() {
    INSTANCE = DEFAULT_GAME_CONFIG;
  }

  // For JSON deserializing
  private GameConfig() {
  }

  // Private constructor to prevent instantiation from outside
  private GameConfig(int width, int height,
                     float soundVolumeValue, float musicVolumeValue,
                     LeaderboardUtil.LeaderboardRecord[] leaderboard) {
    this.windowWidth = width;
    this.windowHeight = height;
    this.soundVolumeValue = soundVolumeValue;
    this.musicVolumeValue = musicVolumeValue;
    this.leaderboard = leaderboard.clone();

    // Sort the leaderboard to ensure it is in order
    LeaderboardUtil.sortLeaderboard(this.leaderboard);
  }

  /**
   * Method to set the single instance of GameConfig.
   *
   * <p>Used when loading in settings
   */
  public void setInstance(GameConfig conf) {
    INSTANCE = conf;
  }

  /**
   * Method to get the single instance of GameConfig.
   */
  public static GameConfig getInstance() {
    return INSTANCE;
  }


  public int getWindowWidth() {
    return windowWidth;
  }

  public int getWindowHeight() {
    return windowHeight;
  }

  public LeaderboardUtil.LeaderboardRecord[] getLeaderboard() {
    return leaderboard;
  }

  /**
   * Check if this score could be in the leaderboard.
   */
  public boolean isOnLeaderboard(int score) {
    return LeaderboardUtil.onLeaderboard(leaderboard, score);
  }

  public float getSoundVolumeValue() {
    return soundVolumeValue;
  }

  public float getMusicVolumeValue() {
    return musicVolumeValue;
  }

  public void setSoundVolumeValue(float soundVolumeValue) {
    this.soundVolumeValue = soundVolumeValue;
  }

  public void setMusicVolumeValue(float musicVolumeValue) {
    this.musicVolumeValue = musicVolumeValue;
  }
}




