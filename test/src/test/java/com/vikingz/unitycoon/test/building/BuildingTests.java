package com.vikingz.unitycoon.test.building;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.BuildingStats;


import org.junit.jupiter.api.Test;

public class BuildingTests extends AbstractHeadlessGdxTest{
    
    TextureRegion testTexture = new TextureRegion();
    BuildingStats.BuildingType testBuildingType = new BuildingStats.BuildingType();

    /*
     * building info
     * ID: 1
     * name: testBuilding
     * Type: nul
     * cost: 200
     * multiplyer: 2.3
     * NumOfStu: 500
     * coinsPerSec: 200
     */
    BuildingInfo testBuildingInfo = new BuildingInfo("1", "TestBuilding", testBuildingType, 200, 2.3, 500, 200);
    
    @Test
    public void testcalculateSatisfaction(){
        Building testBuilding = new Building(testTexture, 0, 0, testBuildingInfo);
        assertEquals(testBuilding.calculateSatisfaction(500), 1150);
    }

    @Test 
    public void testGetDimensions (){
        Building testBuilding = new Building(testTexture, 0, 0, testBuildingInfo);
        assertEquals(testBuilding.getWidth(), 128);
        assertEquals(testBuilding.getHeight(), 128);
    }

    @Test
    public void testSetDimensions(){
        Building testBuilding = new Building(testTexture, 0, 0, testBuildingInfo);

        testBuilding.setHeight(0);
        testBuilding.setWidth(0);
        assertEquals(testBuilding.getWidth(), 0);
        assertEquals(testBuilding.getHeight(), 0);

        testBuilding.setHeight(9000);
        testBuilding.setWidth(9000);
        assertEquals(testBuilding.getHeight(), 9000);
        assertEquals(testBuilding.getWidth(), 9000);
    }

    @Test
    public void testGetCoordinates(){
        Building testBuilding = new Building(testTexture, 0, 0, testBuildingInfo);
        assertEquals(testBuilding.getX(), 0);
        assertEquals(testBuilding.getY(), 0);
    }

    @Test
    public void testSetCoordinates(){
        Building testBuilding = new Building(testTexture, 0, 0, testBuildingInfo);

        testBuilding.setX(5);
        testBuilding.setY(5);
        assertEquals(testBuilding.getX(), 5);
        assertEquals(testBuilding.getY(), 5);

        testBuilding.setX(9000);
        testBuilding.setY(9000);
        assertEquals(testBuilding.getX(), 9000);
        assertEquals(testBuilding.getY(), 9000);
    }

    @Test
    public void testTexture(){
        Building testBuilding = new Building(testTexture, 0, 0, testBuildingInfo);
        assertEquals(testBuilding.getTexture(), nul);
        //add set textures
    }

    @Test
    public void testGetBuildingType(){
        Building testBuilding = new Building(testTexture, 0, 0, testBuildingInfo);
        assertEquals(testBuilding.getBuildingType(), testBuildingType);
    }

    @Test
    public void testToString(){
        Building testBuilding = new Building(testTexture, 0, 0, testBuildingInfo);
        String str = "";

        str += "x: 0" + this.posX;
        str += " y: 0" + this.posY;
        str += " width: 128" + this.width;
        str += " height: 128" + this.height;
        str += " type: ???" + this.buildingType;


        assertEquals(testBuilding.toString(), str);
    }

    @Test 
    public void testGetBuildingInfo(){
        Building testBuilding = new Building(testTexture, 0, 0, testBuildingInfo);
        assertEquals(testBuilding.getBuildingInfo(), testBuildingInfo);
    }

   
}
