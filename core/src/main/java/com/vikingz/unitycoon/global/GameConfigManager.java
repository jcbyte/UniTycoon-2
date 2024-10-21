package com.vikingz.unitycoon.global;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;

public class GameConfigManager {
    

    public static void setFullScreen(){
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    }

    public static void setWindowScreen(){
        Gdx.graphics.setWindowedMode(GameConfig.WINDOW_WIDTH,GameConfig.WINDOW_HEIGHT);
    }

    public static String CurrentWindowSize(){
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        if (Gdx.graphics.isFullscreen()) return displayMode.toString();
        return Integer.toString(GameConfig.WINDOW_WIDTH).concat("x").concat(Integer.toString(GameConfig.WINDOW_HEIGHT)).concat(" bpp ").concat(Integer.toString(displayMode.bitsPerPixel)).concat(" hz ").concat(Integer.toString(Gdx.graphics.getFramesPerSecond()));
    }



    public static void saveGameConfig(){

        GameConfig conf = new GameConfig();

    
        try {
            FileOutputStream fileOut = new FileOutputStream("gameconfig/gameconf.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(conf);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved.");

        } catch (IOException i) {
            i.printStackTrace();
        }
        

    }


    public static void loadGameConfig(){

        

    }




}
