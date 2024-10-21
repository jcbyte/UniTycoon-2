package com.vikingz.unitycoon.global;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GameConfigManager {
    

    public static void saveGameConfig(){

        GameConfig conf = new GameConfig();

        
        try {
            FileOutputStream fileOut = new FileOutputStream("gameconf.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(conf);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved.");

        } catch (IOException i) {
            i.printStackTrace();
        }
        

    }



}
