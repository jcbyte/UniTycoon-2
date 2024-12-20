package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.util.Point;


/**
 * Represents the academic building in the game.
 */
public class AcademicBuilding extends Building {
  /**
   * Creates a new Academic Building.
   *
   * @param texture      Texture
   * @param p            Point p
   * @param buildingInfo Info about the building
   */
  public AcademicBuilding(TextureRegion texture, Point p, BuildingInfo buildingInfo) {
    super(texture, p, buildingInfo);
  }
}
