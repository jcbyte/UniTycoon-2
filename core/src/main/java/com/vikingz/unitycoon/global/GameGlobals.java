package com.vikingz.unitycoon.global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * This class is used to store all the constants and global values that are needed from different
 * classes.
 *
 * <p>This class only provides public static attributes and public static methods.
 */
public class GameGlobals {

  private static final int startingBALANCE = 500;

  //Static stats of the current game
  public static int BALANCE = startingBALANCE;
  public static int SATISFACTION = 0;
  public static int STUDENTS = 0;
  public static int ACADEMIC_BUILDINGS_COUNT = 0;
  public static int ACCOMMODATION_BUILDINGS_COUNT = 0;
  public static int RECREATIONAL_BUILDINGS_COUNT = 0;
  public static int FOOD_BUILDINGS_COUNT = 0;
  public static int ELAPSED_TIME = 0;

  // Load map textures
  public static final Texture map1Texture = new Texture(Gdx.files.internal("png/map1Texture.png"));
  public static final Texture map2Texture = new Texture(Gdx.files.internal("png/map2Texture.png"));
  public static final Texture map3Texture = new Texture(Gdx.files.internal("png/map3Texture.png"));

  public static final TextureRegionDrawable backGroundDrawable = new TextureRegionDrawable(
      new Texture("png/background.png"));

  //Loads map as a drawable to allow changing Image Actors to different images
  public static final TextureRegionDrawable map1Draw = new TextureRegionDrawable(map1Texture);
  public static final TextureRegionDrawable map2Draw = new TextureRegionDrawable(map2Texture);
  public static final TextureRegionDrawable map3Draw = new TextureRegionDrawable(map3Texture);
  public static final TextureRegionDrawable[] mapArray = new TextureRegionDrawable[]{
      map1Draw, map2Draw, map3Draw};

  /**
   * Resets the game globals to the original values.
   *
   * @param time The amount of time in minutes the game should last for
   */
  public static void resetGlobals(int time) {
    ELAPSED_TIME = time;
    ACADEMIC_BUILDINGS_COUNT = 0;
    ACCOMMODATION_BUILDINGS_COUNT = 0;
    RECREATIONAL_BUILDINGS_COUNT = 0;
    FOOD_BUILDINGS_COUNT = 0;
    SATISFACTION = 0;
    STUDENTS = 0;
    BALANCE = startingBALANCE;
  }
}
