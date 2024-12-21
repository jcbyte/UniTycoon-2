package com.vikingz.unitycoon.event;

import java.util.concurrent.Callable;

/**
 * Class representing an event, with its message options and actions.
 * This is extended in each of the events which call super to initialise.
 */
public class Event {
  private final Callable<Event> calculateEvent;
  private final boolean isEventCalculated;

  /**
   * Represents an option for an event.
   */
  public static class Option {
    private final String text;
    private final Runnable action;
    private final boolean disabled;

    /**
     * Initialise option with given data.
     *
     * @param action to run when this option is selected
     * @param text   to display for this option
     */
    public Option(Runnable action, String text) {
      this.action = action;
      this.text = text;
      disabled = false;
    }

    /**
     * Initialise option with given data.
     *
     * @param action   to run when this option is selected
     * @param text     to display for this option
     * @param disabled if the option is disabled
     */
    public Option(Runnable action, String text, boolean disabled) {
      this.action = action;
      this.text = text;
      this.disabled = disabled;
    }

    public String getText() {
      return text;
    }

    public Runnable getAction() {
      return action;
    }

    public boolean isDisabled() {
      return disabled;
    }
  }

  private final String message;
  private final Option opt1;
  private final Option opt2;
  private final boolean choice;

  /**
   * Initialise event with message and given options.
   */
  public Event(String message, Option opt1, Option opt2) {
    this.message = message;
    this.opt1 = opt1;
    this.opt2 = opt2;
    choice = true;

    calculateEvent = null;
    isEventCalculated = false;
  }

  /**
   * Initialise event with message and given option.
   */
  public Event(String message, Option opt) {
    this.message = message;
    this.opt1 = opt;
    this.opt2 = null;
    choice = false;

    calculateEvent = null;
    isEventCalculated = false;
  }

  /**
   * Initialise event with callback to generate event in realtime.
   */
  public Event(Callable<Event> event) {
    this.message = null;
    this.opt1 = null;
    this.opt2 = null;
    choice = true;

    calculateEvent = event;
    isEventCalculated = true;
  }

  /**
   * Get the event object.
   */
  public Event getEvent() {
    if (isEventCalculated) {
      assert calculateEvent != null;
      try {
        return calculateEvent.call();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else {
      return this;
    }
  }

  public String getMessage() {
    return message;
  }

  public Option getOpt1() {
    return opt1;
  }

  public Option getOpt2() {
    return opt2;
  }

  /**
   * If the user has two options instead of one.
   */
  public boolean hasChoice() {
    return choice;
  }
}
