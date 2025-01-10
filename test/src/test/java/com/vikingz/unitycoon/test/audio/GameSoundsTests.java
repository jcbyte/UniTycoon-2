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
   * Mock `Gdx.audio` and our own Sound's to allow for testing.
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

    verify(placedBuildingSoundMock, times(0)).play(0.5f);
    GameSounds.playPlacedBuilding();
    verify(placedBuildingSoundMock, times(1)).play(0.5f);
    GameSounds.playPlacedBuilding();
    verify(placedBuildingSoundMock, times(2)).play(0.5f);
    GameSounds.playPlacedBuilding();
    verify(placedBuildingSoundMock, times(3)).play(0.5f);
    GameSounds.playPlacedBuilding();
    verify(placedBuildingSoundMock, times(4)).play(0.5f);
  }

  @Test
  public void testPlayPlaceError() {
    GameSounds.setVolume(0.5f);

    verify(placeErrorSoundMock, times(0)).play(0.5f);
    GameSounds.playPlaceError();
    verify(placeErrorSoundMock, times(1)).play(0.5f);
    GameSounds.playPlaceError();
    verify(placeErrorSoundMock, times(2)).play(0.5f);
    GameSounds.playPlaceError();
    verify(placeErrorSoundMock, times(3)).play(0.5f);
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
