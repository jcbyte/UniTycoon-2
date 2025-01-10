package com.vikingz.unitycoon.test.audio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.vikingz.unitycoon.audio.GameSounds;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link com.vikingz.unitycoon.audio.GameSounds} .
 */
public class GameSoundsTests extends AbstractHeadlessGdxTest {
  private static Sound placedBuildingSoundMock;
  private static Sound placeErrorSoundMock;

  /**
   * Initialise `Gdx.files` for loading assets.
   * Mock `Gdx.audio` to allow for testing.
   * Re-initialise GameConfig for consistency.
   */
  @BeforeAll
  public static void setupAll() {
    // Initialise Gdx.files
    Gdx.files = new HeadlessFiles();

    // Mock Gdx.audio
    placedBuildingSoundMock = mock(Sound.class);
    placeErrorSoundMock = mock(Sound.class);
    Gdx.audio = mock(Audio.class);
    GameSounds.setBuildingSounds(placedBuildingSoundMock, placeErrorSoundMock);
  }

  @Test
  public void testPlayPlacedBuilding() {
    GameSounds.setVolume(0.5f);
    GameSounds.playPlacedBuilding();

    verify(placedBuildingSoundMock, times(1)).play(0.5f);
  }

  @Test
  public void testPlayPlaceError() {
    GameSounds.setVolume(0.5f);
    GameSounds.playPlaceError();

    verify(placeErrorSoundMock, times(1)).play(0.5f);
  }

  @Test
  public void testVolume() {
    // Reset GameConfig instance to ensure test and game are in sync
    GameConfig.resetInstance();

    GameSounds.setVolume(0.7f);
    assertEquals(0.7f, GameSounds.getVolume());
    assertEquals(0.7f, GameSounds.volume);
    assertEquals(0.7f, GameConfig.getInstance().soundVolumeValue);
  }
}
