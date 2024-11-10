package com.vikingz.unitycoon.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Dictionary;

/**
 * This class contains all of the static data of the buildings
 *
 * Ideally we would make all of the data in this class be read in from a file
 *
 *
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

    // Buildings available
    public enum BuildingID {

        // Academic
        PZA,
        RCH,

        // Accomodation
        KATO,
        LISTER,

        // Recreational
        YSV, // York sports village

        // Food
        MCD, // Mc Donalds?

    }

    // Dicts for build menu
    public static Dictionary<BuildingType, String[]> BuildingNameDict;
    public static Dictionary<BuildingStats.BuildingType, String[]> BuildingPriceDict;
    public static Dictionary<BuildingStats.BuildingType, String[]> BuildingSatisfactionDict;
    public static Dictionary<BuildingStats.BuildingType, String[]> BuildingStudentDict;
    public static Dictionary<BuildingStats.BuildingType, String[]> BuildingCoinDict;
    public static Dictionary<BuildingStats.BuildingType, BuildingStats.BuildingID[]> BuildingDict;


    /**
     * Uses the params to lookup and convert values
     * into creating a new BuildingInfo Object
     * using the lookup dictionaries
     * @param buildingType
     * @param index
     * @return BuildingInfo
     */
    public static BuildingInfo getInfo(BuildingStats.BuildingType buildingType, int index){
        int price = 0, student,coins;
        float satisfaction;

        //price
        try {Integer.parseInt(BuildingPriceDict.get(buildingType)[index]);}
        catch (Exception e){price = 100;}

        //Satisfaction
        try {satisfaction = Float.valueOf(BuildingSatisfactionDict.get(buildingType)[index]);}
        catch (Exception e){satisfaction = 0f;}

        //Student
        try {student = Integer.parseInt(BuildingStudentDict.get(buildingType)[index]);}
        catch (Exception e) {student = 0;}

        //coins
        try {coins = Integer.parseInt(BuildingCoinDict.get(buildingType)[index]);}
        catch (Exception e) {coins = 0;}


        try {
            return new BuildingInfo(BuildingDict.get(buildingType)[index],
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

    public static TextureRegion getTextureOfBuilding(BuildingID id){
        Texture textureAtlas = new Texture(Gdx.files.internal("textureAtlases/buildingsAtlas.png")); // Load your 64x64 PNG
        int atlasBuildingSize = 128;
        switch (id){
            // Academic
            case PZA:
                return new TextureRegion(textureAtlas, 0, 0,atlasBuildingSize, atlasBuildingSize);

            case RCH:
                return new TextureRegion(textureAtlas, atlasBuildingSize, 0,atlasBuildingSize, atlasBuildingSize);

            // Academic
            case KATO:
                return new TextureRegion(textureAtlas, 0, atlasBuildingSize, atlasBuildingSize, atlasBuildingSize);

            case LISTER:
                return new TextureRegion(textureAtlas, atlasBuildingSize, atlasBuildingSize, atlasBuildingSize, atlasBuildingSize);

            // Recreational
            case YSV:
                return new TextureRegion(textureAtlas, 0, atlasBuildingSize * 2, atlasBuildingSize, atlasBuildingSize);

            // Food
            case MCD:
                return new TextureRegion(textureAtlas, 0, atlasBuildingSize * 3, atlasBuildingSize, atlasBuildingSize);

            default:
                break;
        }
        return null;
    }

    public static TextureRegionDrawable getTextureDrawableOfBuilding(BuildingID id) {
        return new TextureRegionDrawable(BuildingStats.getTextureOfBuilding(id));
    }

}
