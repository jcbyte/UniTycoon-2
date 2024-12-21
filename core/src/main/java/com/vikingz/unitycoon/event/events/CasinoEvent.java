package com.vikingz.unitycoon.event.events;

import com.badlogic.gdx.math.MathUtils;
import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where you can choose to gamble your money to lose it all or x2 it.
 */
public class CasinoEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public CasinoEvent(GameScreen gameScreen) {
    super(
        "You are at the casino.\nDo you put it all on red?",
        new Event.Option(() -> gameScreen.setPaused(false), "Dont gamble"),
        new Event.Option(() -> {
          if (MathUtils.randomBoolean()) {
            GameGlobals.BALANCE *= 2;
          } else {
            GameGlobals.BALANCE = 0;
          }
          
          gameScreen.setPaused(false);
        }, "All on red\n2x Money (50%)\nLose all money (50%)")
    );
  }
}
