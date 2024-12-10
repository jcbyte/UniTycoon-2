package com.vikingz.unitycoon.util;

import com.badlogic.gdx.math.MathUtils;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UIRenderer;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.util.events.*;

import java.lang.reflect.Constructor;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

         // CLass containing a list of class objects of events.
         // We can add helper functions into this class in order to reduce code duplication
        class ClassList
        {
            public final Class<? extends Event>[] events;

            public ClassList(Class<? extends Event>[] events)
            {
                this.events = events;
            }

            /**
             * Get a random event from the declared event classes
             */
            public Event GetRandomEvent()
            {
                if (events.length == 0)
                {
                    throw new RuntimeException("There are no events");
                }

                try {
                    int randomIndex = MathUtils.random(0, events.length - 1);
                    return getEvent(events[randomIndex]);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            /**
             * Create an event instance from a class object
             */
            private Event getEvent(Class<? extends Event> eventClass)
            {
                try {
                    // Create an instance of a random event from the array
                    return eventClass.getConstructor(GameScreen.class).newInstance(gameScreen);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        ClassList goodEvents = new ClassList(new Class[] {
            GrantEvent.class,
            ScolarEvent.class,
            ChampionshipWinEvent.class,
        });

        ClassList badEvents = new ClassList(new Class[] {
            LongboiDeathEvent.class,
            ExamWeekEvent.class,
            FloodingEvent.class,
            EarthquakeEvent.class,
        });

        ClassList neutralEvents = new ClassList(new Class[] {
            AlumniVisitEvent.class,
            PeacefulProtestEvent.class,
            UniPartyEvent.class,
            CasinoEvent.class,
            CurriculumChangeEvent.class,
            StrikeEvent.class
        });

        // todo balance the events

         // This contains the time of each random event which will be shown in the game
        Map.Entry<ClassList, Integer[]>[] gameEvents = new Map.Entry[] {
            new AbstractMap.SimpleEntry(goodEvents, new Integer[] { 4 * 60, 3 * 60, 1 * 60 }),
            new AbstractMap.SimpleEntry(badEvents, new Integer[] { (int)(3.5 * 60), 2 * 60 }),
            new AbstractMap.SimpleEntry(neutralEvents, new Integer[] { (int)(4.5 * 60), (int)(2.5 * 60), (int)(0.5 * 60) }),
        };

        events = new ArrayList<>();
        for (Map.Entry<ClassList, Integer[]> entry : gameEvents)
        {
            ClassList classList = entry.getKey();
            for (Integer time : entry.getValue())
            {
                events.add(new ManagedEvent(classList.GetRandomEvent(), time));
            }
        }

        // Add static events here
        events.add(new ManagedEvent(new GraduationEvent(gameScreen), 10));
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

}
