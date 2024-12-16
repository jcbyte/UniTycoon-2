package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.util.Point;


/**
 * AcademicBuilding
 * <p>
 * Represents the academic building in the game
 * Inherits Building
 *
 */
public class AcademicBuilding extends Building{

    /**
     * Creates a new Academic Building
     * @param texture Texture
     * @param x X
     * @param y Y
     * @param buildingInfo Info about the building
     */
    public AcademicBuilding(TextureRegion texture, float x, float y, BuildingInfo buildingInfo){
        super(texture, x, y, buildingInfo);
    }

    /**
     * Creates a new Academic Building
     * @param texture Texture
     * @param p Point p
     * @param buildingInfo Info about the building
     */
    public AcademicBuilding(TextureRegion texture, Point p, BuildingInfo buildingInfo){
        super(texture, p, buildingInfo);
    }

}
