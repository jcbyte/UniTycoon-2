package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where the students organise a protest, and you can choose to fund them for increased
 * satisfaction or not for reduced satisfaction.
 */
public class PeacefulProtestEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public PeacefulProtestEvent(GameScreen gameScreen) {
    super(() ->
        new Event(
            "The students begin to organise a peaceful protest.",
            new Event.Option(() -> {
              GameGlobals.SATISFACTION -= 50000;

              gameScreen.setPaused(false);
            }, "Shut them down\n-Satisfaction"),
            new Event.Option(() -> {
              GameGlobals.BALANCE -= 100;
              GameGlobals.SATISFACTION += 50000;

              gameScreen.setPaused(false);
            }, "Fund the rally\n-100 Money\n+Satisfaction", GameGlobals.BALANCE < 100)
        )
    );
  }
}
