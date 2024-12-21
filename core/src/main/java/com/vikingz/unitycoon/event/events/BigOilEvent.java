package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where big oil wants to donate, if you accept your reputation is damaged.
 */
public class BigOilEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public BigOilEvent(GameScreen gameScreen) {
    super(
        "Big oil wants to donate to the university.\nThis could be controversial.",
        new Event.Option(() -> {
          GameGlobals.SATISFACTION += 50;

          gameScreen.setPaused(false);
        }, "Dont Accept\n+50 Students"),
        new Event.Option(() -> {
          GameGlobals.BALANCE += 4000;
          GameGlobals.STUDENTS = Math.max(0, GameGlobals.STUDENTS - 500);

          gameScreen.setPaused(false);
        }, "Accept the money\n+4000 Money\n-500 Students")
    );
  }
}
