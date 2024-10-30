package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.util.Point;
import com.vikingz.unitycoon.util.StatsCalculator;


public class FoodBuilding extends Building{

    private float coinsPerSecond;

    // Standard constructor with float x and y
    public FoodBuilding(TextureRegion texture, float x, float y, BuildingStats.BuildingType buildingType, float satisfactionMultiplier, float coinsPerSecond){
        super(texture, x, y, buildingType, satisfactionMultiplier);
        this.coinsPerSecond = coinsPerSecond;
    }

    // Constructor using Point class 
    public FoodBuilding(TextureRegion texture, Point p, BuildingStats.BuildingType buildingType, float satisfactionMultiplier, float coinsPerSecond){
        super(texture, p, buildingType, satisfactionMultiplier);
        this.coinsPerSecond = coinsPerSecond;
    }

    public float calcuateProfitMade(){
        return StatsCalculator.calcuateProfitMade(this.coinsPerSecond);
    }
}
