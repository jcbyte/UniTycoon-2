package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameSkins;

public abstract class SuperScreen {
    

    public Stage stage;
    public Camera  camera;
    public Viewport viewport;

    public SpriteBatch batch;
    public GameSkins skinLoader;

    public SuperScreen(){
        camera = new OrthographicCamera();
        viewport = new FillViewport(GameConfig.getInstance().getWindowWidth(), GameConfig.getInstance().getWindowHeight());
        stage = new Stage(new ScreenViewport());

        batch = new SpriteBatch();
        skinLoader = new GameSkins();

    }

    public void takeInput(){

        Gdx.input.setInputProcessor(stage);
    }

}
