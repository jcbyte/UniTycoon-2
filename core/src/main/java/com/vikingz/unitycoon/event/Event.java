package com.vikingz.unitycoon.event;

import java.util.concurrent.Callable;

/**
 * Class representing an event, with its message options and actions.
 * This is extended in each of the events which call super to initialise.
 */
public class Event
{
    private final Callable<Event> calculateEvent;
    private final boolean isEventCalculated;

    public static class Option
    {
        public final String text;
        public final Runnable action;
        public final boolean disabled;

        public Option(Runnable action, String text)
        {
            this.action = action;
            this.text = text;
            disabled = false;
        }

        public Option(Runnable action, String text, boolean disabled)
        {
            this.action = action;
            this.text = text;
            this.disabled = disabled;
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

        calculateEvent = null;
        isEventCalculated = false;
    }

    public Event(String message, Option opt)
    {
        this.message = message;
        this.opt1 = opt;
        this.opt2 = null;
        choice = false;

        calculateEvent = null;
        isEventCalculated = false;
    }

    public Event(Callable<Event> event)
    {
        this.message = null;
        this.opt1 = null;
        this.opt2 = null;
        choice = true;

        calculateEvent = event;
        isEventCalculated = true;
    }

    public Event getEvent()
    {
        if (isEventCalculated)
        {
            assert calculateEvent != null;
            try {
                return calculateEvent.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            return this;
        }
    }
}
