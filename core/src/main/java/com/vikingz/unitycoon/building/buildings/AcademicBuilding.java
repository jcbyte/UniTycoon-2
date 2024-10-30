package com.vikingz.unitycoon.building.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.util.Point;

public class AcademicBuilding extends Building{

    public AcademicBuilding(TextureRegion texture, float x, float y, BuildingStats.BuildingType buildingType, float satisfactionMultiplier){
        super(texture, x, y, buildingType, satisfactionMultiplier);
    }

    public AcademicBuilding(TextureRegion texture, Point p, BuildingStats.BuildingType buildingType, float satisfactionMultiplier){
        super(texture, p, buildingType, satisfactionMultiplier);
    }

}
