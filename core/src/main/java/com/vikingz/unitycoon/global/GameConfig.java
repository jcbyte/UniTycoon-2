package com.vikingz.unitycoon.global;

import com.badlogic.gdx.Gdx;

import java.io.Serializable;


public class GameConfig implements Serializable{

    // Constants for width and height
    private int windowWidth;
    private int windowHeight;
    private boolean skipMenus;
    private float volumeValue;
    private static boolean VSync = false;
    private float guiSize = 1;


    // 31.5 rows
    // 56 cols




    // The single instance of GameConfig (eager initialization)
    private static GameConfig INSTANCE = new GameConfig(
        1792, 1008, false, 60); // Default values

    // Private constructor to prevent instantiation from outside
    private GameConfig(int width, int height, boolean skipMenus, float volumeValue) {
        this.windowWidth = width;
        this.windowHeight = height;
        this.skipMenus = skipMenus;
        this.volumeValue = volumeValue;

    }

    //Sets VSync mode for game on or off
    public static boolean setVSync(boolean enable){
        VSync = enable;
        Gdx.graphics.setVSync(enable);
        return VSync;
    }
    public static boolean getVSync(){
        return VSync;
    }

    public void setInstance(GameConfig conf){
        INSTANCE = conf;
    }


    // Method to get the single instance of GameConfig
    public static GameConfig getInstance() {
        return INSTANCE;
    }

    // Getter methods for WIDTH and HEIGHT
    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public boolean isSkipMenus() {
        return skipMenus;
    }

    public float getVolumeValue() {
        return volumeValue;
    }

    public void setVolumeValue(float newVolValue){
        this.volumeValue = newVolValue;
    }
    public float getGuiSize() {
        return guiSize;
    }

    public void setGuiSize(float guiSize) {
        this.guiSize = guiSize;
    }
}




