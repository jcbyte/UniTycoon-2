package com.vikingz.unitycoon.test.event;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vikingz.unitycoon.event.EventsManager;
import com.vikingz.unitycoon.event.events.GraduationEvent;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.BuildingRenderer;
import com.vikingz.unitycoon.render.GameRenderer;
import com.vikingz.unitycoon.render.UiRenderer;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.Test;

/**
 * Test's checking {@link EventsManager}.
 */
public class EventsManagerTests extends AbstractHeadlessGdxTest {
  @Test
  public void testEventsManager() {
    GameScreen gameScreenMock = mock(GameScreen.class);
    UiRenderer uiRendererMock = mock(UiRenderer.class);
    GameRenderer gameRendererMock = mock(GameRenderer.class);
    BuildingRenderer buildingRendererMock = mock(BuildingRenderer.class);
    when(gameScreenMock.getUiRenderer()).thenReturn(uiRendererMock);
    when(gameScreenMock.getGameRenderer()).thenReturn(gameRendererMock);
    when(gameRendererMock.getBuildingRenderer()).thenReturn(buildingRendererMock);

    GameGlobals.resetGlobals(5 * 60);
    EventsManager em = new EventsManager(gameScreenMock);
    em.render();
    verify(uiRendererMock, times(0)).showEvent(any());

    GameGlobals.ELAPSED_TIME = (4 * 60) + 25;
    em.render();
    verify(uiRendererMock, times(1)).showEvent(any());

    GameGlobals.ELAPSED_TIME = 0;
    em.render();
    verify(uiRendererMock, times(9)).showEvent(any());
    verify(uiRendererMock, times(1)).showEvent(isA(GraduationEvent.class));
  }

  // todo test getRandomEvent
}
