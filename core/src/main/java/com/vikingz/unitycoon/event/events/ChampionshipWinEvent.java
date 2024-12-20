package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where the university wins a championship and satisfaction is boosted.
 */
public class ChampionshipWinEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public ChampionshipWinEvent(GameScreen gameScreen) {
    super(
        "The university team wins a championship.",
        new Event.Option(() -> {
          GameGlobals.SATISFACTION += 100000;

          gameScreen.setPaused(false);
        }, "+Satisfaction")
    );
  }
}
