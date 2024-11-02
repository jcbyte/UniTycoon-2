package com.vikingz.unitycoon.global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameGlobals {

    public static int BALANCE = 1000;
    public static int SATISFACTION = 0;
    public static int STUDENTS = 0;
    public static int BUILDINGS_COUNT = 0;



    // Load map textures (replace with your own textures)
    public static final  Texture map1Texture = new Texture(Gdx.files.internal("png/map1Texture.png"));
    public static final  Texture map2Texture = new Texture(Gdx.files.internal("png/map2Texture.png"));
    public static final Texture map3Texture = new Texture(Gdx.files.internal("png/map3Texture.png"));

    public static final TextureRegionDrawable backGroundDrawable = new TextureRegionDrawable(new Texture("png/background.png"));
    public static final  TextureRegionDrawable map1Draw = new TextureRegionDrawable(map1Texture);
    public static final TextureRegionDrawable map2Draw = new TextureRegionDrawable(map2Texture);
    public static final TextureRegionDrawable map3Draw = new TextureRegionDrawable(map3Texture);
    public static final TextureRegionDrawable[] mapArray = new TextureRegionDrawable[]{map1Draw, map2Draw, map3Draw};


}
