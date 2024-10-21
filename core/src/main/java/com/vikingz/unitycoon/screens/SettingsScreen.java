package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.global.GameSkins;

public class SettingsScreen implements Screen {

    private Label resolutionLabel;
    private Game game;
    private Stage stage;
    private Skin skin;
    private String volume;

    private Slider volumeSlider;
    private Label volumeLabel;
    private GameSkins skinLoader;

    public SettingsScreen(Game game, GameSkins skinLoader) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Get Skins variable from Skin loader
        this.skinLoader = skinLoader;
        skin = skinLoader.getDefaultSkin();

        this.resolutionLabel = new Label(GameConfig.CurrentWindowSize(),skin);

        // Create volume slider
        volumeSlider = new Slider(0, 100, 1, false, skin); // Min: 0, Max: 100, Step: 1
        volumeSlider.setValue(GameConfig.VOLUME_VALUE);

        volumeLabel = new Label(volume, skin);
        this.volume = "Volume: " + String.valueOf(volumeSlider.getValue());

        // Back button to return to MenuScreen
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(e -> {
            if (!backButton.isPressed()) return false;
            game.setScreen(new MenuScreen(game,skinLoader)); // Navigate back to MenuScreen
            return true;
        });

        TextButton Debug = new TextButton("Fullscreen",skin);
        Debug.addListener(e -> {
            if (Debug.isPressed()){
                GameConfig.setFullScreen();
            }
            return true;
        });

        TextButton window = new TextButton("Window Mode",skin);
        window.addListener(e -> {
            if (window.isPressed()){
                GameConfig.setWindowScreen();
            }
            return true;
        });

        // Create layout table
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        table.add((Actor) null);
        table.add(resolutionLabel);
        table.row();

        // Add elements to the table
        table.add((Actor) null);
        table.add(volumeLabel).uniformX().pad(10);
        table.row();


        table.add((Actor) null);
        table.add(volumeSlider).fillX().uniformX().pad(10);
        table.row();
        table.row();

        table.add(Debug).fillX().uniformX().pad(10);
        table.add(backButton).fillX().pad(10);
        table.add(window).fillX().uniformX().pad(10);
        table.row();


        // Add table to stage
        stage.addActor(table);
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        // Draw stage
        volume = "Volume: " + String.valueOf(volumeSlider.getValue());
        volumeLabel.setText(volume);
        resolutionLabel.setText(GameConfig.CurrentWindowSize());
        GameConfig.VOLUME_VALUE = volumeSlider.getValue();


        //Key bindings Escape
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MenuScreen(game,skinLoader)); // Navigate back to MenuScreen
        }

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {


        stage.dispose();
        skin.dispose();
    }
}
