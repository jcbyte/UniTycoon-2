package com.vikingz.unitycoon.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.util.Point;

public class GameRenderer {
    
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

    public void resize(int width, int height){
        viewport.update(width, height);

        backgroundRenderer.resize(width, height);
        buildingRenderer.resize(width, height);

    }

    public BuildingRenderer getBuildingRenderer(){
        return buildingRenderer;
    }



}
