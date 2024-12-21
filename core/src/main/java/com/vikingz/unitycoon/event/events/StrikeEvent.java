package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where the lectures are on strike, and you choose to pay them more or fire them resulting
 * in reduced satisfaction and students leaving.
 */
public class StrikeEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public StrikeEvent(GameScreen gameScreen) {
    super(() ->
        new Event(
            "Faculty members threaten to strike over pay.",
            new Event.Option(() -> {
              GameGlobals.BALANCE -= 500;

              gameScreen.setPaused(false);
            }, "Increase pay\n-500 Money", GameGlobals.BALANCE < 500),
            new Event.Option(() -> {
              GameGlobals.SATISFACTION -= 200000;
              GameGlobals.STUDENTS = Math.max(0, GameGlobals.STUDENTS - 100);

              gameScreen.setPaused(false);
            }, "Fire the staff\n-Satisfaction\n-100 Students")
        )
    );
  }
}
