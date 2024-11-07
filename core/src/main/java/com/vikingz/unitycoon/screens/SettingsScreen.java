package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameConfigManager;

public class SettingsScreen extends SuperScreen implements Screen {

    private Label resolutionLabel;
    private String volume;

    private Slider volumeSlider;
    private Label volumeLabel;

    private ScreenMultiplexer.Screens previousScreen;

    private TextButton backButton;
    TextButton fullscreenButton;
    TextButton windowButton;
    TextButton saveGameConfigButton;

    private GameScreen gameScreen;


    public SettingsScreen() {
        super();

        this.previousScreen = ScreenMultiplexer.Screens.MENU;
        this.resolutionLabel = new Label(GameConfigManager.CurrentWindowSize(), skin);

        // Create volume slider
        volumeSlider = new Slider(0, 100, 1, false, skin); // Min: 0, Max: 100, Step: 1
        volumeSlider.setValue(GameConfig.getInstance().getVolumeValue());
        volumeLabel = new Label(volume, skin);
        this.volume = "Volume: " + String.valueOf(volumeSlider.getValue());




        // Back button to return to MenuScreen
        backButton = new TextButton("Back", skin);
        // backButton.addListener(e -> {
        //     if (backButton.isPressed()){
        //         ScreenMultiplexer.switchScreens(previousScreen);
        //     }
        //     return true;
        // });

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ScreenMultiplexer.switchScreens(previousScreen);

            }
        });

        fullscreenButton = new TextButton("Fullscreen",skin);
        fullscreenButton.addListener(e -> {
            if (fullscreenButton.isPressed()){
                GameConfigManager.setFullScreen();
                gameScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                
            }
            return true;
        });

        windowButton = new TextButton("Window Mode",skin);
        windowButton.addListener(e -> {
            if (windowButton.isPressed()){
                GameConfigManager.setWindowScreen();
            }
            return true;
        });

        saveGameConfigButton = new TextButton("Save",skin);
        saveGameConfigButton.addListener(e -> {
            if (saveGameConfigButton.isPressed()){
                GameConfigManager.saveGameConfig();
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


        table.add(fullscreenButton).fillX().uniformX().pad(10);
        table.add(saveGameConfigButton).fillX().pad(10);
        table.add(windowButton).fillX().uniformX().pad(10);
        table.row();


        table.add((Actor) null);
        table.add(backButton).fillX().pad(10);
        table.row();


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
        resolutionLabel.setText(GameConfigManager.CurrentWindowSize());
        GameConfig.getInstance().setVolumeValue(volumeSlider.getValue());

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

    public void disableButtons(){
        System.out.println("Disabled");
        backButton.setTouchable(Touchable.disabled);
    }


    public void enableButtons(){
        System.out.println("Enabled");
        backButton.setTouchable(Touchable.enabled);
    }

    public void setPrevScreen(ScreenMultiplexer.Screens prevScreen){
        this.previousScreen = prevScreen;
    }
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

}
