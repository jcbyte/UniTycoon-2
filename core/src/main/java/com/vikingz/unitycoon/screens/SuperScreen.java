package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameSkins;

/**
 * This is an abstract class that contains all of the components
 * that all other screens use, and therefore by creating them in here
 * we de-clutter all of the other screen classes.
 */
public abstract class SuperScreen {
    
    public Stage stage;
    private Camera  camera;
    private Viewport viewport;

    public SpriteBatch batch;
    private GameSkins skinLoader;
    public Skin skin;

    /**
     * Defines a Super screen constructor that other buildings
     * that inherit from this abstract class use as a base case
     */
    public SuperScreen(){
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.getInstance().getWindowWidth(), GameConfig.getInstance().getWindowHeight());
        stage = new Stage(new ScreenViewport());

        batch = new SpriteBatch();
        skinLoader = new GameSkins();
        skin = skinLoader.getQuantumSkin();

    }

    /**
     * Sets the input processor to this screen
     */
    public void takeInput(){
        Gdx.input.setInputProcessor(stage);
    }

}
