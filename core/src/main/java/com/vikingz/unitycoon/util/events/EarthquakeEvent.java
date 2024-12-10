package com.vikingz.unitycoon.util.events;

import com.badlogic.gdx.math.MathUtils;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.render.BuildingRenderer;
import com.vikingz.unitycoon.screens.GameScreen;

import java.util.List;

public class EarthquakeEvent extends Event {
    public EarthquakeEvent(GameScreen gameScreen) {
        super(
            "An earthquake has occurred and 3 buildings have been destroyed.",
            new Event.Option(() -> {
                BuildingRenderer buildingRenderer = gameScreen.getGameRenderer().getBuildingRenderer();
                List<Building> placedBuildings = buildingRenderer.getPlaceBuildings();

                for (int i = 0; i < 3; i++)
                {
                    if (placedBuildings.isEmpty())
                        break;

                    int randomIndex = MathUtils.random(placedBuildings.size() - 1);
                    buildingRenderer.removeBuilding(placedBuildings.get(randomIndex));
                }

                gameScreen.setPaused(false);
            }, "-3 Buildings")
        );
    }
}
