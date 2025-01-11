package com.vikingz.unitycoon.test.event.events;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.vikingz.unitycoon.event.EventsManager;
import com.vikingz.unitycoon.event.events.AlumniVisitEvent;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UiRenderer;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.Test;

public class EventsTests extends AbstractHeadlessGdxTest {
  @Test
  public void testTests() {
    GameScreen gameScreenMock = mock(GameScreen.class);

    EventsManager em = new EventsManager(gameScreenMock);

    AlumniVisitEvent e = new AlumniVisitEvent(gameScreenMock);
    assertNotNull(e.getEvent());

    // todo this for all files in package
  }
}
