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

    /**
     * Initialises the Game music class by loading in the 
     * sound files and settings initial settings
     */
    public void init() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/Background_Music.ogg"));
        backgroundMusic.setLooping(true);
    }

    /**
     * Plays the game music 
     */
    public static void play(){
        backgroundMusic.setVolume(volume);
        backgroundMusic.play();
    }

    /**
     * Gets the volume of the music
     * @return Float Volume level
     */
    public static float getVolume() {
        return volume;
    }

    /**
     * Sets the volume of the music
     * @param volume New volume level
     */
    public static void setVolume(float volume) {
        GameConfig.getInstance().MusicVolumeValue = volume;
        GameMusic.volume = GameConfig.getInstance().MusicVolumeValue;
        play();
    }

}
