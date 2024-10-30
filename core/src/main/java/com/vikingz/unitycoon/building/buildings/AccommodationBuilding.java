package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.util.Point;

/**
 * Accomodation building class 
 * 
 * TODO:
 * Add a way to simulate students?
 */
public class AccommodationBuilding extends Building{

    private int numberOfStudents;

    // Standard constructor with float x and y
    public AccommodationBuilding(TextureRegion texture, float x, float y, BuildingStats.BuildingType buildingType, float satisfactionMultiplier, int numberOfStudents){
        super(texture, x, y, buildingType, satisfactionMultiplier);
        this.numberOfStudents = numberOfStudents;
    }

    // Constructor using Point class 
    public AccommodationBuilding(TextureRegion texture, Point p, BuildingStats.BuildingType buildingType, float satisfactionMultiplier, int numberOfStudents){
        super(texture, p, buildingType, satisfactionMultiplier);
        this.numberOfStudents = numberOfStudents;
    }

}
