package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where you can choose to throw a party resulting in increased satisfaction.
 */
public class UniPartyEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public UniPartyEvent(GameScreen gameScreen) {
    super(() ->
        new Event(
            "The students want the university to throw a party.",
            new Event.Option(() -> gameScreen.setPaused(false), "Don't do anything"),
            new Event.Option(() -> {
              GameGlobals.BALANCE -= 250;
              GameGlobals.SATISFACTION += 100000;

              gameScreen.setPaused(false);
            }, "Throw the party\n-250 Money\n+Satisfaction", GameGlobals.BALANCE < 250)
        )
    );
  }
}
