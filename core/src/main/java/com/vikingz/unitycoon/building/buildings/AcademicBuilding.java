package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.util.Point;


/**
 * AcademicBuilding
 *
 * Represents the academic building in the game
 * Inherits Building
 *
 */
public class AcademicBuilding extends Building{

    public AcademicBuilding(TextureRegion texture, float x, float y, BuildingInfo buildingInfo){
        super(texture, x, y, buildingInfo);
    }

    public AcademicBuilding(TextureRegion texture, Point p, BuildingInfo buildingInfo){
        super(texture, p, buildingInfo);
    }

}
