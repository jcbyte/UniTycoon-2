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

    /**
     * Creates new food building
     * @param texture Texture
     * @param x X
     * @param y Y
     * @param buildingInfo Building Info
     * @param coinsPerSecond Ammount of coins this building make per second
     */
    public FoodBuilding(TextureRegion texture, float x, float y, BuildingInfo buildingInfo, float coinsPerSecond){
        super(texture, x, y, buildingInfo);
        this.coinsPerSecond = coinsPerSecond;
    }

    /**
     * Creates new food building
     * @param texture Texture
     * @param p Point p
     * @param buildingInfo Building Info
     * @param coinsPerSecond Ammount of coins this building make per second
     */
    public FoodBuilding(TextureRegion texture, Point p, BuildingInfo buildingInfo, float coinsPerSecond){
        super(texture, p, buildingInfo);
        this.coinsPerSecond = coinsPerSecond;
    }

    /**
     * Calculates profit of this building
     * @return float coins made
     */
    public float calcuateProfitMade(){
        return StatsCalculator.calcuateProfitMade(this.coinsPerSecond);
    }
}
