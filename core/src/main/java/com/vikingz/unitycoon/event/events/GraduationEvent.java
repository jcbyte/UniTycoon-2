package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where your students graduate, boosting satisfaction.
 *
 * <p>This should be displayed at the end of the game to finish up
 */
public class GraduationEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public GraduationEvent(GameScreen gameScreen) {
    super(
        """
            Graduation Day!
            The hard work of your students, faculty, and staff has lead
            up to this moment. Proud graduates, dressed in caps and
            gowns, step into the world carrying the knowledge and
            experiences gained under your leadership.""",
        new Event.Option(() -> {
          GameGlobals.SATISFACTION += 1000000;

          gameScreen.setPaused(false);
        }, "+Satisfaction")
    );
  }
}
