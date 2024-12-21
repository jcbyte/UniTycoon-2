package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.util.LeaderboardUtils;

/**
 * This class represents the main menu of the game.
 *
 * <p>The main menu is where the user begins from.
 * This menu contains multiple buttons that allow the user to begin the game.
 */
public class MenuScreen extends SuperScreen implements Screen {
  /**
   * Creates a new menu screen.
   */
  public MenuScreen() {
    Gdx.input.setInputProcessor(stage);

    // Create buttons
    TextButton playButton = new TextButton("Play", skin);
    playButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        ScreenMultiplexer.switchScreens(ScreenMultiplexer.Screens.MAP_SELECTION);
      }
    });

    TextButton settingsButton = new TextButton("Settings", skin);
    settingsButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        ScreenMultiplexer.switchScreens(ScreenMultiplexer.Screens.SETTINGS);
      }
    });

    TextButton quitButton = new TextButton("Quit", skin);
    quitButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        Gdx.app.exit(); // Quit the application
      }
    });

    // Create a table for layout
    Table table = new Table();
    table.setFillParent(true);
    table.center();

    Image texture = new Image(new Texture(Gdx.files.internal("textures/gameLogo.png")));
    table.add(texture).pad(50).row();

    // Add buttons to table
    Table buttonsTable = new Table();
    buttonsTable.add(playButton).pad(10).row();
    buttonsTable.add(settingsButton).pad(10).row();
    buttonsTable.add(quitButton).pad(10);

    // Add the table to the stage
    table.add(buttonsTable);
    stage.addActor(table);

    // Create table for leaderboard
    Table leaderboardTable = new Table();
    leaderboardTable.setFillParent(true);
    leaderboardTable.bottom().right();

    Label leaderboardLabel = new Label(
        "Leaderboard:\n\n"
            + LeaderboardUtils.leaderboardToString(GameConfig.getInstance().getLeaderboard()),
        skin);
    leaderboardLabel.setFontScale(1.75f);

    // Add leaderboard to the stage
    leaderboardTable.add(leaderboardLabel).padBottom(20).padRight(100).bottom().right();
    stage.addActor(leaderboardTable);
  }

  @Override
  public void show() {
    // Called when this screen becomes the current screen for the game.
  }

  @Override
  public void render(float delta) {
    // Clear the screen
    ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1f);

    stage.act(delta);
    stage.draw();
  }

  /**
   * Updates the viewport when the window is resized.
   *
   * @param width  New width
   * @param height New height
   */
  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
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
    // Dispose of assets when this screen is no longer used
    stage.dispose();
    skin.dispose();
  }
}
