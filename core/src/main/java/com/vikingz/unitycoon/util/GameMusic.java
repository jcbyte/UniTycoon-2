package com.vikingz.unitycoon.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.vikingz.unitycoon.global.GameConfig;


/**
 * This class plays the game music throughout the game
 */
public class GameMusic {

    private static Music backgroundMusic;

    public static float volume = GameConfig.getInstance().MusicVolumeValue;

    public void init() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/Background_Music.ogg"));
        backgroundMusic.setLooping(true);
    }


    public static void play(){
        backgroundMusic.setVolume(volume);
        backgroundMusic.play();
    }

    public static float getVolume() {
        return volume;
    }

    public static void setVolume(float volume) {
        GameConfig.getInstance().MusicVolumeValue = volume;
        GameMusic.volume = GameConfig.getInstance().MusicVolumeValue;
        play();
    }

}
