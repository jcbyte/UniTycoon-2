package com.vikingz.unitycoon.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Dictionary;
import java.util.Hashtable;

import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.*;


public class BuildingStats {


    public enum BuildingType {
        NONE,
        ACADEMIC,
        ACCOMODATION,
        RECREATIONAL,
        FOOD,

    }


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


    //Lookup Table for values of buildings
    //TODO make it read all info of a building from a json file
    public static final  Dictionary<BuildingType, String[]> BuildingNameDict = new Hashtable<BuildingType, String[]>(){{
        put(ACADEMIC, new String[]{"Ron Cooke","Piazza"});
        put(ACCOMODATION, new String[]{"David Kato","Anne Lister"});
        put(RECREATIONAL, new String[]{"YSV"});
        put(FOOD, new String[]{"McD"});
        put(NONE, new String[]{"NONE"});
    }};
    public static final  Dictionary<BuildingStats.BuildingType, String[]> BuildingPriceDict = new Hashtable<BuildingStats.BuildingType, String[]>() {{
        put(ACADEMIC, new String[]{"100","150"});
        put(ACCOMODATION, new String[]{"100","100"});
        put(RECREATIONAL, new String[]{"200"});
        put(FOOD, new String[]{"200"});
        put(NONE, new String[]{"NONE"});
    }};
    public static final  Dictionary<BuildingStats.BuildingType, String[]> BuildingStudentDict = new Hashtable<BuildingStats.BuildingType, String[]>(){{
        put(ACADEMIC, new String[]{"Satisfaction: 0.5 students/second","Satisfaction: 0.8 students/second"});
        put(ACCOMODATION, new String[]{"Students: +100","Students: +150"});
        put(RECREATIONAL, new String[]{"Satisfaction: 0.5 students/second"});
        put(FOOD, new String[]{"Satisfaction: 0.5 students/second"});
        put(NONE, new String[]{"NONE"});
    }};
    public static final Dictionary<BuildingStats.BuildingType, BuildingStats.BuildingID[]> BuildingDict = new Hashtable<BuildingStats.BuildingType, BuildingStats.BuildingID[]>(){{
        put(ACADEMIC,new BuildingStats.BuildingID[]{BuildingStats.BuildingID.RCH, BuildingStats.BuildingID.PZA});
        put(ACCOMODATION,new BuildingStats.BuildingID[]{BuildingStats.BuildingID.KATO, BuildingStats.BuildingID.LISTER});
        put(RECREATIONAL,new BuildingStats.BuildingID[]{BuildingStats.BuildingID.YSV});
        put(FOOD,new BuildingStats.BuildingID[]{BuildingStats.BuildingID.MCD});
        put(NONE,new BuildingStats.BuildingID[]{null});
    }};


    // Academic
    public static int PZA_COST = 100;
    public static float PZA_SATISFACTION_MULT = 1.1f;

    public static int RCH_COST = 100;
    public static float RCH_SATISFACTION_MULT = 1.1f;


    // Accomodation
    public static int KATO_COST = 100;
    public static float KATO_SATISFACTION_MULT = 1.1f;
    public static int KATO_STUDENT_CAPACITY = 100;

    public static int LISTER_COST = 100;
    public static float LISTER_SATISFACTION_MULT = 1.1f;
    public static int LISTER_STUDENT_CAPACITY = 150;


    // Recreational
    public static int YSV_COST = 100;
    public static float YSV_SATISFACTION_MULT = 1.5f;
    public static float YSV_COINS_PER_SECOND = 10;



    // Food
    public static int MCD_COST = 100;
    public static float MCD_SATISFACTION_MULT = 1.1f;
    public static float MCD_COINS_PER_SECOND = 10;




    public static BuildingInfo getInfo(BuildingID id){

        switch (id) {
            // Academic
            case PZA:
                return new BuildingInfo(id, BuildingType.ACADEMIC, PZA_COST, PZA_SATISFACTION_MULT);

            case RCH:
                return new BuildingInfo(id, BuildingType.ACADEMIC, RCH_COST, RCH_SATISFACTION_MULT);

            // Accomodation
            case KATO:
                return new BuildingInfo(id, BuildingType.ACCOMODATION, KATO_COST, PZA_SATISFACTION_MULT, KATO_STUDENT_CAPACITY);

            case LISTER:
                return new BuildingInfo(id, BuildingType.ACCOMODATION, LISTER_COST, PZA_SATISFACTION_MULT, LISTER_STUDENT_CAPACITY);

            // Recreational
            case YSV:
                return new BuildingInfo(id, BuildingType.RECREATIONAL, YSV_COST, YSV_SATISFACTION_MULT, YSV_COINS_PER_SECOND);

            // Food
            case MCD:
                return new BuildingInfo(id, BuildingType.FOOD, MCD_COST, MCD_SATISFACTION_MULT, MCD_COINS_PER_SECOND);
        }

        return null;

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
        }
        return null;
    }

    public static TextureRegionDrawable getTextureDrawableOfBuilding(BuildingID id) {
        return new TextureRegionDrawable(BuildingStats.getTextureOfBuilding(id));
    }


}
