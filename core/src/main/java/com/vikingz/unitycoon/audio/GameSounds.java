package com.vikingz.unitycoon.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.vikingz.unitycoon.annotations.DebugMethodExcludeGeneratedJacoco;
import com.vikingz.unitycoon.global.GameConfig;
import java.util.Random;

/**
 * This class loads and plays all the sounds effects for the entire game.
 */
public class GameSounds {

  // Load the sounds fx
  private static Sound placeBuilding1 = Gdx.audio.newSound(
      Gdx.files.internal("audio/place_1.ogg")
  );
  private static Sound placeBuilding2 = Gdx.audio.newSound(
      Gdx.files.internal("audio/place_2.ogg")
  );
  private static Sound placeBuilding3 = Gdx.audio.newSound(
      Gdx.files.internal("audio/place_3.ogg")
  );
  private static Sound placeError1 = Gdx.audio.newSound(
      Gdx.files.internal("audio/place_error_1.ogg")
  );
  private static Sound placeError2 = Gdx.audio.newSound(
      Gdx.files.internal("audio/place_error_2.ogg")
  );

  /**
   * Method to allow us to inject our sounds for testing.
   */
  @DebugMethodExcludeGeneratedJacoco
  public static void setBuildingSounds(Sound placedBuildingSounds, Sound placeErrorSounds) {
    placeBuilding1 = placedBuildingSounds;
    placeBuilding2 = placedBuildingSounds;
    placeBuilding3 = placedBuildingSounds;
    placeError1 = placeErrorSounds;
    placeError2 = placeErrorSounds;
  }

  //Sets the volume of the GameSounds to be played
  public static float volume = GameConfig.getInstance().getSoundVolumeValue();

  /**
   * Plays the placed building sound.
   */
  public static void playPlacedBuilding() {
    int randNum = new Random().nextInt(1, 4);
    switch (randNum) {
      case 1 -> placeBuilding1.play(volume);
      case 2 -> placeBuilding2.play(volume);
      case 3 -> placeBuilding3.play(volume);
      default -> {
      }
    }
  }


  /**
   * Plays the error sound.
   */
  public static void playPlaceError() {
    int randNum = new Random().nextInt(1, 3);

    switch (randNum) {
      case 1 -> placeError1.play(volume);
      case 2 -> placeError2.play(volume);
      default -> {
      }
    }
  }

  /**
   * Gets the volume.
   *
   * @return Float Volume level
   */
  public static float getVolume() {
    return volume;
  }

  /**
   * Sets the volume level.
   *
   * @param volume New volume level
   */
  public static void setVolume(float volume) {
    GameSounds.volume = volume;
    GameConfig.getInstance().setSoundVolumeValue(volume);
  }
}
