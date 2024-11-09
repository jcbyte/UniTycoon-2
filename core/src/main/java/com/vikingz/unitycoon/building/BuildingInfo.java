package com.vikingz.unitycoon.building;

import com.vikingz.unitycoon.building.BuildingStats.BuildingID;
import com.vikingz.unitycoon.building.BuildingStats.BuildingType;

/**
 * This class essentially serves as a struct to pass data around regarding buildings.
 * When the user presses a button in the menu to buy a building, the data for that building
 * is passed around a type BuildingInfo
 */
public class BuildingInfo {

    BuildingID  buildingID;
    BuildingType buildingType;
    float buildingCost;

    float satisfactionMultiplier;
    int numberOfStudents;
    float coinsPerSecond;





    /**
     * Creates new Building Info
     * The following constructors,
     * are for different types of building and have different params
     * @param buildingID Building ID
     * @param buildingType Building Type
     * @param buildingCost Building Cost
     * @param satisfactionMultiplier Satisfaction Multiplier
     * @param numberOfStudents Number of students
     * @param coinsPerSecond Coins per second the building generates
     */
    public BuildingInfo(BuildingStats.BuildingID  buildingID, BuildingStats.BuildingType buildingType, float buildingCost, float satisfactionMultiplier, int numberOfStudents, float coinsPerSecond){
        this.buildingID = buildingID;
        this.buildingType = buildingType;
        this.buildingCost = buildingCost;

        this.satisfactionMultiplier = satisfactionMultiplier;
        this.numberOfStudents = numberOfStudents;
        this.coinsPerSecond = coinsPerSecond;


    }


    public BuildingInfo(BuildingStats.BuildingID  buildingID, BuildingStats.BuildingType buildingType, float buildingCost, int numberOfStudents){
        this.buildingID = buildingID;
        this.buildingType = buildingType;
        this.buildingCost = buildingCost;

        this.satisfactionMultiplier = 0;
        this.numberOfStudents = numberOfStudents;
        this.coinsPerSecond = 0;


    }

    public BuildingInfo(BuildingStats.BuildingID  buildingID, BuildingStats.BuildingType buildingType, float buildingCost, float satisfactionMultiplier, int numberOfStudents){
        this.buildingID = buildingID;
        this.buildingType = buildingType;
        this.buildingCost = buildingCost;

        this.satisfactionMultiplier = satisfactionMultiplier;
        this.numberOfStudents = numberOfStudents;
        this.coinsPerSecond = 0;


    }

    public BuildingInfo(BuildingStats.BuildingID  buildingID, BuildingStats.BuildingType buildingType, float buildingCost, float satisfactionMultiplier){

        this.buildingID = buildingID;
        this.buildingType = buildingType;
        this.buildingCost = buildingCost;

        this.satisfactionMultiplier = satisfactionMultiplier;
        this.numberOfStudents = 0;
        this.coinsPerSecond = 0;


    }

    public BuildingInfo(BuildingStats.BuildingID  buildingID, BuildingStats.BuildingType buildingType, float buildingCost, float satisfactionMultiplier, float coinsPerSecond){
        this.buildingID = buildingID;
        this.buildingType = buildingType;
        this.buildingCost = buildingCost;

        this.satisfactionMultiplier = satisfactionMultiplier;
        this.numberOfStudents = 0;
        this.coinsPerSecond = coinsPerSecond;


    }


    //Getters and Setters
    public BuildingID getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(BuildingStats.BuildingID buildingID) {
        this.buildingID = buildingID;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public float getBuildingCost() {
        return buildingCost;
    }

    public void setBuildingCost(float buildingCost) {
        this.buildingCost = buildingCost;
    }

    public float getSatisfactionMultiplier() {
        return satisfactionMultiplier;
    }

    public void setSatisfactionMultiplier(float satisfactionMultiplier) {
        this.satisfactionMultiplier = satisfactionMultiplier;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public float getCoinsPerSecond() {
        return coinsPerSecond;
    }

    public void setCoinsPerSecond(float coinsPerSecond) {
        this.coinsPerSecond = coinsPerSecond;
    }



}
