package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where it is exam week and students are stressed, hence reduced satisfaction.
 */
public class ExamWeekEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public ExamWeekEvent(GameScreen gameScreen) {
    super(
        "It's exam week, students are stressed and scramble\n"
            + "to study leading to overcrowded libraries.",
        new Event.Option(() -> {
          GameGlobals.SATISFACTION -= 100000;

          gameScreen.setPaused(false);
        }, "-Satisfaction")
    );
  }
}
