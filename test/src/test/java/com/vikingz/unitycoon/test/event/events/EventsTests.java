package com.vikingz.unitycoon.test.event.events;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.vikingz.unitycoon.event.Event;
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
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.render.BuildingRenderer;
import com.vikingz.unitycoon.render.GameRenderer;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.Test;
import java.util.List;

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

    List<Class<? extends Event>> events = List.of(
        AlumniVisitEvent.class,
        BigOilEvent.class,
        CasinoEvent.class,
        ChampionshipWinEvent.class,
        CurriculumChangeEvent.class,
        EarthquakeEvent.class,
        ExamWeekEvent.class,
        FloodingEvent.class,
        GraduationEvent.class,
        GrantEvent.class,
        LongboiDeathEvent.class,
        PeacefulProtestEvent.class,
        ScolarEvent.class,
        StrikeEvent.class,
        UniPartyEvent.class
    );

    for (Class<? extends Event> eventClass : events) {
      try {
        Event event = eventClass.getConstructor(GameScreen.class).newInstance(gameScreenMock).getEvent();
        event.getOpt1().getAction().run();
        if (event.hasChoice()) {
          event.getOpt2().getAction().run();
        }
      } catch (Exception e) {
        throw new RuntimeException("Failed to instantiate: " + eventClass.getSimpleName(), e);
      }
    }
  }
}
