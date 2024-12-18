package com.vikingz.unitycoon.util;

import com.badlogic.gdx.math.MathUtils;
import com.vikingz.unitycoon.events.*;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UIRenderer;
import com.vikingz.unitycoon.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class EventsManager {
    UIRenderer uiRenderer;
    GameScreen gameScreen;

    /**
     * Class containing an event and its helpful properties for calling
     */
    public static class ManagedEvent
    {
        private final Event event;

        public final int timeCalled;
        public boolean called;

        public ManagedEvent(Event event, int timeCalled)
        {
            this.event = event;
            this.timeCalled = timeCalled;
            this.called = false;
        }

        public Event getEvent()
        {
            return event.getEvent();
        }
    }

    private List<ManagedEvent> events;

    public EventsManager(GameScreen gameScreen)
    {
        this.gameScreen = gameScreen;
        this.uiRenderer = gameScreen.getUIRenderer();

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
            StrikeEvent.class
        );

        // This contains the time of each random event which will be shown in the game
        List<Pair<List<Class<? extends Event>>, Integer[]>> gameEvents = List.of(
            new Pair<>(goodEvents, new Integer[] { 4 * 60, 3 * 60, 1 * 60 }),
            new Pair<>(badEvents, new Integer[] { (int)(3.5 * 60), 2 * 60 }),
            new Pair<>(neutralEvents, new Integer[] { (int)(4.5 * 60), (int)(2.5 * 60), (int)(0.5 * 60) })
        );

        events = new ArrayList<>();
        for (Pair<List<Class<? extends Event>>, Integer[]> entry : gameEvents)
        {
            List<Class<? extends Event>> classList = entry.first;
            for (Integer time : entry.second)
            {
                events.add(new ManagedEvent(GetRandomEvent(classList), time));
            }
        }

        // Add static events here
        events.add(new ManagedEvent(new GraduationEvent(gameScreen), 1));
    }

    public void render()
    {
        for (ManagedEvent event : events)
        {
            if (GameGlobals.ELAPSED_TIME <= event.timeCalled)
            {
                if (!event.called) {
                    gameScreen.setPaused(true);
                    uiRenderer.showEvent(event.getEvent());
                    event.called = true;
                }
            }
        }
    }

    /**
     * Get a random instance of an event from a list of possible event classes
     */
    public Event GetRandomEvent(List<Class<? extends Event>> eventList)
    {
        if (eventList.isEmpty())
        {
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
     * Create an instance of an event from the class
     */
    private Event createEvent(Class<? extends Event> eventClass)
    {
        try {
            return eventClass.getConstructor(GameScreen.class).newInstance(gameScreen);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
