package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vikingz.unitycoon.audio.GameMusic;
import com.vikingz.unitycoon.audio.GameSounds;
import com.vikingz.unitycoon.global.GameConfigManager;

/**
 * This screen represents the settings screen in the game.
 *
 * <p>It contains multiple buttons and slider which edit different game settings
 */
public class SettingsScreen extends SuperScreen implements Screen {
  // Components on the settings screen
  private final Label resolutionLabel;
  private String resolutionString;
  private String musicVolume;
  private String soundVolume;

  // Music and Sounds Components
  private final Slider soundVolumeSlider;
  private final Label soundVolumeLabel;
  private final Slider musicVolumeSlider;
  private final Label musicVolumeLabel;

  // Stores the previous screen before settings
  private ScreenMultiplexer.Screens previousScreen;

  private GameScreen gameScreen;

  /**
   * Creates a new settings screen.
   */
  public SettingsScreen() {
    super();

    resolutionString = GameConfigManager.getFriendlyWindowSize();

    previousScreen = ScreenMultiplexer.Screens.MENU;
    resolutionLabel = new Label(GameConfigManager.getFriendlyWindowSize(), skin);
    resolutionLabel.setFontScale(2f);

    // Create Sound volume slider
    soundVolumeSlider = new Slider(0, 1, 0.1f, false, skin);
    soundVolumeSlider.setValue(GameSounds.getVolume());
    soundVolumeLabel = new Label(soundVolume, skin);
    soundVolumeLabel.setFontScale(1.8f);
    soundVolume = "Sound Volume: " + soundVolumeSlider.getValue();

    // Create Music volume slider
    musicVolumeSlider = new Slider(0, 1, 0.1f, false, skin);
    musicVolumeSlider.setValue(GameMusic.getVolume());
    musicVolumeLabel = new Label(soundVolume, skin);
    musicVolumeLabel.setFontScale(1.8f);
    this.musicVolume = "Music Volume: " + musicVolumeSlider.getValue();

    // Back button to return to MenuScreen
    TextButton backButton = new TextButton("Back", skin);
    backButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        goBack();
      }
    });

    // Button that fullscreen game
    TextButton fullscreenButton = new TextButton("Fullscreen", skin);
    fullscreenButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        GameConfigManager.setFullScreen();
        if (gameScreen != null) {
          gameScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
      }
    });

    //Button that makes the game window
    TextButton windowButton = new TextButton("Window Mode", skin);
    windowButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        GameConfigManager.setWindowScreen();
      }
    });

    //Saves the configuration of GameConfig
    TextButton saveGameConfigButton = new TextButton("Save", skin);
    saveGameConfigButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        GameConfigManager.saveGameConfig();
      }
    });

    // Create layout table
    Table table = new Table();
    table.setFillParent(true);
    table.center();

    table.add(resolutionLabel).colspan(2).row();
    table.add(soundVolumeLabel).colspan(2).uniformX().pad(10).row();
    table.add(soundVolumeSlider).colspan(2).uniformX().width(500).pad(10).row();
    table.add(musicVolumeLabel).colspan(2).uniformX().pad(10).row();
    table.add(musicVolumeSlider).colspan(2).uniformX().width(500).pad(10).row();
    table.add(fullscreenButton).fillX().uniformX().pad(10);
    table.add(windowButton).fillX().uniformX().pad(10).row();
    table.add(saveGameConfigButton).colspan(2).pad(10).padTop(60).row();
    table.add(backButton).colspan(2).uniformX().pad(10);

    // Add table to stage
    stage.addActor(table);
  }

  /**
   * Switches screens back to the screen the user accessed settings from.
   */
  public void goBack() {
    System.out.println(previousScreen.name());

    if (previousScreen.name().equals("GAME")) {
      ScreenMultiplexer.switchScreens(previousScreen);
    } else {
      ScreenMultiplexer.openMenu();
    }
  }

  @Override
  public void show() {
  }

  /**
   * Draws the components of the settings screen.
   *
   * @param delta Time since last frame
   */
  @Override
  public void render(float delta) {
    // Clear screen
    ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);

    // Go back when pressing ESC
    if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
      goBack();
    }

    soundVolume = "Sound Volume: " + Math.round(soundVolumeSlider.getValue() * 10);
    musicVolume = "Music Volume: " + Math.round(musicVolumeSlider.getValue() * 10);

    GameSounds.setVolume(soundVolumeSlider.getValue());
    GameMusic.setVolume(musicVolumeSlider.getValue());

    soundVolumeLabel.setText(soundVolume);
    musicVolumeLabel.setText(musicVolume);
    resolutionLabel.setText(resolutionString);

    stage.act(delta);
    stage.draw();
  }

  /**
   * Changes SettingScreen to new resolution and updates resolutionText.
   *
   * @param width  int resolution
   * @param height int resolution
   */
  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
    resolutionString = "Resolution: " + width + "x" + height;
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void hide() {
  }

  /**
   * Dispose for garbage collection.
   */
  @Override
  public void dispose() {

    stage.dispose();
    skin.dispose();
  }

  /**
   * Sets the previous screen.
   *
   * @param prevScreen Previous screen
   */
  public void setPrevScreen(ScreenMultiplexer.Screens prevScreen) {
    this.previousScreen = prevScreen;
  }

  public void setGameScreen(GameScreen gameScreen) {
    this.gameScreen = gameScreen;
  }

}
