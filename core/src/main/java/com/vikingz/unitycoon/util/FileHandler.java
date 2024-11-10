package com.vikingz.unitycoon.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vikingz.unitycoon.building.BuildingStats;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Dictionary;
import java.util.Hashtable;

import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.*;


/**
 * This class handles loading in files
 *
 * Main use is to load in the map file
 * and loading building information
 */
public class FileHandler {


    public static String loadMap(String fileName){
        String mapData = "";
        FileHandle fileHandle = Gdx.files.internal("maps/" + fileName + ".txt");
        //FileHandle fileHandle = Gdx.files.internal("maps/map1.txt");

        // Check if the file exists
        if (fileHandle.exists()) {
            // Read the content as a string
            mapData = fileHandle.readString();

        } else {
            System.out.println("File not found!: " + fileHandle.toString());
        }

        return mapData;
    }



    /**
     * Loads Building information maps,
     * into static Dictionaries in BuildingStats
     * @param fileName String
     */
    public static void loadBuildings(String fileName)  {
        FileHandle fileHandle = Gdx.files.internal("config/" + fileName + ".json");
        if (fileHandle.exists()) {

            //JSON HANDLE
            String fileRead = fileHandle.readString();
            String[] arrayDict = fileRead.split("\n");
            Gson gson = new Gson();
            if (arrayDict.length != 6){
                System.out.println("FILE CORRUPTION DETECTED");
            }

            //Name
            BuildingParse nameParser = gson.fromJson(arrayDict[0],BuildingParse.class);

            BuildingStats.BuildingNameDict = new Hashtable<BuildingStats.BuildingType, String[]>(){{
                put(ACADEMIC, nameParser.ACADEMIC);
                put(ACCOMODATION, nameParser.ACCOMODATION);
                put(RECREATIONAL, nameParser.RECREATIONAL);
                put(FOOD, nameParser.FOOD);
                put(NONE, nameParser.NONE);
            }};

            //Price
            BuildingParse priceParser = gson.fromJson(arrayDict[1],BuildingParse.class);
            BuildingStats.BuildingPriceDict = new Hashtable<BuildingStats.BuildingType, String[]>(){{
                put(ACADEMIC, priceParser.ACADEMIC);
                put(ACCOMODATION, priceParser.ACCOMODATION);
                put(RECREATIONAL, priceParser.RECREATIONAL);
                put(FOOD, priceParser.FOOD);
                put(NONE, priceParser.NONE);
            }};

            //Student
            BuildingParse studentParser = gson.fromJson(arrayDict[2],BuildingParse.class);
            BuildingStats.BuildingStudentDict = new Hashtable<BuildingStats.BuildingType, String[]>(){{
                put(ACADEMIC, studentParser.ACADEMIC);
                put(ACCOMODATION, studentParser.ACCOMODATION);
                put(RECREATIONAL, studentParser.RECREATIONAL);
                put(FOOD, studentParser.FOOD);
                put(NONE, studentParser.NONE);
            }};

            //Satisfaction
            BuildingParse satisfactionParser = gson.fromJson(arrayDict[3],BuildingParse.class);
            BuildingStats.BuildingSatisfactionDict = new Hashtable<BuildingStats.BuildingType, String[]>(){{
                put(ACADEMIC, satisfactionParser.ACADEMIC);
                put(ACCOMODATION, satisfactionParser.ACCOMODATION);
                put(RECREATIONAL, satisfactionParser.RECREATIONAL);
                put(FOOD, satisfactionParser.FOOD);
                put(NONE, satisfactionParser.NONE);
            }};

            //Coins

            BuildingParse coinParser = gson.fromJson(arrayDict[4],BuildingParse.class);
            BuildingStats.BuildingCoinDict = new Hashtable<BuildingStats.BuildingType, String[]>(){{
                put(ACADEMIC, coinParser.ACADEMIC);
                put(ACCOMODATION, coinParser.ACCOMODATION);
                put(RECREATIONAL, coinParser.RECREATIONAL);
                put(FOOD, coinParser.FOOD);
                put(NONE, coinParser.NONE);
            }};

            //ID
            BuildingParseId idParser = gson.fromJson(arrayDict[5],BuildingParseId.class);
            BuildingStats.BuildingDict = new Hashtable<BuildingStats.BuildingType, BuildingStats.BuildingID[]>(){{
                put(ACADEMIC, idParser.ACADEMIC);
                put(ACCOMODATION, idParser.ACCOMODATION);
                put(RECREATIONAL, idParser.RECREATIONAL);
                put(FOOD, idParser.FOOD);
                put(NONE, idParser.NONE);
            }};

            System.out.println("Successfully Loaded Buildings");

        } else {
            System.err.println("File not found!: " + fileHandle.toString());
        }
    }


}

/**
 * Object parser that has public variable
 * to allow map json to dictionary.
 */
class BuildingParse {
    public String ACADEMIC[];
    public String ACCOMODATION[];
    public String RECREATIONAL[];
    public String FOOD[];
    public String NONE[];
}
class BuildingParseId {

    public BuildingStats.BuildingID ACADEMIC[];
    public BuildingStats.BuildingID ACCOMODATION[];
    public BuildingStats.BuildingID RECREATIONAL[];
    public BuildingStats.BuildingID FOOD[];
    public BuildingStats.BuildingID NONE[];
}

