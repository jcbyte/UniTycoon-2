package com.vikingz.unitycoon.test.achievement;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vikingz.unitycoon.achievement.AchievementsManager;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UiRenderer;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link AchievementsManager}.
 */
public class AchievementsManagerTests extends AbstractHeadlessGdxTest {
  @Test
  public void testAchievementsManager() {
    GameScreen gameScreenMock = mock(GameScreen.class);
    UiRenderer uiRendererMock = mock(UiRenderer.class);
    when(gameScreenMock.getUiRenderer()).thenReturn(uiRendererMock);

    AchievementsManager am = new AchievementsManager(gameScreenMock);

    GameGlobals.BALANCE = 15000;
    am.update();
    verify(uiRendererMock, times(0)).showAchievement(am.achievements[0]);
    verify(gameScreenMock, times(0)).setPaused(true);
    GameGlobals.BALANCE = 15001;
    am.update();
    verify(uiRendererMock, times(1)).showAchievement(am.achievements[0]);
    verify(gameScreenMock, times(1)).setPaused(true);

    GameGlobals.SATISFACTION = 1000000;
    am.update();
    verify(uiRendererMock, times(0)).showAchievement(am.achievements[1]);
    verify(gameScreenMock, times(1)).setPaused(true);
    GameGlobals.SATISFACTION = 1000001;
    am.update();
    verify(uiRendererMock, times(1)).showAchievement(am.achievements[1]);
    verify(gameScreenMock, times(2)).setPaused(true);

    GameGlobals.ACADEMIC_BUILDINGS_COUNT = 9;
    am.update();
    verify(uiRendererMock, times(0)).showAchievement(am.achievements[2]);
    verify(gameScreenMock, times(2)).setPaused(true);
    GameGlobals.FOOD_BUILDINGS_COUNT = 2;
    am.update();
    verify(uiRendererMock, times(1)).showAchievement(am.achievements[2]);
    verify(gameScreenMock, times(3)).setPaused(true);

    GameGlobals.STUDENTS = 1000;
    am.update();
    verify(uiRendererMock, times(0)).showAchievement(am.achievements[3]);
    verify(gameScreenMock, times(3)).setPaused(true);
    GameGlobals.STUDENTS = 1001;
    am.update();
    verify(uiRendererMock, times(1)).showAchievement(am.achievements[3]);
    verify(gameScreenMock, times(4)).setPaused(true);

    GameGlobals.RECREATIONAL_BUILDINGS_COUNT = 5;
    am.update();
    verify(uiRendererMock, times(0)).showAchievement(am.achievements[4]);
    verify(gameScreenMock, times(4)).setPaused(true);
    GameGlobals.RECREATIONAL_BUILDINGS_COUNT = 6;
    am.update();
    verify(uiRendererMock, times(1)).showAchievement(am.achievements[4]);
    verify(gameScreenMock, times(5)).setPaused(true);
  }
}
