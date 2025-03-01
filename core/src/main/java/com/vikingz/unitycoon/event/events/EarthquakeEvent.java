package com.vikingz.unitycoon.event.events;

import com.badlogic.gdx.math.MathUtils;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.render.BuildingRenderer;
import com.vikingz.unitycoon.screens.GameScreen;
import java.util.List;

/**
 * Event where there is an earthquake and buildings are destroyed.
 */
public class EarthquakeEvent extends Event {
  /**
   * Initialise this type of event.
   */
  public EarthquakeEvent(GameScreen gameScreen) {
    super(
        "An earthquake has occurred and 4 buildings\nhave been destroyed.",
        new Event.Option(() -> {
          BuildingRenderer buildingRenderer = gameScreen.getGameRenderer().getBuildingRenderer();
          List<Building> placedBuildings = buildingRenderer.getPlaceBuildings();

          for (int i = 0; i < 4; i++) {
            if (placedBuildings.isEmpty()) {
              break;
            }

            int randomIndex = MathUtils.random(placedBuildings.size() - 1);
            buildingRenderer.removeBuilding(placedBuildings.get(randomIndex), false);
          }

          gameScreen.setPaused(false);
        }, "-4 Buildings")
    );
  }
}
