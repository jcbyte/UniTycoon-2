package com.vikingz.unitycoon.util;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.vikingz.unitycoon.global.GameConfig;

/**
 * GameSounds
 * This class loads and plays all of the sounds effects for the entire game
 */
public class GameSounds {

    // Load the sounds fx
    private static Sound placeBuilding1 = Gdx.audio.newSound(Gdx.files.internal("audio/place_1.ogg"));
    private static Sound placeBuilding2 = Gdx.audio.newSound(Gdx.files.internal("audio/place_2.ogg"));
    private static Sound placeBuilding3 = Gdx.audio.newSound(Gdx.files.internal("audio/place_3.ogg"));

    private static Sound placeError1 = Gdx.audio.newSound(Gdx.files.internal("audio/place_error_1.ogg"));
    private static Sound placeError2 = Gdx.audio.newSound(Gdx.files.internal("audio/place_error_2.ogg"));

    public static float volume = GameConfig.getInstance().getSoundVolumeValue();

    // Could be useful for more sounds later on
    private static ArrayList<Sound> sounds = new ArrayList<Sound>(){
        {
            add(placeBuilding1);
            add(placeBuilding2);
            add(placeBuilding3);
            add(placeError1);
            add(placeError2);
        }
    };

    // Plays placed building sound
    public static void playPlacedBuilding(){
        int randNum = new Random().nextInt(1, 4);

        switch (randNum) {
            case 1:
                placeBuilding1.play(volume);
                break;

            case 2:
                placeBuilding2.play(volume);
                break;

            case 3:
                placeBuilding3.play(volume);
                break;

            default:
                break;
        }
    }



    // Plays placed error building sound
    public static void playPlaceError(){
        int randNum = new Random().nextInt(1, 3);

        switch (randNum) {
            case 1:
                placeError1.play(volume);
                break;

            case 2:
                placeError2.play(volume);
                break;


            default:
                break;
        }
    }





}
