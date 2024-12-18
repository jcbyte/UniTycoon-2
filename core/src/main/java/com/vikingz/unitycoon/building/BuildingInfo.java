package com.vikingz.unitycoon.building;


import com.vikingz.unitycoon.building.BuildingStats.BuildingType;

/**
 * This class essentially serves as a struct to pass data around regarding buildings.
 * When the user presses a button in the menu to buy a building, the data for that building
 * is passed around a type BuildingInfo
 */
public class BuildingInfo {
    String  buildingID;
    String name;
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
     * @param buildingName Building name
     * @param buildingType Building Type
     * @param buildingCost Building Cost
     * @param satisfactionMultiplier Satisfaction Multiplier
     * @param numberOfStudents Number of students
     * @param coinsPerSecond Coins per second the building generates
     */
    public BuildingInfo(String buildingID, String buildingName, BuildingStats.BuildingType buildingType, float buildingCost, float satisfactionMultiplier, int numberOfStudents, float coinsPerSecond){
        this.name = buildingName;
        this.buildingID = buildingID;
        this.buildingType = buildingType;
        this.buildingCost = buildingCost;
        this.satisfactionMultiplier = satisfactionMultiplier;
        this.numberOfStudents = numberOfStudents;
        this.coinsPerSecond = coinsPerSecond;


    }

    //Getters and Setters

    /**
     * Gets Friendly Building name
     * @return string of the friendly building name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets Building ID ENUM
     * @return string of BuildingID
     */
    public String getBuildingID() {
        return buildingID;
    }

    /**
     * Gets Building ID ENUM
     * @return BuildingType
     */
    public BuildingType getBuildingType() {
        return buildingType;
    }

    /**
     * Gets BuildingCost float which is initialized by constructor
     * @return buildingCost float
     */
    public float getBuildingCost() {
        return buildingCost;
    }


    /**
     * Gets SatisfactionMultiplier float which is initialized by constructor
     * @return satisfactionMultiplier float
     */
    public float getSatisfactionMultiplier() {
        return satisfactionMultiplier;
    }

    /**
     * gets the number of students which is initialized by constructor
     * @return numberOfStudents float
     */
    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    /**
     * Gets the coins per second float which is initialized by constructor
     * @return coinsPerSecond float
     */
    public float getCoinsPerSecond() {
        return coinsPerSecond;
    }

}
