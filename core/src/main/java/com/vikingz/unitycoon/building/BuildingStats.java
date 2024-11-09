package com.vikingz.unitycoon.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Dictionary;
import java.util.Hashtable;

import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.*;

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
    public static Dictionary<BuildingStats.BuildingType, String[]> BuildingStudentDict;
    public static Dictionary<BuildingStats.BuildingType, BuildingStats.BuildingID[]> BuildingDict;


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

            default:
                break;
        }
        return null;
    }

    public static TextureRegionDrawable getTextureDrawableOfBuilding(BuildingID id) {
        return new TextureRegionDrawable(BuildingStats.getTextureOfBuilding(id));
    }


}
