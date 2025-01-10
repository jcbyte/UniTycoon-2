package com.vikingz.unitycoon.test.audio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.vikingz.unitycoon.audio.GameMusic;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


/**
 * Tests checking {@link com.vikingz.unitycoon.audio.GameMusic} .
 */
public class GameMusicTests extends AbstractHeadlessGdxTest {
  private static Music backgroundMusicMock;

  /**
   * Initialise `Gdx.files` for loading assets.
   * Mock `Gdx.audio` and our own Music to allow for testing.
   * Initialise GameMusic.
   */
  @BeforeAll
  public static void setupAll() {
    // Initialise Gdx.files
    Gdx.files = new HeadlessFiles();

    // Mock Gdx.audio
    backgroundMusicMock = mock(Music.class);
    Audio audioMock = mock(Audio.class);
    when(audioMock.newMusic(any())).thenReturn(backgroundMusicMock);
    Gdx.audio = audioMock;

    // Initialise GameMusic with our injected backgroundMusicMock
    GameMusic gm = new GameMusic();
    gm.init();
  }

  @Test
  public void testInit() {
    // From gm.init() in setupAll()
    verify(backgroundMusicMock, times(1)).setLooping(true);
  }

  @Test
  public void testPlay() {
    GameMusic.play();
    verify(backgroundMusicMock, times(1)).play();
  }

  @Test
  public void testVolume() {
    // Reset GameConfig instance to ensure test and game are in sync
    GameConfig.resetInstance();

    GameMusic.setVolume(0.7f);
    assertEquals(0.7f, GameMusic.getVolume());
    assertEquals(0.7f, GameMusic.volume);
    assertEquals(0.7f, GameConfig.getInstance().musicVolumeValue);
    verify(backgroundMusicMock, times(2)).play();
  }
}
