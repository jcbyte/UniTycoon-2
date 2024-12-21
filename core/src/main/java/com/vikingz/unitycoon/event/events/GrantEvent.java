package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where you receive a grant and can choose to take it as cash or reduce student fees.
 */
public class GrantEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public GrantEvent(GameScreen gameScreen) {
    super(
        "You receive a grant from the government.",
        new Event.Option(() -> {
          GameGlobals.BALANCE += 1000;

          gameScreen.setPaused(false);
        }, "Take the cash\n+1000 Money"),
        new Event.Option(() -> {
          GameGlobals.STUDENTS += 300;

          gameScreen.setPaused(false);
        }, "Reduce fees\n+300 Students")
    );
  }
}
