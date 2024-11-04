package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class SuperScreen {
    

    public Stage stage;

    public void takeInput(){

        Gdx.input.setInputProcessor(stage);
    }

}
