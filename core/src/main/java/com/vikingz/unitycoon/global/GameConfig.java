package com.vikingz.unitycoon.global;

import com.badlogic.gdx.Gdx;

public class GameConfig {

    public static boolean SKIP_MENUS = false;
    public static int WINDOW_WIDTH = 1820;
    public static int WINDOW_HEIGHT = 980;
    public static float VOLUME_VALUE = 60;

    public static void setFullScreen(){
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    }

    public static void setWindowScreen(){
        Gdx.graphics.setWindowedMode(WINDOW_WIDTH,WINDOW_HEIGHT);
    }

    public static int getWINDOW_WIDTH(){
        return WINDOW_WIDTH;
    }
    public static int getWindowHeight(){
        return WINDOW_HEIGHT;
    }

}
