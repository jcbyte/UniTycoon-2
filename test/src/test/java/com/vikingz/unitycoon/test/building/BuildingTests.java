package com.vikingz.unitycoon.test.building;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.building.buildings.AcademicBuilding;
import com.vikingz.unitycoon.building.buildings.AccommodationBuilding;
import com.vikingz.unitycoon.building.buildings.FoodBuilding;
import com.vikingz.unitycoon.building.buildings.RecreationalBuilding;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.Point;
import com.vikingz.unitycoon.util.StatsCalculator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link Building}, {@link BuildingInfo} and related classes:
 * {@link com.vikingz.unitycoon.building.buildings.AcademicBuilding},
 * {@link com.vikingz.unitycoon.building.buildings.AccommodationBuilding},
 * {@link com.vikingz.unitycoon.building.buildings.FoodBuilding},
 * {@link com.vikingz.unitycoon.building.buildings.RecreationalBuilding}.
 */
public class BuildingTests extends AbstractHeadlessGdxTest {
  private static TextureRegion testTexture;

  /**
   * Initialise shared resources.
   */
  @BeforeAll
  public static void setupAll() {
    testTexture = new TextureRegion();
  }

  /**
   * Dispose of shared resources.
   */
  @AfterAll
  public static void teardownAll() {
    // If there is a texture associated then dispose of it
    if (testTexture.getTexture() != null) {
      testTexture.getTexture().dispose();
    }
  }

  @Test
  public void testAcademicBuilding() {
    BuildingInfo i = new BuildingInfo(
        "TEST_ACADEMIC",
        "TestAcademicBuilding",
        BuildingStats.BuildingType.ACADEMIC,
        200,
        2.3f,
        500,
        200);
    AcademicBuilding b = new AcademicBuilding(testTexture, new Point(23, -14), i);
    assertEquals("TEST_ACADEMIC", b.getBuildingInfo().getBuildingId());
    assertEquals("TestAcademicBuilding", b.getBuildingInfo().getName());
    assertEquals(BuildingStats.BuildingType.ACADEMIC, b.getBuildingInfo().getBuildingType());
    assertEquals(200, b.getBuildingInfo().getBuildingCost());
    assertEquals(2.3f, b.getBuildingInfo().getSatisfactionMultiplier());
    assertEquals(500, b.getBuildingInfo().getNumberOfStudents());
    assertEquals(200, b.getBuildingInfo().getCoinsPerSecond());
    assertEquals(BuildingStats.BuildingType.ACADEMIC, b.getBuildingType());
    assertEquals(23, b.getX());
    assertEquals(-14, b.getY());
    assertEquals(StatsCalculator.calculateSatisfaction(500, 2.3f),
        b.calculateSatisfaction(b.getBuildingInfo().getNumberOfStudents()));
    assertEquals(128, b.getHeight());
    assertEquals(128, b.getWidth());
    assertEquals(testTexture, b.getTexture());
  }

  @Test
  public void testAccommodationBuilding() {
    BuildingInfo i = new BuildingInfo(
        "TEST_ACCOMMODATION",
        "TestAccommodationBuilding",
        BuildingStats.BuildingType.ACCOMMODATION,
        250,
        1.0f,
        0,
        500);
    AccommodationBuilding b = new AccommodationBuilding(testTexture, new Point(0, 0), i);
    assertEquals("TEST_ACCOMMODATION", b.getBuildingInfo().getBuildingId());
    assertEquals("TestAccommodationBuilding", b.getBuildingInfo().getName());
    assertEquals(BuildingStats.BuildingType.ACCOMMODATION, b.getBuildingInfo().getBuildingType());
    assertEquals(250, b.getBuildingInfo().getBuildingCost());
    assertEquals(1.0f, b.getBuildingInfo().getSatisfactionMultiplier());
    assertEquals(0, b.getBuildingInfo().getNumberOfStudents());
    assertEquals(500, b.getBuildingInfo().getCoinsPerSecond());
    assertEquals(BuildingStats.BuildingType.ACCOMMODATION, b.getBuildingType());
    assertEquals(0, b.getX());
    assertEquals(0, b.getY());
    assertEquals(StatsCalculator.calculateSatisfaction(0, 1.0f),
        b.calculateSatisfaction(b.getBuildingInfo().getNumberOfStudents()));
    assertEquals(128, b.getHeight());
    assertEquals(128, b.getWidth());
    assertEquals(testTexture, b.getTexture());
  }

