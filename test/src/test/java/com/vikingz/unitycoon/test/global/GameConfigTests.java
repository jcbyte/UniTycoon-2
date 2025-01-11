package com.vikingz.unitycoon.test.global;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Preferences;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link GameConfigManager} and {@link GameConfig}.
 */
public class GameConfigTests extends AbstractHeadlessGdxTest {
  private Graphics graphicsMock;

  /**
   * Reset GameConfig instance to ensure test and game are in sync.
   */
  @BeforeEach
  public void setup() {
    GameConfig.resetInstance();

    graphicsMock = mock(Graphics.class);
    Gdx.graphics = graphicsMock;
  }

  @Test
  public void testSetFullscreen() {
    GameConfigManager.setFullScreen();
    verify(Gdx.graphics, times(1)).setFullscreenMode(Gdx.graphics.getDisplayMode());
  }

  @Test
  public void testSetWindowScreen() {
    GameConfigManager.setWindowScreen();
    verify(Gdx.graphics, times(1)).setWindowedMode(1792, 1008);

  }

  @Test
  public void testGetFriendlyWindowSize() {
    when(graphicsMock.isFullscreen()).thenReturn(false);
    assertEquals("1792x1008", GameConfigManager.getFriendlyWindowSize());

    when(graphicsMock.isFullscreen()).thenReturn(true);
    assertEquals("Fullscreen", GameConfigManager.getFriendlyWindowSize());
  }

  /**
   * Mock `Gdx.app` with a custom preferences.
   */
  private void mockPreferences(Preferences mockedPreferences) {
    Application appMock = mock(Application.class);
    when(appMock.getPreferences(any())).thenReturn(mockedPreferences);
    Gdx.app = appMock;
  }

  @Test
  public void testLoadGameConfig() {
    Preferences preferencesMock = mock(Preferences.class);
    mockPreferences(preferencesMock);

    String savedJsonString = "";
    when(preferencesMock.getString("config")).thenReturn(savedJsonString);
    GameConfigManager.loadGameConfig();
    assertEquals(1792, GameConfig.getInstance().getWindowWidth());
    assertEquals(1008, GameConfig.getInstance().getWindowHeight());
    assertEquals(1f, GameConfig.getInstance().getSoundVolumeValue());
    assertEquals(1f, GameConfig.getInstance().getMusicVolumeValue());
    assertEquals(5, GameConfig.getInstance().getLeaderboard().length);

    savedJsonString = "{\"invalid json\",]]";
    when(preferencesMock.getString("config")).thenReturn(savedJsonString);
    GameConfigManager.loadGameConfig();
    assertEquals(1792, GameConfig.getInstance().getWindowWidth());
    assertEquals(1008, GameConfig.getInstance().getWindowHeight());
    assertEquals(1f, GameConfig.getInstance().getSoundVolumeValue());
    assertEquals(1f, GameConfig.getInstance().getMusicVolumeValue());
    assertEquals(5, GameConfig.getInstance().getLeaderboard().length);

    savedJsonString = "{windowWidth:1000,windowHeight:600,soundVolumeValue:0.7,musicVolumeValue:0.4,leaderboard:[{},{}]}";
    when(preferencesMock.getString("config")).thenReturn(savedJsonString);
    GameConfigManager.loadGameConfig();
    assertEquals(1000, GameConfig.getInstance().getWindowWidth());
    assertEquals(600, GameConfig.getInstance().getWindowHeight());
    assertEquals(0.7f, GameConfig.getInstance().getSoundVolumeValue());
    assertEquals(0.4f, GameConfig.getInstance().getMusicVolumeValue());
    assertEquals(2, GameConfig.getInstance().getLeaderboard().length);
  }

  @Test
  public void testSaveGameConfig() {
    Preferences preferencesMock = mock(Preferences.class);
    mockPreferences(preferencesMock);

    GameConfigManager.saveGameConfig();
    verify(preferencesMock, times(1)).putString(eq("config"), eq("{windowWidth:1792,windowHeight:1008,soundVolumeValue:1,musicVolumeValue:1,leaderboard:[{},{},{},{},{}]}"));
    verify(preferencesMock, times(1)).flush();

    GameConfig.getInstance().setSoundVolumeValue(0.1f);
    GameConfig.getInstance().setMusicVolumeValue(0.6f);
    GameConfigManager.saveGameConfig();
    verify(preferencesMock, times(1)).putString(eq("config"), eq("{windowWidth:1792,windowHeight:1008,soundVolumeValue:0.1,musicVolumeValue:0.6,leaderboard:[{},{},{},{},{}]}"));
    verify(preferencesMock, times(2)).flush();
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
