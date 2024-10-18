package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.vikingz.unitycoon.global.GameConfig;

public class SettingsScreen implements Screen {

    private Game game;
    private Stage stage;
    private Skin skin;
    private String volume;

    private Slider volumeSlider;
    private Label volumeLabel;

    public SettingsScreen(Game game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json")); // Default UI skin

        // Create volume slider

        volumeSlider = new Slider(0, 100, 1, false, skin); // Min: 0, Max: 100, Step: 1
        volumeSlider.setValue(GameConfig.VOLUME_VALUE);

        volumeLabel = new Label(volume, skin);
        this.volume = "Volume: " + String.valueOf(volumeSlider.getValue());


        // Back button to return to MenuScreen
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(e -> {
            if (!backButton.isPressed()) return false;
            game.setScreen(new MenuScreen(game)); // Navigate back to MenuScreen
            return true;
        });

        // Create layout table
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        // Add elements to the table
        table.add(volumeLabel).pad(10);
        table.row();
        table.add(volumeSlider).fillX().uniformX().pad(10);
        table.row();
        table.add(backButton).fillX().uniformX().pad(10);

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
        GameConfig.VOLUME_VALUE = volumeSlider.getValue();


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
