package com.vikingz.unitycoon.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vikingz.unitycoon.global.GameGlobals;

/**
 * This class represents the screen where the user chooses the map they want to play.
 *
 * <p>This class contains a bunch of buttons that allow the user to cycle through the different
 * maps available as well as go back to the main menu or play game.
 */
public class MapSelectorScreen extends SuperScreen implements Screen {

  //Text that shows which map is currently selected
  private final TextField mapText;

  //List of map Image that are used to set the preview image
  private final TextureRegionDrawable[] mapArray;

  //Current map Selected
  private int mapSelection = 1;

  /**
   * Creates a new Map selector screen.
   */
  public MapSelectorScreen() {
    super();

    //Loads maps from global config
    mapArray = GameGlobals.mapArray;

    Image mapImage = new Image(GameGlobals.map1Texture);

    // Selects the next map
    TextButton nextMap = new TextButton("->", skin);
    nextMap.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        mapSelection++;
        if (mapSelection > 3) {
          mapSelection = 1;
        }
        mapImage.setDrawable(mapArray[mapSelection - 1]);
      }
    });

    // Selects the previous map
    TextButton previousMap = new TextButton("<-", skin);
    previousMap.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        mapSelection--;
        if (mapSelection < 1) {
          mapSelection = mapArray.length;
        }
        mapImage.setDrawable(mapArray[mapSelection - 1]);
      }
    });

    mapText = new TextField("map".concat(Integer.toString(mapSelection)), skin);

    TextButton startGame = new TextButton("Start Game", skin);
    startGame.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        ScreenMultiplexer.runGame(mapText.getText().toLowerCase());
      }
    });

    TextButton goBack = new TextButton("Go Back", skin);
    goBack.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        ScreenMultiplexer.switchScreens(ScreenMultiplexer.Screens.MENU);
      }
    });

    // Create table for layout
    Table table = new Table();
    table.setFillParent(true);
    table.center();

    table.add(mapImage).colspan(3).width(300).height(350).pad(10).row();
    table.add(previousMap).pad(10);
    table.add(mapText).pad(10);
    table.add(nextMap).pad(10).row();
    table.add(startGame).colspan(3).pad(10).padTop(80).row();
    table.add(goBack).colspan(3).pad(10);

    // Add the table to the stage
    stage.addActor(table);
  }

  @Override
  public void show() {
    // This method is called when the screen is shown
  }

  /**
   * Renders the stage to the screen.
   *
   * @param delta Time since last frame
   */
  @Override
  public void render(float delta) {
    // Clear the screen
    ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);

    mapText.setText("Map".concat(Integer.toString(mapSelection)));

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
    stage.dispose();
    skin.dispose();
  }
}
