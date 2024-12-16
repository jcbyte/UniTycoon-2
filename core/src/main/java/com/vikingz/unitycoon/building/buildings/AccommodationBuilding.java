package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.util.Point;

/**
 * Accommodation building class
 * <p>
 * Represents the Accommodation building in the game
 * Inherits Building
 *
 */
public class AccommodationBuilding extends Building{

    private final int numberOfStudents;

    /**
     * Creates a new Accommodation Building
     * @param texture Texture
     * @param x X
     * @param y Y
     * @param buildingInfo Building Info
     * @param numberOfStudents Number of students that this building adds
     */
    public AccommodationBuilding(TextureRegion texture, float x, float y, BuildingInfo buildingInfo, int numberOfStudents){
        super(texture, x, y, buildingInfo);
        this.numberOfStudents = numberOfStudents;
    }

    /**
     * Creates a new Accommodation Building
     * @param texture Texture
     * @param p Point p
     * @param buildingInfo Building Info
     * @param numberOfStudents Number of students that this building adds
     */
    public AccommodationBuilding(TextureRegion texture, Point p,  BuildingInfo buildingInfo, int numberOfStudents){
        super(texture, p,  buildingInfo);
        this.numberOfStudents = numberOfStudents;
    }

}
