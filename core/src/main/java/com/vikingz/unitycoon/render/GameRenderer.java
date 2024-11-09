package com.vikingz.unitycoon.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.util.Point;

/**
 * This class contains all of the renderers that render the game. 
 * 
 * This class contains the renderers that draw the background as well
 * as the buildings. Using this class enables us to have a sepereate viewport 
 * that controls how the game is rendered as well as what happens when the 
 * game window is resized, as we wanted the map and the buildings to resize 
 * differently from the UI, which is what the {@code UIRenderer} is used for.
 */
public class GameRenderer {

    // Viewport stuff
    private Stage stage;
    private Camera camera;
    private Viewport viewport;

    private BackgroundRenderer backgroundRenderer;
    private BuildingRenderer buildingRenderer;


    public GameRenderer(String mapName){
        camera = new OrthographicCamera();
        viewport = new FillViewport(GameConfig.getInstance().getWindowWidth(), GameConfig.getInstance().getWindowHeight());
        //viewport = new FitViewport(GameConfig.getInstance().getWindowWidth(), GameConfig.getInstance().getWindowHeight(), camera);
        //viewport = new ScreenViewport();

        stage = new Stage(viewport);
        backgroundRenderer = new BackgroundRenderer(mapName);
        buildingRenderer = new BuildingRenderer(this);

    }

    public void render(float delta){

        viewport.apply();
        stage.getViewport().apply();
        camera.update();
        backgroundRenderer.render(delta);
        buildingRenderer.render(delta);

    }

    public Point translateCoords(Point p){
        Vector3 vec3 = new Vector3(p.getX(), p.getY(), 0);
        Vector3 vec3Translated = viewport.unproject(vec3);
        return new Point(vec3Translated.x, vec3Translated.y);
    }

    public float translateX(float x){
        Vector3 vec3 = new Vector3(x, 0, 0);
        Vector3 vec3Translated = viewport.unproject(vec3);
        return vec3Translated.x;
    }

    public float translateY(float y){
        Vector3 vec3 = new Vector3(0, y, 0);
        Vector3 vec3Translated = viewport.unproject(vec3);
        return vec3Translated.y;
    }

    public void resize(int width, int height){
        viewport.update(width, height);
        backgroundRenderer.resize(width, height);
        buildingRenderer.resize(width, height);

    }

    public BuildingRenderer getBuildingRenderer(){
        return buildingRenderer;
    }


    public void dispose(){
        stage.dispose();
        backgroundRenderer.dispose();
        buildingRenderer.dispose();
    }

}
