package com.vikingz.unitycoon.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.Dictionary;

/**
 * This class contains all the static data,
 * or data loaded from a file.
 */
public class BuildingStats {


    /**
     * Enum of types of buildings available
     */
    public enum BuildingType {
        NONE,
        ACADEMIC,
        ACCOMODATION,
        RECREATIONAL,
        FOOD,

    }





    /**
     * dictionaries of all the buildings in files
     * BuildingNameDict contains the full names of every Building, lookup using BuildingType
     * BuildingPriceDict contains the price of every building, lookup using BuildingType
     * BuildingSatisfactionDict contains the satisfaction of every building, lookup using BuildingType
     * BuildingStudentDict contains the student increase of every building, lookup using BuildingType
     * BuildingCoinDict contains the coins per second of every building, lookup using BuildingType
     * BuildingDict contains the ShortHandNames of every building, lookup using BuildingType
     * BuildingTextureMap contains the positions of every building's Texture,
     *                           lookup using String of the buildings ShortHandName
     */

    //Loaded from buildingInfo.json
    public static Dictionary<BuildingType, String[]> BuildingNameDict;
    public static Dictionary<BuildingStats.BuildingType, String[]> BuildingPriceDict;
    public static Dictionary<BuildingStats.BuildingType, String[]> BuildingSatisfactionDict;
    public static Dictionary<BuildingStats.BuildingType, String[]> BuildingStudentDict;
    public static Dictionary<BuildingStats.BuildingType, String[]> BuildingCoinDict;
    public static Dictionary<BuildingStats.BuildingType, String[]> BuildingDict;
    public static ArrayList<String> BuildingIDs;

    //Loaded from TextureAtlasMap.json
    public static Dictionary<String, int[]> BuildingTextureMap;

    //Textures information
    public static String textureAtlasLocation;
    public static int atlasBuildingSize = 128;


    /**
     * Uses the params to lookup and convert values
     * into creating a new BuildingInfo Object
     * using the lookup dictionaries
     * @param buildingType contains Type of building from BuildingStats
     * @param index int contains the index of which building is being select from Dictionary
     * @return BuildingInfo
     */
    public static BuildingInfo getInfo(BuildingStats.BuildingType buildingType, int index){

        int price, student,coins;
        float satisfaction;


        //price
        try {price = Integer.parseInt(BuildingPriceDict.get(buildingType)[index]);}
        catch (Exception e){price = 100;}

        //Satisfaction
        try {satisfaction = Float.parseFloat(BuildingSatisfactionDict.get(buildingType)[index]);}
        catch (Exception e){satisfaction = 0f;}

        //Student
        try {student = Integer.parseInt(BuildingStudentDict.get(buildingType)[index]);}
        catch (Exception e) {student = 0;}

        //coins
        try {coins = Integer.parseInt(BuildingCoinDict.get(buildingType)[index]);}
        catch (Exception e) {coins = 0;}


        try {
            return new BuildingInfo(BuildingDict.get(buildingType)[index],
                "todo add name",
                buildingType,
                price,
                satisfaction,
                student,
                coins);
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * This class Creates the Texture Image for the Building
     * to be drawn with, this uses a lookup Dictionary created from json file.
     * @param id String name of the building
     * @return TextureRegion
     */
    public static TextureRegion getTextureOfBuilding(String id){
        Texture textureAtlas = new Texture(Gdx.files.internal(textureAtlasLocation)); // Load your 64x64 PNG
        try {
            return new TextureRegion(textureAtlas, atlasBuildingSize * BuildingTextureMap.get(id)[0],
                atlasBuildingSize * BuildingTextureMap.get(id)[1],
                atlasBuildingSize, atlasBuildingSize);
        }
        catch (Exception e){
            return null;
        }
    }

    /**
     * Returns a drawable Texture region, used for building ui.
     * @param id Selects which building is being used the building StringID
     * @return TextureRegionDrawable
     */
    public static TextureRegionDrawable getTextureDrawableOfBuilding(String id) {
        return new TextureRegionDrawable(BuildingStats.getTextureOfBuilding(id));
    }

}
