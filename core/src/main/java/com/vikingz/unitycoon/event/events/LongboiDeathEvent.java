package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

/**
 * Event where the university's mascot dies reducing satisfaction, this is reduced with the amount
 * of statues you have.
 */
public class LongboiDeathEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public LongboiDeathEvent(GameScreen gameScreen) {
    super(() -> {
      int statueCount = (int) gameScreen.getGameRenderer().getBuildingRenderer()
          .getPlaceBuildings().stream()
          .filter(building -> building.getBuildingInfo().getBuildingId().equals("LONGBS"))
          .count();

      return new Event(
          "Your university's mascot, Longboi, has passed away."
              + (statueCount > 0
              ? "\nYou have " + statueCount + " Longboi Statues, so this decrease is reduced."
              : ""),
          new Event.Option(() -> {
            GameGlobals.SATISFACTION -= 250000 / (statueCount + 1);

            gameScreen.setPaused(false);
          }, "-Satisfaction")
      );
    });
  }
}
