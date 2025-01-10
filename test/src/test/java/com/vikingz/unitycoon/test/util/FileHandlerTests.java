package com.vikingz.unitycoon.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.FileHandler;
import com.vikingz.unitycoon.util.LeaderboardUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Tests checking {@link FileHandler}.
 */
public class FileHandlerTests extends AbstractHeadlessGdxTest {
  /**
   * Initialise Gdx.files for loading assets.
   */
  @BeforeAll
  public static void setupAll() {
    // Initialise Gdx.files
    Gdx.files = new HeadlessFiles();
  }

  @Test
  public void testLoadBuildings() {
    FileHandler.loadBuildings();
    assertEquals(5, BuildingStats.BuildingNameDict.size());
    assertEquals(5, BuildingStats.BuildingPriceDict.size());
    assertEquals(5, BuildingStats.BuildingStudentDict.size());
    assertEquals(5, BuildingStats.BuildingSatisfactionDict.size());
    assertEquals(5, BuildingStats.BuildingCoinDict.size());
    assertEquals(5, BuildingStats.BuildingDict.size());
    assertEquals(8, BuildingStats.BuildingIds.size());
    assertEquals("textures/textureAtlases/buildingsAtlas.png", BuildingStats.textureAtlasLocation);
    assertEquals(128, BuildingStats.atlasBuildingSize);
    assertEquals(8, BuildingStats.BuildingTextureMap.size());
  }
}
