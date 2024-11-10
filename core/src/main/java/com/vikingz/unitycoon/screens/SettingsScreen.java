package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.util.GameMusic;
import com.vikingz.unitycoon.util.GameSounds;

/**
 * This screen represents the settings screen in the game
 * 
 * It contains multiple buttons and slider which edit different game settings
 * 
 * Inherits Screen, SuperScreen
 */
public class SettingsScreen extends SuperScreen implements Screen {


    // Components on the settings screen
    private Label resolutionLabel;
    private String resolutionString;
    private String musicVolume;
    private String soundVolume;

    private Slider SoundVolumeSlider;
    private Label SoundVolumeLabel;
    private Slider MusicVolumeSlider;
    private Label MusicVolumeLabel;

    private ScreenMultiplexer.Screens previousScreen;

    private TextButton backButton;
    private TextButton quitButton;
    private TextButton fullscreenButton;
    private TextButton windowButton;
    private TextButton saveGameConfigButton;

    private GameScreen gameScreen;


    /**
     * Creates a new settings screen
     */
    public SettingsScreen() {
        super();
        resolutionString = GameConfigManager.CurrentWindowSize();

        this.previousScreen = ScreenMultiplexer.Screens.MENU;
        this.resolutionLabel = new Label(GameConfigManager.CurrentWindowSize(), skin);

        // Create Sound volume slider
        SoundVolumeSlider = new Slider(0, 1, 0.1f, false, skin); // Min: 0, Max: 100, Step: 1
        SoundVolumeSlider.setValue(GameSounds.getVolume());
        SoundVolumeLabel = new Label(soundVolume, skin);
        this.soundVolume = "Sound Volume: " + String.valueOf(SoundVolumeSlider.getValue());

        // Create Music volume slider
        MusicVolumeSlider = new Slider(0, 1, 0.1f, false, skin); // Min: 0, Max: 100, Step: 1
        MusicVolumeSlider.setValue(GameMusic.getVolume());
        MusicVolumeLabel = new Label(soundVolume, skin);
        this.musicVolume = "Music Volume: " + String.valueOf(MusicVolumeSlider.getValue());


        // Adds event listeners to buttons 
        
        // Back button to return to MenuScreen
        backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                goBack();
            }
        });

        quitButton = new TextButton("Quit", skin);
        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit(); // Quit the application
            }
        });

        fullscreenButton = new TextButton("Fullscreen",skin);
        fullscreenButton.addListener(e -> {
            if (fullscreenButton.isPressed()){
                GameConfigManager.setFullScreen();
                if(gameScreen != null) { gameScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); }

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
        table.add(SoundVolumeLabel).uniformX().pad(10);
        table.row();

        table.add((Actor) null);
        table.add(SoundVolumeSlider).fillX().uniformX().pad(10);
        table.row();

        table.add((Actor) null);
        table.add(MusicVolumeLabel).uniformX().pad(10);
        table.row();

        table.add((Actor) null);
        table.add(MusicVolumeSlider).fillX().uniformX().pad(10);
        table.row();

        table.add(fullscreenButton).fillX().uniformX().pad(10);
        table.add(saveGameConfigButton).fillX().pad(10);
        table.add(windowButton).fillX().uniformX().pad(10);
        table.row();


        table.add((Actor) null);
        table.add(backButton).fillX().uniformX().pad(10);

        table.row();
        table.add((Actor) null);

        table.add(quitButton).fillX().pad(10);
        table.row();

        table.row();


        // Add table to stage
        stage.addActor(table);
    }

    /**
     * Switches screens back to the screen the user access the settings from
     */
    public void goBack(){
        System.out.println(previousScreen.name());
        if (previousScreen.name().equals("GAME")) {
            ScreenMultiplexer.switchScreens(previousScreen);
        }
        else {
            ScreenMultiplexer.openMenu();
        }
    }

    @Override
    public void show() { }

    /**
     * Draws the components of the settings screen
     * @param delta Time since last frame
     */
    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        //back button
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            goBack();
        }

        soundVolume = "Sound Volume: " + String.valueOf(Math.round(SoundVolumeSlider.getValue()*10));
        musicVolume = "Music Volume: " + String.valueOf(Math.round(MusicVolumeSlider.getValue()*10));

        GameSounds.setVolume(SoundVolumeSlider.getValue());
        GameMusic.setVolume(MusicVolumeSlider.getValue());

        SoundVolumeLabel.setText(soundVolume);
        MusicVolumeLabel.setText(musicVolume);
        resolutionLabel.setText(resolutionString);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        resolutionString = "Resolution: " + width + "x" + height;
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

    /**
     * Disables buttons on the screen 
     */
    public void disableButtons(){
        System.out.println("Disabled");
        backButton.setTouchable(Touchable.disabled);
    }

    /**
     * Enables buttons on the screen 
     */
    public void enableButtons(){
        System.out.println("Enabled");
        backButton.setTouchable(Touchable.enabled);
    }

    /**
     * Sets the previous screen
     * @param prevScreen Previous screen
     */
    public void setPrevScreen(ScreenMultiplexer.Screens prevScreen){
        this.previousScreen = prevScreen;
    }

    // Getters and setters
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

}
