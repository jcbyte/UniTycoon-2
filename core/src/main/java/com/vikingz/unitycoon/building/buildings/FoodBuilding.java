package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.util.Point;
import com.vikingz.unitycoon.util.StatsCalculator;

/**
 * Represents the food building class in the game.
 */
public class FoodBuilding extends Building {

  private final float coinsPerSecond;

  /**
   * Creates new food building.
   *
   * @param texture        Texture
   * @param p              Point p
   * @param buildingInfo   Building Info
   * @param coinsPerSecond Amount of coins this building make per second
   */
  public FoodBuilding(TextureRegion texture, Point p, BuildingInfo buildingInfo,
                      float coinsPerSecond) {
    super(texture, p, buildingInfo);
    this.coinsPerSecond = coinsPerSecond;
  }

  /**
   * Calculates profit of this building.
   *
   * @return float coins made
   */
  public float calculateProfitMade() {
    return StatsCalculator.calculateProfitMade(this.coinsPerSecond);
  }
}
