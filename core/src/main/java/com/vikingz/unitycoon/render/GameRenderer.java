package com.vikingz.unitycoon.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.util.Point;

/**
 * This class contains all the renderers that render the game.
 * <p>
 * This class contains the renderers that draw the background as well
 * as the buildings. Using this class enables us to have a separate viewport
 * that controls how the game is rendered as well as what happens when the
 * game window is resized, as we wanted the map and the buildings to resize
 * differently from the UI, which is what the {@code UIRenderer} is used for.
 */
public class GameRenderer {

    // Viewport stuff
    private final Stage stage;
    private final Camera camera;
    private final Viewport viewport;

    private final BackgroundRenderer backgroundRenderer;
    private final BuildingRenderer buildingRenderer;

    /**
     * Creates and new Game Renderer
     * @param mapName Name of the map to be drawn as the background
     */
    public GameRenderer(String mapName){

        // Creates and camera and set up the viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.getInstance().getWindowWidth(), GameConfig.getInstance().getWindowHeight());


        stage = new Stage(viewport);
        backgroundRenderer = new BackgroundRenderer(mapName);
        buildingRenderer = new BuildingRenderer(this);

    }

    /**
     * Draws the game contents to the screen
     * @param delta Time since last frame
     */
    public void render(float delta){

        viewport.apply();
        stage.getViewport().apply();
        camera.update();
        backgroundRenderer.render(delta);
        buildingRenderer.render(delta);

    }

    /**
     * Translates screen coordinates to game canvas coordinates
     * @param p Point on the screen
     * @return Point on the game canvas
     */
    public Point translateCoords(Point p){
        Vector3 vec3 = new Vector3(p.getX(), p.getY(), 0);
        Vector3 vec3Translated = viewport.unproject(vec3);
        return new Point(vec3Translated.x, vec3Translated.y);
    }

    /**
     * Translates screen width to canvas width
     * @param width Width
     * @return float Translated width
     */
    public float translateX(float width){
        Vector3 vec3 = new Vector3(width, 0, 0);
        Vector3 vec3Translated = viewport.unproject(vec3);
        return vec3Translated.x;
    }

    /**
     * Translates screen height to canvas height
     * @param height Height
     * @return float Translated height
     */
    public float translateY(float height){
        Vector3 vec3 = new Vector3(0, height, 0);
        Vector3 vec3Translated = viewport.unproject(vec3);
        return vec3Translated.y;
    }

    /**
     * Updates renderers when the window is resized
     * @param width New width
     * @param height New height
     */
    public void resize(int width, int height){
        viewport.update(width, height);
        backgroundRenderer.resize();
        buildingRenderer.resize();

    }

    /**
     * used to get BuildingRenderer from parent GameRenderer
     * @return BuildingRenderer
     */
    public BuildingRenderer getBuildingRenderer(){
        return buildingRenderer;
    }

    /**
     * used to get BackgroundRenderer from parent GameRenderer
     * @return BackgroundRenderer
     */
    public BackgroundRenderer getBackgroundRenderer() {return backgroundRenderer;}

    /**
     * disposes all renderers being drawn for garbage collection
     */
    public void dispose(){
        stage.dispose();
        backgroundRenderer.dispose();
        buildingRenderer.dispose();
    }

    /**
     * Get the scaling value from the FitViewport
     */
    public float getViewportScaling()
    {
        float heightScaling = viewport.getScreenHeight() / viewport.getWorldHeight();
        float widthScaling = viewport.getScreenWidth() / viewport.getWorldWidth();
        return Math.min(heightScaling, widthScaling);
    }

}
