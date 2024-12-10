package com.vikingz.unitycoon.util;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UIRenderer;
import com.vikingz.unitycoon.screens.GameScreen;

import java.util.concurrent.Callable;

public class EventsManager {
    UIRenderer uiRenderer;
    GameScreen gameScreen;

    public static class Event
    {
        public static class Option
        {
            public final String text;
            public final Runnable action;

            public Option(Runnable action, String text)
            {
                this.action = action;
                this.text = text;
            }
        }

        public final String message;
        public final Option opt1, opt2;
        public final boolean choice;

        public Event(String message, Option opt1, Option opt2)
        {
            this.message = message;
            this.opt1 = opt1;
            this.opt2 = opt2;
            choice = true;
        }

        public Event(String message, Option opt)
        {
            this.message = message;
            this.opt1 = opt;
            this.opt2 = null;
            choice = false;
        }
    }

    public static class CalculatedEvent
    {
        private final Callable<Event> calculateEvent;

        CalculatedEvent(Callable<Event> event)
        {
            calculateEvent = event;
        }

        public Event getEvent()
        {
            try {
                return calculateEvent.call();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class ManagedEvent
    {
        private final Event event;
        private final CalculatedEvent calcEvent;
        private final boolean isEventCalculated;

        public final int timeCalled;
        public boolean called;

        public ManagedEvent(Event event, int timeCalled)
        {
            this.event = event;
            calcEvent = null;
            isEventCalculated = false;
            this.timeCalled = timeCalled;
            this.called = false;
        }

        public ManagedEvent(CalculatedEvent event, int timeCalled)
        {
            this.event = null;
            calcEvent = event;
            isEventCalculated = true;
            this.timeCalled = timeCalled;
            this.called = false;
        }

        public Event getEvent()
        {
            if (isEventCalculated) {
                assert calcEvent != null;
                return calcEvent.getEvent();
            }
            else
                return event;
        }
    }

    private ManagedEvent[] events;

    public EventsManager(GameScreen gameScreen, UIRenderer uiRenderer)
    {
        this.gameScreen = gameScreen;
        this.uiRenderer = uiRenderer;

        Event grantEvent = new Event("You receive a grant from the government",
            new Event.Option(() -> {
                GameGlobals.BALANCE += 1000;
                gameScreen.setPaused(false);
            }, "Take the cash\n+1000 Money"),
            new Event.Option(() -> {
                GameGlobals.STUDENTS += 300;
                gameScreen.setPaused(false);
            }, "Reduce fees\n+300 Students")
        );

        CalculatedEvent alumniVisit =
            new CalculatedEvent(() -> {
                boolean alumniImpressed = GameGlobals.SATISFACTION > 2000;
                return new Event("Surprise alumni Visit\n" + (alumniImpressed ? "The alumni is impressed with the university and donates" : "The alumni is not impressed and your reputation decreases"),
                    new Event.Option(() -> {
                        if (alumniImpressed)
                            GameGlobals.BALANCE += 1000;
                        else
                            GameGlobals.STUDENTS = Math.max(0, GameGlobals.STUDENTS - 200);
                        gameScreen.setPaused(false);
                    }, alumniImpressed ? "+1000 Money" : "-200 Students")
                );
            });

        Event longboiDies =
            new Event("Your university's mascot, Longboi, has passed away",
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION -= 1000;
                    gameScreen.setPaused(false);
                }, "-1000 Satisfaction")
            );


        events = new ManagedEvent[] {
            new ManagedEvent(grantEvent, 8),
            new ManagedEvent(alumniVisit, 5),
            new ManagedEvent(longboiDies, 1)
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
