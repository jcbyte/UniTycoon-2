package com.vikingz.unitycoon.global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


/**
 * This class is used to store all of the constants and global values
 * that need to be accessed from different classes.
 * 
 * This class only provideds public static attributes and public
 * statc methods. 
 */
public class GameGlobals {

    public static int BALANCE = 1000;
    public static int SATISFACTION = 0;
    public static int STUDENTS = 0;
    //public static int BUILDINGS_COUNT = 0;

    public static int ACADEMIC_BUILDINGS_COUNT = 0;
    public static int ACCOMODATION_BUILDINGS_COUNT = 0;
    public static int RECREATIONAL_BUILDINGS_COUNT = 0;
    public static int FOOD_BUILDINGS_COUNT = 0;

    public static int ELAPSED_TIME = 0;


    // Load map textures (replace with your own textures)
    public static final Texture map1Texture = new Texture(Gdx.files.internal("png/map1Texture.png"));
    public static final Texture map2Texture = new Texture(Gdx.files.internal("png/map2Texture.png"));
    public static final Texture map3Texture = new Texture(Gdx.files.internal("png/map3Texture.png"));

    public static final TextureRegionDrawable backGroundDrawable = new TextureRegionDrawable(new Texture("png/background.png"));
    public static final TextureRegionDrawable map1Draw = new TextureRegionDrawable(map1Texture);
    public static final TextureRegionDrawable map2Draw = new TextureRegionDrawable(map2Texture);
    public static final TextureRegionDrawable map3Draw = new TextureRegionDrawable(map3Texture);
    public static final TextureRegionDrawable[] mapArray = new TextureRegionDrawable[]{map1Draw, map2Draw, map3Draw};


    public static void resetGlobals(int time){
        ELAPSED_TIME = time;
        ACADEMIC_BUILDINGS_COUNT = 0;
        ACCOMODATION_BUILDINGS_COUNT = 0;
        RECREATIONAL_BUILDINGS_COUNT = 0;
        FOOD_BUILDINGS_COUNT = 0;
        SATISFACTION = 0;
        STUDENTS = 0;
        BALANCE = 1000;
    }

}
