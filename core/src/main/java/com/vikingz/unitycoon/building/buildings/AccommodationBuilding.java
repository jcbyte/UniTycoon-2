package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.util.Point;

/**
 * Represents the Accommodation building in the game.
 */
public class AccommodationBuilding extends Building {
  /**
   * Creates a new Accommodation Building.
   *
   * @param texture      Texture
   * @param p            Point p
   * @param buildingInfo Building Info
   */
  public AccommodationBuilding(TextureRegion texture, Point p, BuildingInfo buildingInfo) {
    super(texture, p, buildingInfo);
  }
}
