package com.vikingz.unitycoon.test.event.events;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.vikingz.unitycoon.event.events.AlumniVisitEvent;
import com.vikingz.unitycoon.event.events.BigOilEvent;
import com.vikingz.unitycoon.event.events.CasinoEvent;
import com.vikingz.unitycoon.event.events.ChampionshipWinEvent;
import com.vikingz.unitycoon.event.events.CurriculumChangeEvent;
import com.vikingz.unitycoon.event.events.EarthquakeEvent;
import com.vikingz.unitycoon.event.events.ExamWeekEvent;
import com.vikingz.unitycoon.event.events.FloodingEvent;
import com.vikingz.unitycoon.event.events.GraduationEvent;
import com.vikingz.unitycoon.event.events.GrantEvent;
import com.vikingz.unitycoon.event.events.LongboiDeathEvent;
import com.vikingz.unitycoon.event.events.PeacefulProtestEvent;
import com.vikingz.unitycoon.event.events.ScolarEvent;
import com.vikingz.unitycoon.event.events.StrikeEvent;
import com.vikingz.unitycoon.event.events.UniPartyEvent;
import com.vikingz.unitycoon.render.BuildingRenderer;
import com.vikingz.unitycoon.render.GameRenderer;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.Test;

/**
 * Tests checking all Events in the {@link com.vikingz.unitycoon.event.events} package.
 */
public class EventsTests extends AbstractHeadlessGdxTest {
  @Test
  public void testTests() {
    GameScreen gameScreenMock = mock(GameScreen.class);
    GameRenderer gameRendererMock = mock(GameRenderer.class);
    BuildingRenderer buildingRendererMock = mock(BuildingRenderer.class);
    when(gameScreenMock.getGameRenderer()).thenReturn(gameRendererMock);
    when(gameRendererMock.getBuildingRenderer()).thenReturn(buildingRendererMock);

    assertNotNull(new AlumniVisitEvent(gameScreenMock).getEvent());
    assertNotNull(new BigOilEvent(gameScreenMock).getEvent());
    assertNotNull(new CasinoEvent(gameScreenMock).getEvent());
    assertNotNull(new ChampionshipWinEvent(gameScreenMock).getEvent());
    assertNotNull(new CurriculumChangeEvent(gameScreenMock).getEvent());
    assertNotNull(new EarthquakeEvent(gameScreenMock).getEvent());
    assertNotNull(new ExamWeekEvent(gameScreenMock).getEvent());
    assertNotNull(new FloodingEvent(gameScreenMock).getEvent());
    assertNotNull(new GraduationEvent(gameScreenMock).getEvent());
    assertNotNull(new GrantEvent(gameScreenMock).getEvent());
    assertNotNull(new LongboiDeathEvent(gameScreenMock).getEvent());
    assertNotNull(new PeacefulProtestEvent(gameScreenMock).getEvent());
    assertNotNull(new ScolarEvent(gameScreenMock).getEvent());
    assertNotNull(new StrikeEvent(gameScreenMock).getEvent());
    assertNotNull(new UniPartyEvent(gameScreenMock).getEvent());
  }
}
