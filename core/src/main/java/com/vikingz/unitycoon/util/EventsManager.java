package com.vikingz.unitycoon.util;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UIRenderer;
import com.vikingz.unitycoon.screens.GameScreen;

public class EventsManager {
    UIRenderer uiRenderer;
    GameScreen gameScreen;

    public static class Event
    {
        public static class Option
        {
            public String text;
            public Runnable action;

            public Option(Runnable action, String text)
            {
                this.action = action;
                this.text = text;
            }
        }

        public String message;
        public Option opt1, opt2;
        public boolean choice;

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
            choice = false;
        }
    }

    public static class ManagedEvent
    {
        public Event event;
        public int timeCalled;
        public boolean called;

        public ManagedEvent(Event event, int timeCalled)
        {
            this.event = event;
            this.timeCalled = timeCalled;
            this.called = false;
        }
    }

    private ManagedEvent[] events;

    public EventsManager(GameScreen gameScreen, UIRenderer uiRenderer)
    {
        this.gameScreen = gameScreen;
        this.uiRenderer = uiRenderer;

        // todo create events
        events = new ManagedEvent[] {
            new ManagedEvent(
                new Event("Test event 1, multiple choice",
                    new Event.Option(() -> {
                        // do something
                        gameScreen.setPaused(false);
                    }, "first"),
                    new Event.Option(() -> {
                        // do something
                        gameScreen.setPaused(false);
                    }, "second")
                ), 13
            ),
            new ManagedEvent(
                new Event("Test event 2, single choice",
                    new Event.Option(() -> {
                        // do something
                        gameScreen.setPaused(false);
                    }, "Close")
                ), 8
            )
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
                    uiRenderer.showEvent(event.event);
                    event.called = true;
                }
            }
        }
    }

}
