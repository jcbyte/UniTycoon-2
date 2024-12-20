package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where a change is suggested to the curriculum which will cost money, but increase
 * students, or you can leave it the same.
 */
public class CurriculumChangeEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public CurriculumChangeEvent(GameScreen gameScreen) {
    super(() ->
        new Event(
            "A faculty member suggests a major change to the university's\n"
                + "curriculum to make it more cutting-edge.",
            new Event.Option(() -> gameScreen.setPaused(false), "Leave it"),
            new Event.Option(() -> {
              GameGlobals.BALANCE -= 300;
              GameGlobals.STUDENTS += 250;

              gameScreen.setPaused(false);
            }, "Change it\n-300 Money\n+250 Students", GameGlobals.BALANCE < 300)
        )
    );
  }
}
