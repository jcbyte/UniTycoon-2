package com.vikingz.unitycoon.test.building;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.building.buildings.AcademicBuilding;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.Point;
import com.vikingz.unitycoon.util.StatsCalculator;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link Building}, {@link BuildingInfo} and related classes:
 * {@link com.vikingz.unitycoon.building.buildings.AcademicBuilding},
 * {@link com.vikingz.unitycoon.building.buildings.AccommodationBuilding},
 * {@link com.vikingz.unitycoon.building.buildings.FoodBuilding},
 * {@link com.vikingz.unitycoon.building.buildings.RecreationalBuilding}.
 */
public class BuildingTests extends AbstractHeadlessGdxTest {
  private final TextureRegion testTexture = new TextureRegion();

  @Test
  public void testAcademicBuilding() {
    BuildingInfo i = new BuildingInfo(
        "TEST_AC",
        "TestAcBuilding",
        BuildingStats.BuildingType.ACADEMIC,
        200,
        2.3f,
        500,
        200);
    AcademicBuilding b = new AcademicBuilding(testTexture, new Point(23, -14), i);
    assertEquals("TEST_AC", b.getBuildingInfo().getBuildingId());
    assertEquals(200, b.getBuildingInfo().getBuildingCost());
    assertEquals(BuildingStats.BuildingType.ACADEMIC, b.getBuildingInfo().getBuildingType());
    assertEquals(2.3f, b.getBuildingInfo().getSatisfactionMultiplier());
    assertEquals("TestAcBuilding", b.getBuildingInfo().getName());
    assertEquals(200, b.getBuildingInfo().getCoinsPerSecond());
    assertEquals(500, b.getBuildingInfo().getNumberOfStudents());
    assertEquals(BuildingStats.BuildingType.ACADEMIC, b.getBuildingType());
    assertEquals(23, b.getX());
    assertEquals(-14, b.getY());
    assertEquals(StatsCalculator.calculateSatisfaction(500, 2.3f),
        b.calculateSatisfaction(b.getBuildingInfo().getNumberOfStudents()));
    assertEquals(128, b.getHeight());
    assertEquals(128, b.getWidth());
    assertEquals(testTexture, b.getTexture());
  }

//
//  @Test
//  public void testSetDimensions() {
//    Building testBuilding = new Building(testTexture, 0, 0, testBuildingInfo);
//
//    testBuilding.setHeight(0);
//    testBuilding.setWidth(0);
//    assertEquals(testBuilding.getWidth(), 0);
//    assertEquals(testBuilding.getHeight(), 0);
//
//    testBuilding.setHeight(9000);
//    testBuilding.setWidth(9000);
//    assertEquals(testBuilding.getHeight(), 9000);
//    assertEquals(testBuilding.getWidth(), 9000);
//  }
//
//  @Test
//  public void testSetCoordinates() {
//    Building testBuilding = new Building(testTexture, 0, 0, testBuildingInfo);
//
//    testBuilding.setX(5);
//    testBuilding.setY(5);
//    assertEquals(testBuilding.getX(), 5);
//    assertEquals(testBuilding.getY(), 5);
//
//    testBuilding.setX(9000);
//    testBuilding.setY(9000);
//    assertEquals(testBuilding.getX(), 9000);
//    assertEquals(testBuilding.getY(), 9000);
//  }
//
//  @Test
//  public void testToString() {
//    Building testBuilding = new Building(testTexture, 0, 0, testBuildingInfo);
//    String str = "";
//
//    str += "x: 0" + this.posX;
//    str += " y: 0" + this.posY;
//    str += " width: 128" + this.width;
//    str += " height: 128" + this.height;
//    str += " type: ???" + this.buildingType;
//
//
//    assertEquals(testBuilding.toString(), str);
//  }


}
