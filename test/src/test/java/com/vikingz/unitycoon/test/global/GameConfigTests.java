package com.vikingz.unitycoon.test.global;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
  }

  @Test
  public void testLoadGameConfig() {
    String savedJsonString = "";

    Preferences preferencesMock = mock(Preferences.class);
    when(preferencesMock.getString("config")).thenReturn(savedJsonString);
    mockPreferences(preferencesMock);

    GameConfigManager.loadGameConfig();

    // todo check same

    savedJsonString = "{\"invalid json\",]]";
    when(preferencesMock.getString("config")).thenReturn(savedJsonString);

    // todo check throw

    savedJsonString = ""; // todo add valid
    when(preferencesMock.getString("config")).thenReturn(savedJsonString);

    // todo check sets correctly
  }

  @Test
  public void testSaveGameConfig() {
    Preferences preferencesMock = mock(Preferences.class);
    // when(preferencesMock.putString(any(), any())); // todo get parameters used and assert these
    mockPreferences(preferencesMock);

    GameConfigManager.saveGameConfig();

    verify(preferencesMock, times(1)).flush();
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
