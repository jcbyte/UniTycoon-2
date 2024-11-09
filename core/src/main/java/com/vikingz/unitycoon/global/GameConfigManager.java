package com.vikingz.unitycoon.global;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;


/**
 * This class allows us to save the GameConfig to a binary file.
 * It contains methods for saving and loading a game configuration.
 */
public class GameConfigManager {


    /**
     * Sets game to fullscreen
     */
    public static void setFullScreen(){
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

    }

    /**
     * Sets game to a windowed screen
     */
    public static void setWindowScreen(){
        Gdx.graphics.setWindowedMode(GameConfig.getInstance().getWindowWidth() ,GameConfig.getInstance().getWindowHeight());
    }

    public static String CurrentWindowSize(){
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        if (Gdx.graphics.isFullscreen()) return displayMode.toString();
        return Integer.toString(GameConfig.getInstance().getWindowWidth()).concat("x").concat(Integer.toString(GameConfig.getInstance().getWindowHeight())).concat(" bpp ").concat(Integer.toString(displayMode.bitsPerPixel)).concat(" hz ").concat(Integer.toString(Gdx.graphics.getFramesPerSecond()));
    }



    /**
     * Saves game config
     */
    public static void saveGameConfig(){
        try {
            FileOutputStream fileOut = new FileOutputStream("config/gameconf.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(GameConfig.getInstance());
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved.");

        } catch (IOException i) {
            System.out.println("FILE NOT FOUND");
        }


    }


    /**
     * Loads game config
     */
    public static void loadGameConfig(){

        GameConfig conf = null;
        try {
            FileInputStream fileIn = new FileInputStream("config/gameconf.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            conf = (GameConfig) in.readObject();
            in.close();
            fileIn.close();

            GameConfig.getInstance().setInstance(conf);
            System.out.println("\n\nLoaded GameConfig");
            System.out.println("TOP_SATISFACTION: " + GameConfig.getInstance().TOP_SATISFACTION);
            System.out.println("Music_Volume: " + GameConfig.getInstance().MusicVolumeValue);
            System.out.println("Song_Volume: " + GameConfig.getInstance().SoundVolumeValue);



        } catch (IOException i) {
            System.out.println("FILE NOT FOUND");
            //i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("GameConfig class not found");
            //c.printStackTrace();
            return;
        }



    }




}
