package com.vikingz.unitycoon.building;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.util.Point;
import com.vikingz.unitycoon.util.StatsCalculator;

public abstract class Building {

    // Building drawing properties
    private TextureRegion texture;
    private float x;
    private float y;
    private float width;
    private float height;


    // Building functional properties
    private BuildingStats.BuildingType buildingType;

    private float satisfactionMultiplier;



    public Building(TextureRegion texture, float x, float y, BuildingStats.BuildingType buildingType, float satisfactionMultiplier){
        this.x = x;
        this.y = y;
        this.width = 64;
        this.height = 64;
        this.texture = texture;
        this.buildingType = buildingType;
        this.satisfactionMultiplier = satisfactionMultiplier;
    }


    
    public Building(TextureRegion texture, Point p, BuildingStats.BuildingType buildingType, float satisfactionMultiplier){
        this.x = p.getX();
        this.y = p.getY();
        this.width = 64;
        this.height = 64;
        this.texture = texture;
        this.buildingType = buildingType;
        this.satisfactionMultiplier = satisfactionMultiplier;
    }



    // Generate Satisfaciton logic

    public int calculateSatisfaction(int numberOfStudents){

        return StatsCalculator.calculateSatisfaction(numberOfStudents, this.satisfactionMultiplier);

    }


    // Getters and Setters


    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }


    public TextureRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion textureBuilding) {
        this.texture = textureBuilding;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    public BuildingStats.BuildingType getBuildingType() {
        return buildingType;
    }




}
