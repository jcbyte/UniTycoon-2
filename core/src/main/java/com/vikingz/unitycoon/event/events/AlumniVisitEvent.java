package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where an alumni member visits the uni, if there is high enough satisfaction they will
 * donate, if not your reputation is damaged.
 */
public class AlumniVisitEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public AlumniVisitEvent(GameScreen gameScreen) {
    super(() -> {
      boolean alumniImpressed = GameGlobals.SATISFACTION > 1000000;
      return new Event(
          "Surprise alumni Visit\n"
              + (alumniImpressed
              ? "The alumni is impressed with the university and donates"
              : "The alumni is not impressed and your reputation decreases")
              + ".",
          new Event.Option(() -> {
            if (alumniImpressed) {
              GameGlobals.BALANCE += 5000;
            } else {
              GameGlobals.STUDENTS = Math.max(0, GameGlobals.STUDENTS - 350);
            }

            gameScreen.setPaused(false);
          }, alumniImpressed ? "+5000 Money" : "-350 Students")
      );
    });
  }
}
