package com.vikingz.unitycoon.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.vikingz.unitycoon.global.GameConfig;

public class GameMusic {

    private static Music backgroundMusic ;

    public static float volume = GameConfig.getInstance().getMusicVolumeValue();

    public GameMusic() {

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/Background_Music.ogg"));
        backgroundMusic.setLooping(true);
        //backgroundMusic.setVolume(50f);
    }


    public void play(){
        backgroundMusic.play();
    }

    public static float getVolume() {
        return volume;
    }

    public static void setVolume(float volume) {
        GameMusic.volume = volume;
        backgroundMusic.setVolume(volume);
    }
}
