package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.util.Point;
import com.vikingz.unitycoon.util.StatsCalculator;


/**
 * FoodBuilding
 *
 * Represents the food building class in the game
 * Inherits Building
 */
public class FoodBuilding extends Building{

    private float coinsPerSecond;

    // Standard constructor with float x and y
    public FoodBuilding(TextureRegion texture, float x, float y, BuildingInfo buildingInfo, float coinsPerSecond){
        super(texture, x, y, buildingInfo);
        this.coinsPerSecond = coinsPerSecond;
    }

    // Constructor using Point class
    public FoodBuilding(TextureRegion texture, Point p, BuildingInfo buildingInfo, float coinsPerSecond){
        super(texture, p, buildingInfo);
        this.coinsPerSecond = coinsPerSecond;
    }

    public float calcuateProfitMade(){
        return StatsCalculator.calcuateProfitMade(this.coinsPerSecond);
    }
}
