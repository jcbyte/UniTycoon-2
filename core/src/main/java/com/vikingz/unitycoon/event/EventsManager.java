package com.vikingz.unitycoon.event;

import com.badlogic.gdx.math.MathUtils;
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
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UiRenderer;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.util.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage random and static events showing and their callbacks.
 */
public class EventsManager {
  UiRenderer uiRenderer;
  GameScreen gameScreen;

  /**
   * Class containing an event and its helpful properties for calling.
   */
  public static class ManagedEvent {
    private final Event event;

    private final int timeCalled;
    private boolean called;

    /**
     * Create a manged event given an event and the time it should be called.
     */
    public ManagedEvent(Event event, int timeCalled) {
      this.event = event;
      this.timeCalled = timeCalled;
      this.called = false;
    }

    public Event getEvent() {
      return event.getEvent();
    }
  }

  /**
   * List of events to be called during the game, this is initialised in the constructor.
   */
  private final List<ManagedEvent> events;

  /**
   * Initialise the event's manager.
   */
  public EventsManager(GameScreen gameScreen) {
    this.gameScreen = gameScreen;
    this.uiRenderer = gameScreen.getUiRenderer();

    List<Class<? extends Event>> goodEvents = List.of(
        GrantEvent.class,
        ScolarEvent.class,
        ChampionshipWinEvent.class
    );
    List<Class<? extends Event>> badEvents = List.of(
        LongboiDeathEvent.class,
        ExamWeekEvent.class,
        FloodingEvent.class,
        EarthquakeEvent.class
    );
    List<Class<? extends Event>> neutralEvents = List.of(
        AlumniVisitEvent.class,
        PeacefulProtestEvent.class,
        UniPartyEvent.class,
        CasinoEvent.class,
        CurriculumChangeEvent.class,
        StrikeEvent.class,
        BigOilEvent.class
    );

    // This contains the time of each random event which will be shown in the game
    // Add random events here
    List<Pair<List<Class<? extends Event>>, Integer[]>> gameEvents = List.of(
        new Pair<>(
            goodEvents,
            new Integer[]{4 * 60, 3 * 60, 1 * 60}
        ),
        new Pair<>(
            badEvents,
            new Integer[]{(int) (3.5 * 60), 2 * 60}
        ),
        new Pair<>(
            neutralEvents,
            new Integer[]{(int) (4.5 * 60), (int) (2.5 * 60), (int) (0.5 * 60)}
        )
    );

    // Update the events list with randomly selected events as defined by `gameEvents`
    events = new ArrayList<>();
    for (Pair<List<Class<? extends Event>>, Integer[]> entry : gameEvents) {
      List<Class<? extends Event>> classList = entry.first;
      for (Integer time : entry.second) {
        // Add a new instance of a randomly generated event at the specific time
        events.add(new ManagedEvent(getRandomEvent(classList), time));
      }
    }

    // Add static events here
    events.add(new ManagedEvent(new GraduationEvent(gameScreen), 1));
  }

  /**
   * Check if any events should be activated, if so display feedback to the user.
   *
   * <p>This should be run every render
   */
  public void render() {
    for (ManagedEvent event : events) {
      if (GameGlobals.ELAPSED_TIME <= event.timeCalled) {
        if (!event.called) {
          gameScreen.setPaused(true);
          uiRenderer.showEvent(event.getEvent());
          event.called = true;
        }
      }
    }
  }

  /**
   * Get a random instance of an event from a list of possible event classes.
   */
  public Event getRandomEvent(List<Class<? extends Event>> eventList) {
    if (eventList.isEmpty()) {
      throw new RuntimeException("There are no events");
    }

    try {
      int randomIndex = MathUtils.random(0, eventList.size() - 1);
      return createEvent(eventList.get(randomIndex));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Create an instance of an event from the class.
   */
  private Event createEvent(Class<? extends Event> eventClass) {
    try {
      return eventClass.getConstructor(GameScreen.class).newInstance(gameScreen);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
