package com.vikingz.unitycoon.util;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UIRenderer;

public class EventsManager {
    UIRenderer uiRenderer;

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

        public Event(String message, Option opt1, Option opt2)
        {
            this.message = message;
            this.opt1 = opt1;
            this.opt2 = opt2;
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

    public EventsManager(UIRenderer uiRenderer)
    {
        this.uiRenderer = uiRenderer;
        events = new ManagedEvent[] {
            new ManagedEvent(
                new Event("Teset event",
                    new Event.Option(() -> {}, "first"),
                    new Event.Option(() -> {}, "second")
                ), 10
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
//                    isPaused = true; // todo pause
                    uiRenderer.showEvent(event.event);
                    event.called = true;
                }
            }
        }
    }

}
