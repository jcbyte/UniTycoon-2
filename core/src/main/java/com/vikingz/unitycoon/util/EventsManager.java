package com.vikingz.unitycoon.util;

import com.badlogic.gdx.math.MathUtils;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UIRenderer;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.util.events.*;

public class EventsManager {
    UIRenderer uiRenderer;
    GameScreen gameScreen;

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

    private ManagedEvent[] events;

    public EventsManager(GameScreen gameScreen)
    {
        this.gameScreen = gameScreen;
        this.uiRenderer = gameScreen.getUIRenderer();

        Class<? extends Event>[] goodEvents = new Class[] {
            GrantEvent.class,
            ScolarEvent.class,
            ChampionshipWinEvent.class,
        };

        Class<? extends Event>[] badEvents = new Class[] {
            LongboiDeathEvent.class,
            ExamWeekEvent.class,
            FloodingEvent.class,
            EarthquakeEvent.class,
        };

        Class<? extends Event>[] neutralEvents = new Class[] {
            AlumniVisitEvent.class,
            PeacefulProtestEvent.class,
            UniPartyEvent.class,
            CasinoEvent.class,
            CurriculumChangeEvent.class,
            StrikeEvent.class
        };

        // todo create static event (exam week + graduation?)
        // todo balance the events

        events = new ManagedEvent[] {

        };
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
