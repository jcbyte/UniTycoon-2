package com.vikingz.unitycoon.test.building;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.FileHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link  BuildingStats}.
 */
public class BuildingStatsTests extends AbstractHeadlessGdxTest {
  /**
   * Load building information into BuildingStats.
   */
  @BeforeAll
  public static void setupAll() {
    // Initialise Gdx.files
    Gdx.files = new HeadlessFiles();
    FileHandler.loadBuildings();
  }

  @Test
  public void testGetInfo() {
    BuildingInfo i = BuildingStats.getInfo(BuildingStats.BuildingType.FOOD, 0);
    assertNotNull(i);
    assertEquals("MCD", i.getBuildingId());
    assertEquals(200, i.getBuildingCost());
    assertEquals(BuildingStats.BuildingType.FOOD, i.getBuildingType());
    assertEquals(1.1f, i.getSatisfactionMultiplier());
    assertEquals("McD", i.getName());
    assertEquals(10, i.getCoinsPerSecond());
    assertEquals(0, i.getNumberOfStudents());

    BuildingInfo i1 = BuildingStats.getInfo(BuildingStats.BuildingType.NONE, 0);
    assertNotNull(i1);
    assertNull(i1.getBuildingId());
    assertEquals(100, i1.getBuildingCost());
    assertEquals(BuildingStats.BuildingType.NONE, i1.getBuildingType());
    assertEquals(0f, i1.getSatisfactionMultiplier());
    assertEquals("NONE", i1.getName());
    assertEquals(0, i1.getCoinsPerSecond());
    assertEquals(0, i1.getNumberOfStudents());

    BuildingInfo i2 = BuildingStats.getInfo(BuildingStats.BuildingType.ACCOMMODATION, 8790);
    assertNull(i2);
  }

  @Test
  public void testGetTexture() {
    TextureRegion t = BuildingStats.getTextureOfBuilding("GREGG");
    assertNotNull(t);
    if (t.getTexture() != null) {
      t.getTexture().dispose();
    }

    TextureRegion t1 = BuildingStats.getTextureOfBuilding("something crazy");
    assertNull(t1);
  }
}
