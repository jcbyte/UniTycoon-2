package com.vikingz.unitycoon.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.vikingz.unitycoon.global.GameConfig;

public class GameMusic {

    private static Music backgroundMusic;



    public GameMusic() {

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/Background_Music.ogg"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(GameConfig.getInstance().getVolumeValue());
    }


    public void play(){
        backgroundMusic.play();
    }


    public static void setVolume(float volume){
        backgroundMusic.setVolume(volume);
    }
    

}
