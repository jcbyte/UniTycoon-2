package com.vikingz.unitycoon.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.util.Point;

public class AcademicBuilding extends Building{

    public AcademicBuilding(TextureRegion texture, float x, float y, float satisfactionMultiplier){
        super(texture, x, y, satisfactionMultiplier);
    }

    public AcademicBuilding(TextureRegion texture, Point p, float satisfactionMultiplier){
        super(texture, p, satisfactionMultiplier);
    }

}
