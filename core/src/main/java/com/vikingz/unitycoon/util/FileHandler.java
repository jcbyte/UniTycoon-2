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
    public String[] ACCOMODATION;
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
      String fileRead = fileHandle.readString();

      String[] arrayDict = fileRead.split("\n");
      Gson gson = new Gson();
      if (arrayDict.length != 6) {
        System.out.println("File corruption detected");
      }

      // Name
      BuildingParse nameParser = gson.fromJson(arrayDict[0], BuildingParse.class);
      BuildingStats.BuildingNameDict = new Hashtable<>();
      BuildingStats.BuildingNameDict.put(ACADEMIC, nameParser.ACADEMIC);
      BuildingStats.BuildingNameDict.put(ACCOMMODATION, nameParser.ACCOMODATION);
      BuildingStats.BuildingNameDict.put(RECREATIONAL, nameParser.RECREATIONAL);
      BuildingStats.BuildingNameDict.put(FOOD, nameParser.FOOD);
      BuildingStats.BuildingNameDict.put(NONE, nameParser.NONE);

      // Price
      BuildingParse priceParser = gson.fromJson(arrayDict[1], BuildingParse.class);
      BuildingStats.BuildingPriceDict = new Hashtable<>();
      BuildingStats.BuildingPriceDict.put(ACADEMIC, priceParser.ACADEMIC);
      BuildingStats.BuildingPriceDict.put(ACCOMMODATION, priceParser.ACCOMODATION);
      BuildingStats.BuildingPriceDict.put(RECREATIONAL, priceParser.RECREATIONAL);
      BuildingStats.BuildingPriceDict.put(FOOD, priceParser.FOOD);
      BuildingStats.BuildingPriceDict.put(NONE, priceParser.NONE);

      // Students
      BuildingParse studentParser = gson.fromJson(arrayDict[2], BuildingParse.class);
      BuildingStats.BuildingStudentDict = new Hashtable<>();
      BuildingStats.BuildingStudentDict.put(ACADEMIC, studentParser.ACADEMIC);
      BuildingStats.BuildingStudentDict.put(ACCOMMODATION, studentParser.ACCOMODATION);
      BuildingStats.BuildingStudentDict.put(RECREATIONAL, studentParser.RECREATIONAL);
      BuildingStats.BuildingStudentDict.put(FOOD, studentParser.FOOD);
      BuildingStats.BuildingStudentDict.put(NONE, studentParser.NONE);

      // Satisfaction
      BuildingParse satisfactionParser = gson.fromJson(arrayDict[3], BuildingParse.class);
      BuildingStats.BuildingSatisfactionDict = new Hashtable<>();
      BuildingStats.BuildingSatisfactionDict.put(ACADEMIC, satisfactionParser.ACADEMIC);
      BuildingStats.BuildingSatisfactionDict.put(ACCOMMODATION, satisfactionParser.ACCOMODATION);
      BuildingStats.BuildingSatisfactionDict.put(RECREATIONAL, satisfactionParser.RECREATIONAL);
      BuildingStats.BuildingSatisfactionDict.put(FOOD, satisfactionParser.FOOD);
      BuildingStats.BuildingSatisfactionDict.put(NONE, satisfactionParser.NONE);

      // Coins
      BuildingParse coinParser = gson.fromJson(arrayDict[4], BuildingParse.class);
      BuildingStats.BuildingCoinDict = new Hashtable<>();
      BuildingStats.BuildingCoinDict.put(ACADEMIC, coinParser.ACADEMIC);
      BuildingStats.BuildingCoinDict.put(ACCOMMODATION, coinParser.ACCOMODATION);
      BuildingStats.BuildingCoinDict.put(RECREATIONAL, coinParser.RECREATIONAL);
      BuildingStats.BuildingCoinDict.put(FOOD, coinParser.FOOD);
      BuildingStats.BuildingCoinDict.put(NONE, coinParser.NONE);

      // Ids
      BuildingParse idParser = gson.fromJson(arrayDict[5], BuildingParse.class);
      BuildingStats.BuildingDict = new Hashtable<>();
      BuildingStats.BuildingDict.put(ACADEMIC, idParser.ACADEMIC);
      BuildingStats.BuildingDict.put(ACCOMMODATION, idParser.ACCOMODATION);
      BuildingStats.BuildingDict.put(RECREATIONAL, idParser.RECREATIONAL);
      BuildingStats.BuildingDict.put(FOOD, idParser.FOOD);
      BuildingStats.BuildingDict.put(NONE, idParser.NONE);

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
