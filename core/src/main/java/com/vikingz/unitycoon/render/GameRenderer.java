package com.vikingz.unitycoon.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.util.Point;

/**
 * This class contains all the renderers that render the game.
 *
 * <p>This class contains the renderers that draw the background as well as the buildings.
 * Using this class enables us to have a separate viewport that controls how the game is rendered
 * as well as what happens when the game window is resized.
 */
public class GameRenderer {

  private final GameScreen gameScreen;

  private final Stage stage;
  private final Camera camera;
  private final Viewport viewport;

  private final BackgroundRenderer backgroundRenderer;
  private final BuildingRenderer buildingRenderer;

  /**
   * Creates new Game Renderer.
   *
   * @param mapName Name of the map to be drawn as the background
   */
  public GameRenderer(GameScreen gameScreen, String mapName) {
    this.gameScreen = gameScreen;

    // Creates and camera and set up the viewport
    camera = new OrthographicCamera();
    viewport = new FitViewport(
        GameConfig.getInstance().getWindowWidth(),
        GameConfig.getInstance().getWindowHeight()
    );

    stage = new Stage(viewport);
    backgroundRenderer = new BackgroundRenderer(mapName);
    buildingRenderer = new BuildingRenderer(this);
  }

  /**
   * Draws the game contents to the screen.
   *
   * @param delta Time since last frame
   */
  public void render(float delta) {
    viewport.apply();
    stage.getViewport().apply();
    camera.update();

    backgroundRenderer.render(delta);
    buildingRenderer.render(delta);
  }

  /**
   * Translates screen coordinates to game canvas coordinates.
   *
   * @param p Point on the screen
   * @return Point on the game canvas
   */
  public Point translateCoords(Point p) {
    Vector3 vec3 = new Vector3(p.getX(), p.getY(), 0);
    Vector3 vec3Translated = viewport.unproject(vec3);
    return new Point(vec3Translated.x, vec3Translated.y);
  }

  /**
   * Updates renderers when the window is resized.
   *
   * @param width  New width
   * @param height New height
   */
  public void resize(int width, int height) {
    viewport.update(width, height);
    backgroundRenderer.resize(width, height);
    buildingRenderer.resize(width, height);
  }

  /**
   * used to get BuildingRenderer from parent GameRenderer.
   *
   * @return BuildingRenderer
   */
  public BuildingRenderer getBuildingRenderer() {
    return buildingRenderer;
  }

  /**
   * used to get BackgroundRenderer from parent GameRenderer.
   *
   * @return BackgroundRenderer
   */
  public BackgroundRenderer getBackgroundRenderer() {
    return backgroundRenderer;
  }

  /**
   * Dispose for garbage collection.
   */
  public void dispose() {
    stage.dispose();
    backgroundRenderer.dispose();
    buildingRenderer.dispose();
  }

  /**
   * Get the scaling value from the FitViewport.
   */
  public float getViewportScaling() {
    float heightScaling = viewport.getScreenHeight() / viewport.getWorldHeight();
    float widthScaling = viewport.getScreenWidth() / viewport.getWorldWidth();
    return Math.min(heightScaling, widthScaling);
  }

  public GameScreen getGameScreen() {
    return gameScreen;
  }
}
