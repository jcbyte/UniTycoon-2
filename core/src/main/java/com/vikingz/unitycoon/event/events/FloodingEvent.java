package com.vikingz.unitycoon.event.events;

import com.badlogic.gdx.math.MathUtils;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.BuildingRenderer;
import com.vikingz.unitycoon.screens.GameScreen;
import java.util.List;

/**
 * Event where there is flooding leading to destroyed buildings and reduced satisfaction.
 */
public class FloodingEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public FloodingEvent(GameScreen gameScreen) {
    super(
        "Major flooding has occurred, a building\nhas been destroyed and morale is low.",
        new Event.Option(() -> {
          BuildingRenderer buildingRenderer = gameScreen.getGameRenderer().getBuildingRenderer();
          List<Building> placedBuildings = buildingRenderer.getPlaceBuildings();

          if (!placedBuildings.isEmpty()) {
            int randomIndex = MathUtils.random(placedBuildings.size() - 1);
            buildingRenderer.removeBuilding(placedBuildings.get(randomIndex), false);
          }

          GameGlobals.SATISFACTION -= 50000;

          gameScreen.setPaused(false);
        }, "-1 Building\n-Satisfaction")
    );
  }
}