  @Test
  public void testFoodBuilding() {
    BuildingInfo i = new BuildingInfo(
        "TEST_FOOD",
        "TestFoodBuilding",
        BuildingStats.BuildingType.FOOD,
        10,
        3.1f,
        13,
        99);
    FoodBuilding b = new FoodBuilding(testTexture, new Point(-99, 100), i, 99);
    assertEquals("TEST_FOOD", b.getBuildingInfo().getBuildingId());
    assertEquals("TestFoodBuilding", b.getBuildingInfo().getName());
    assertEquals(BuildingStats.BuildingType.FOOD, b.getBuildingInfo().getBuildingType());
    assertEquals(10, b.getBuildingInfo().getBuildingCost());
    assertEquals(3.1f, b.getBuildingInfo().getSatisfactionMultiplier());
    assertEquals(13, b.getBuildingInfo().getNumberOfStudents());
    assertEquals(99, b.getBuildingInfo().getCoinsPerSecond());
    assertEquals(StatsCalculator.calculateProfitMade(99), b.calculateProfitMade());
    assertEquals(BuildingStats.BuildingType.FOOD, b.getBuildingType());
    assertEquals(-99, b.getX());
    assertEquals(100, b.getY());
    assertEquals(StatsCalculator.calculateSatisfaction(13, 3.1f),
        b.calculateSatisfaction(b.getBuildingInfo().getNumberOfStudents()));
    assertEquals(128, b.getHeight());
    assertEquals(128, b.getWidth());
    assertEquals(testTexture, b.getTexture());
  }

  @Test
  public void testRecreationalBuilding() {
    BuildingInfo i = new BuildingInfo(
        "TEST_RECREATIONAL",
        "TestRecreationalBuilding",
        BuildingStats.BuildingType.RECREATIONAL,
        70,
        0.8f,
        0,
        0);
    RecreationalBuilding b = new RecreationalBuilding(testTexture, new Point(0, 0), i, 0);
    assertEquals("TEST_RECREATIONAL", b.getBuildingInfo().getBuildingId());
    assertEquals("TestRecreationalBuilding", b.getBuildingInfo().getName());
    assertEquals(BuildingStats.BuildingType.RECREATIONAL, b.getBuildingInfo().getBuildingType());
    assertEquals(70, b.getBuildingInfo().getBuildingCost());
    assertEquals(0.8f, b.getBuildingInfo().getSatisfactionMultiplier());
    assertEquals(0, b.getBuildingInfo().getNumberOfStudents());
    assertEquals(0, b.getBuildingInfo().getCoinsPerSecond());
    assertEquals(StatsCalculator.calculateProfitMade(0), b.calculateProfitMade());
    assertEquals(BuildingStats.BuildingType.RECREATIONAL, b.getBuildingType());
    assertEquals(0, b.getX());
    assertEquals(0, b.getY());
    assertEquals(StatsCalculator.calculateSatisfaction(0, 0.8f),
        b.calculateSatisfaction(b.getBuildingInfo().getNumberOfStudents()));
    assertEquals(128, b.getHeight());
    assertEquals(128, b.getWidth());
    assertEquals(testTexture, b.getTexture());
  }

  @Test
  public void testSetBuildingAttributes() {
    BuildingInfo i = new BuildingInfo(
        "TEST_ACADEMIC",
        "TestAcademicBuilding",
        BuildingStats.BuildingType.ACADEMIC,
        200,
        2.3f,
        500,
        200);
    AcademicBuilding b = new AcademicBuilding(testTexture, new Point(23, -14), i);

    assertEquals(23, b.getX());
    assertEquals(-14, b.getY());
    b.setX(-12);
    b.setY(90);
    assertEquals(-12, b.getX());
    assertEquals(90, b.getY());

    assertEquals(128, b.getHeight());
    assertEquals(128, b.getWidth());
    b.setHeight(64);
    b.setWidth(64);
    assertEquals(64, b.getHeight());
    assertEquals(64, b.getWidth());

    assertEquals(testTexture, b.getTexture());
    TextureRegion newTexture = new TextureRegion();
    b.setTexture(newTexture);
    assertEquals(newTexture, b.getTexture());
    // If there is a texture associated then dispose of it
    if (newTexture.getTexture() != null) {
      newTexture.getTexture().dispose();
    }
  }
}
