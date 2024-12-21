package com.vikingz.unitycoon.util;

import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.ACADEMIC;
import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.ACCOMMODATION;
import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.FOOD;
import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.NONE;
import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.RECREATIONAL;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;
import com.vikingz.unitycoon.building.BuildingStats;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This class handles loading in files.
 *
 * <p>Main use is to load in the map file and loading building information
 */
public class FileHandler {
  /**
   * Object parser that has public variable to allow map json to dictionary.
   */
  @SuppressWarnings({"checkstyle:MemberName", "checkstyle:AbbreviationAsWordInName"})
  private static class BuildingParse {
    public String[] ACADEMIC;
    public String[] ACCOMMODATION;
    public String[] RECREATIONAL;
    public String[] FOOD;
    public String[] NONE;
  }

  /**
   * Object parser that has public variable to allow map json to dictionary.
   */
  @SuppressWarnings({"checkstyle:MemberName", "checkstyle:AbbreviationAsWordInName"})
  private static class TextureParse {
    public String textureAtlasLocation = "textures/textureAtlases/buildingsAtlas.png";
    public int atlasBuildingSize = 128;
    ArrayList<String> buildings;
    ArrayList<String> buildingPos;
  }

  /**
   * Loads in the map from a file.
   *
   * @param fileName Name of the file to load in
   * @return String The map
   */
  public static String loadMap(String fileName) {
    String mapData = "";
    FileHandle fileHandle = Gdx.files.internal("gameData/maps/" + fileName + ".txt");

    // Check if the file exists
    if (fileHandle.exists()) {
      // Read the content as a string
      mapData = fileHandle.readString();
    } else {
      System.out.println("File not found!: " + fileHandle);
    }

    return mapData;
  }

  /**
   * Loads Building information maps into static Dictionaries in BuildingStats.
   */
  public static void loadBuildings() {
    FileHandle fileHandle = Gdx.files.internal("gameData/buildings/buildingInfo.json");
    FileHandle textureFileHandle = Gdx.files.internal("gameData/buildings/buildingsAtlasMap.json");

    if (fileHandle.exists() && textureFileHandle.exists()) {
      // Json handle
      Gson gson = new Gson();
      BuildingParse[] buildingInfo = gson.fromJson(fileHandle.readString(), BuildingParse[].class);

      if (buildingInfo.length != 6) {
        System.out.println("File corruption detected");
      }

      // Name
      BuildingStats.BuildingNameDict = new Hashtable<>();
      BuildingStats.BuildingNameDict.put(ACADEMIC, buildingInfo[0].ACADEMIC);
      BuildingStats.BuildingNameDict.put(ACCOMMODATION, buildingInfo[0].ACCOMMODATION);
      BuildingStats.BuildingNameDict.put(RECREATIONAL, buildingInfo[0].RECREATIONAL);
      BuildingStats.BuildingNameDict.put(FOOD, buildingInfo[0].FOOD);
      BuildingStats.BuildingNameDict.put(NONE, buildingInfo[0].NONE);

      // Price
      BuildingStats.BuildingPriceDict = new Hashtable<>();
      BuildingStats.BuildingPriceDict.put(ACADEMIC, buildingInfo[1].ACADEMIC);
      BuildingStats.BuildingPriceDict.put(ACCOMMODATION, buildingInfo[1].ACCOMMODATION);
      BuildingStats.BuildingPriceDict.put(RECREATIONAL, buildingInfo[1].RECREATIONAL);
      BuildingStats.BuildingPriceDict.put(FOOD, buildingInfo[1].FOOD);
      BuildingStats.BuildingPriceDict.put(NONE, buildingInfo[1].NONE);

      // Students
      BuildingStats.BuildingStudentDict = new Hashtable<>();
      BuildingStats.BuildingStudentDict.put(ACADEMIC, buildingInfo[2].ACADEMIC);
      BuildingStats.BuildingStudentDict.put(ACCOMMODATION, buildingInfo[2].ACCOMMODATION);
      BuildingStats.BuildingStudentDict.put(RECREATIONAL, buildingInfo[2].RECREATIONAL);
      BuildingStats.BuildingStudentDict.put(FOOD, buildingInfo[2].FOOD);
      BuildingStats.BuildingStudentDict.put(NONE, buildingInfo[2].NONE);

      // Satisfaction
      BuildingStats.BuildingSatisfactionDict = new Hashtable<>();
      BuildingStats.BuildingSatisfactionDict.put(ACADEMIC, buildingInfo[3].ACADEMIC);
      BuildingStats.BuildingSatisfactionDict.put(ACCOMMODATION, buildingInfo[3].ACCOMMODATION);
      BuildingStats.BuildingSatisfactionDict.put(RECREATIONAL, buildingInfo[3].RECREATIONAL);
      BuildingStats.BuildingSatisfactionDict.put(FOOD, buildingInfo[3].FOOD);
      BuildingStats.BuildingSatisfactionDict.put(NONE, buildingInfo[3].NONE);

      // Coins
      BuildingStats.BuildingCoinDict = new Hashtable<>();
      BuildingStats.BuildingCoinDict.put(ACADEMIC, buildingInfo[4].ACADEMIC);
      BuildingStats.BuildingCoinDict.put(ACCOMMODATION, buildingInfo[4].ACCOMMODATION);
      BuildingStats.BuildingCoinDict.put(RECREATIONAL, buildingInfo[4].RECREATIONAL);
      BuildingStats.BuildingCoinDict.put(FOOD, buildingInfo[4].FOOD);
      BuildingStats.BuildingCoinDict.put(NONE, buildingInfo[4].NONE);

      // Ids
      BuildingStats.BuildingDict = new Hashtable<>();
      BuildingStats.BuildingDict.put(ACADEMIC, buildingInfo[5].ACADEMIC);
      BuildingStats.BuildingDict.put(ACCOMMODATION, buildingInfo[5].ACCOMMODATION);
      BuildingStats.BuildingDict.put(RECREATIONAL, buildingInfo[5].RECREATIONAL);
      BuildingStats.BuildingDict.put(FOOD, buildingInfo[5].FOOD);
      BuildingStats.BuildingDict.put(NONE, buildingInfo[5].NONE);

      // Passing child elements from types
      Enumeration<String[]> buildingIdsIterator = BuildingStats.BuildingDict.elements();
      BuildingStats.BuildingIds = new ArrayList<>();
      while (buildingIdsIterator.hasMoreElements()) {
        for (String item : buildingIdsIterator.nextElement()) {
          if (item != null) {
            BuildingStats.BuildingIds.add(item);
          }
        }
      }

      // Textures
      String textureFileRead = textureFileHandle.readString();

      TextureParse textureParse = gson.fromJson(textureFileRead, TextureParse.class);
      BuildingStats.textureAtlasLocation = textureParse.textureAtlasLocation;
      BuildingStats.atlasBuildingSize = textureParse.atlasBuildingSize;

      BuildingStats.BuildingTextureMap = new Hashtable<>();
      for (int i = 0; i < textureParse.buildings.size(); i++) {
        int[] convertValue = new int[]{
            Integer.parseInt(textureParse.buildingPos.get(i).split(",")[0]),
            Integer.parseInt(textureParse.buildingPos.get(i).split(",")[1]),
        };
        BuildingStats.BuildingTextureMap.put(textureParse.buildings.get(i), convertValue);
      }

      System.out.println("Successfully Loaded Buildings");
    } else {
      System.err.println("File not found!: " + fileHandle);
    }
  }
}
