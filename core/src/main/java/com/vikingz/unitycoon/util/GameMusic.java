package com.vikingz.unitycoon.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.render.BackgroundRenderer;

public class GameMusic {

    private static Music backgroundMusic ;

    public static float volume = GameConfig.getInstance().MusicVolumeValue;

    public GameMusic() {

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/Background_Music.ogg"));
        backgroundMusic.setLooping(true);
        //backgroundMusic.setVolume(50f);
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
