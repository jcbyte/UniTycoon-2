package com.vikingz.unitycoon.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;


public class FileHandler {


    public static String loadMap(String fileName){
        String mapData = "";
        FileHandle fileHandle = Gdx.files.internal("maps/" + fileName + ".txt");

        // Check if the file exists
        if (fileHandle.exists()) {
            // Read the content as a string
            mapData = fileHandle.readString();

        } else {
            System.out.println("File not found!");
        }

        return mapData;
    }


}
