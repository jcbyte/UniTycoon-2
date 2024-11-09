package com.vikingz.unitycoon.building;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.util.Point;
import com.vikingz.unitycoon.util.StatsCalculator;


/**
 * Building
 *
 * Abstract class that represents all of the buildings in the game.
 */
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

    private BuildingInfo buildingInfo;



    public Building(TextureRegion texture, float x, float y, BuildingInfo buildingInfo){
        this.x = x;
        this.y = y;
        this.width = 64;
        this.height = 64;
        this.texture = texture;
        this.buildingType = buildingInfo.getBuildingType();
        this.satisfactionMultiplier = buildingInfo.getSatisfactionMultiplier();
        this.buildingInfo = buildingInfo;
    }


    public BuildingInfo getBuildingInfo() {
        return buildingInfo;
    }

    public Building(TextureRegion texture, Point p, BuildingInfo buildingInfo){
        this.x = p.getX();
        this.y = p.getY();
        this.width = 64;
        this.height = 64;
        this.texture = texture;
        this.buildingType = buildingInfo.getBuildingType();
        this.satisfactionMultiplier = buildingInfo.getSatisfactionMultiplier();
        this.buildingInfo = buildingInfo;
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


    public String toString(){
        String str = "";

        str += "x: " + this.x;
        str += " y: " + this.y;
        str += " width: " + this.width;
        str += " height: " + this.height;
        str += " type: " + this.buildingType;


        return str;

    }



}
