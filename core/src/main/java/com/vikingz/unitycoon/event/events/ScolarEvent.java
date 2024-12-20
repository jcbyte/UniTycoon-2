package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where a famous solar visits and increases the university's reputation, attracting more
 * students.
 */
public class ScolarEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public ScolarEvent(GameScreen gameScreen) {
    super(
        "A famous scholar visits.\nPrestige increases, attracting more students.",
        new Event.Option(() -> {
          GameGlobals.STUDENTS += 150;

          gameScreen.setPaused(false);
        }, "+150 Students")
    );
  }
}
