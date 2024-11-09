package com.vikingz.unitycoon.global;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;

public class GameConfigManager {


    public static void setFullScreen(){
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

    }

    public static void setWindowScreen(){
        Gdx.graphics.setWindowedMode(GameConfig.getInstance().getWindowWidth() ,GameConfig.getInstance().getWindowHeight());
    }

    public static String CurrentWindowSize(){
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        if (Gdx.graphics.isFullscreen()) return displayMode.toString();
        return Integer.toString(GameConfig.getInstance().getWindowWidth()).concat("x").concat(Integer.toString(GameConfig.getInstance().getWindowHeight())).concat(" bpp ").concat(Integer.toString(displayMode.bitsPerPixel)).concat(" hz ").concat(Integer.toString(Gdx.graphics.getFramesPerSecond()));
    }



    public static void saveGameConfig(){
        try {
            FileOutputStream fileOut = new FileOutputStream("gameconf.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(GameConfig.getInstance());
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved.");

        } catch (IOException i) {
            System.out.println("FILE NOT FOUND");
            //i.printStackTrace();
        }


    }


    public static void loadGameConfig(){

        GameConfig conf = null;
        try {
            FileInputStream fileIn = new FileInputStream("gameconf.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            conf = (GameConfig) in.readObject();
            in.close();
            fileIn.close();

            GameConfig.getInstance().setInstance(conf);




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
