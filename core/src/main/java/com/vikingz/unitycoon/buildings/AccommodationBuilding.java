package com.vikingz.unitycoon.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.util.Point;

/**
 * Accomodation building class 
 * 
 * TODO:
 * Add a way to simulate students?
 */
public class AccommodationBuilding extends Building{

    // Standard constructor with float x and y
    public AccommodationBuilding(TextureRegion texture, float x, float y, float satisfactionMultiplier){
        super(texture, x, y, satisfactionMultiplier);
    }

    // Constructor using Point class 
    public AccommodationBuilding(TextureRegion texture, Point p, float satisfactionMultiplier){
        super(texture, p, satisfactionMultiplier);
    }

}
