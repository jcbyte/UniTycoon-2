package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.util.Point;
import com.vikingz.unitycoon.util.StatsCalculator;

/**
 * RecreationalBuilding
 * 
 * Represents the recreational building class in the game
 * Inherits Building 
 */
public class RecreationalBuilding extends Building{

    private float coinsPerSecond;

    // Standard constructor with float x and y
    public RecreationalBuilding(TextureRegion texture, float x, float y, BuildingStats.BuildingType buildingType, float satisfactionMultiplier, float coinsPerSecond){
        super(texture, x, y, buildingType, satisfactionMultiplier);
        this.coinsPerSecond = coinsPerSecond;
    }

    // Constructor using Point class 
    public RecreationalBuilding(TextureRegion texture, Point p, BuildingStats.BuildingType buildingType, float satisfactionMultiplier, float coinsPerSecond){
        super(texture, p, buildingType, satisfactionMultiplier);
        this.coinsPerSecond = coinsPerSecond;
    }

    public float calcuateProfitMade(){
        return StatsCalculator.calcuateProfitMade(this.coinsPerSecond);
    }

}
