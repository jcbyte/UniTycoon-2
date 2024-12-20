package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.util.Point;
import com.vikingz.unitycoon.util.StatsCalculator;

/**
 * Represents the recreational building class in the game.
 */
public class RecreationalBuilding extends Building {

  private final float coinsPerSecond;

  /**
   * Creates a new Recreational Building.
   *
   * @param texture        Texture
   * @param p              Point p
   * @param buildingInfo   Building Info
   * @param coinsPerSecond The amount of coins this building makes per second
   */
  public RecreationalBuilding(TextureRegion texture, Point p, BuildingInfo buildingInfo,
                              float coinsPerSecond) {
    super(texture, p, buildingInfo);
    this.coinsPerSecond = coinsPerSecond;
  }

  /**
   * Calculates profit made.
   *
   * @return float of profit made
   */
  public float calculateProfitMade() {
    return StatsCalculator.calculateProfitMade(this.coinsPerSecond);
  }
}
