package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.util.Point;

/**
 * Accomodation building class
 *
 * Represents the Accomodation building in the game
 * Inherits Building
 *
 */
public class AccommodationBuilding extends Building{

    private int numberOfStudents;
    // Standard constructor with float x and y
    public AccommodationBuilding(TextureRegion texture, float x, float y, BuildingInfo buildingInfo, int numberOfStudents){
        super(texture, x, y, buildingInfo);
        this.numberOfStudents = numberOfStudents;
    }

    // Constructor using Point class
    public AccommodationBuilding(TextureRegion texture, Point p,  BuildingInfo buildingInfo, int numberOfStudents){
        super(texture, p,  buildingInfo);
        this.numberOfStudents = numberOfStudents;
    }

}
