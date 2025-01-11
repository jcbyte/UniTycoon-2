package com.vikingz.unitycoon.test.event;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.vikingz.unitycoon.event.EventsManager;
import com.vikingz.unitycoon.global.GameGlobals;
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
    when(gameScreenMock.getUiRenderer()).thenReturn(uiRendererMock);

    GameGlobals.resetGlobals(5 * 60);
    EventsManager em = new EventsManager(gameScreenMock);
    GameGlobals.ELAPSED_TIME = (4 * 60) + 25;
    em.render();
    // todo testing
  }

  // todo test getRandomEvent
}
