package com.vikingz.unitycoon.test.audio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.badlogic.gdx.files.FileHandle;
import com.vikingz.unitycoon.audio.GameSounds;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Tests checking {@link com.vikingz.unitycoon.audio.GameSounds} .
 */
public class GameSoundsTests extends AbstractHeadlessGdxTest {
  private static Sound soundMock1;
  private static Sound soundMock2;
  private static Sound soundMock3;

  /**
   * Initialise `Gdx.files` for loading assets.
   * Mock `Gdx.audio` to allow for testing.
   */
  @BeforeAll
  public static void setupAll() {
    // Initialise Gdx.files
    Gdx.files = new HeadlessFiles();

    // Mock Gdx.audio
    soundMock1 = mock(Sound.class);
    soundMock2 = mock(Sound.class);
    soundMock3 = mock(Sound.class);

    Gdx.audio = mock(Audio.class);
    when(Gdx.audio.newSound(any(FileHandle.class)))
        .thenReturn(soundMock1, soundMock2, soundMock3);
  }

  @Test
  public void testPlayPlacedBuilding() {
    GameSounds.setVolume(0.5f);
    GameSounds.playPlacedBuilding();

    int totalCalls = (Mockito.mockingDetails(soundMock1).getInvocations().size()
        + Mockito.mockingDetails(soundMock2).getInvocations().size()
        + Mockito.mockingDetails(soundMock3).getInvocations().size());
    assertEquals(1, totalCalls);
  }

  @Test
  public void testPlayPlaceError() {
    GameSounds.setVolume(0.5f);
    GameSounds.playPlaceError();

    int totalCalls = (Mockito.mockingDetails(soundMock1).getInvocations().size()
        + Mockito.mockingDetails(soundMock2).getInvocations().size()
        + Mockito.mockingDetails(soundMock3).getInvocations().size());
    assertEquals(1, totalCalls);
  }

  @Test
  public void testVolume() {
    GameSounds.setVolume(0.7f);
    assertEquals(0.7f, GameSounds.getVolume());
    assertEquals(0.7f, GameSounds.volume);
    assertEquals(0.7f, GameConfig.getInstance().soundVolumeValue);
  }
}
