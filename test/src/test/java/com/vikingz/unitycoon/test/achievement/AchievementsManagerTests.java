package com.vikingz.unitycoon.test.achievement;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.Game;
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

    when(uiRendererMock.showAchievement(any())); // todo get which achivement has been called to verify
    GameGlobals.BALANCE = 15000;
    am.update();
    // todo verify not called
    GameGlobals.BALANCE = 15001;
    am.update();
    // todo verify called

    GameGlobals.SATISFACTION = 1000000;
    am.update();
    // todo verify not called
    GameGlobals.SATISFACTION = 1000001;
    am.update();
    // todo verify called

    GameGlobals.ACADEMIC_BUILDINGS_COUNT = 9;
    am.update();
    // todo verify not called
    GameGlobals.FOOD_BUILDINGS_COUNT = 2;
    am.update();
    // todo verify called

    GameGlobals.STUDENTS = 1000;
    am.update();
    // todo verify not called
    GameGlobals.STUDENTS = 1001;
    am.update();
    // todo verify called

    GameGlobals.RECREATIONAL_BUILDINGS_COUNT = 5;
    am.update();
    // todo verify not called
    GameGlobals.RECREATIONAL_BUILDINGS_COUNT = 6;
    am.update();
    // todo verify called
  }
}
