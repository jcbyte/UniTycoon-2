package com.vikingz.unitycoon.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vikingz.unitycoon.global.GameConfig;

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
        buildingRenderer = new BuildingRenderer();

        


    }

    public void render(float delta){

        viewport.apply();
        stage.getViewport().apply();

        camera.update();

        backgroundRenderer.render(delta);
        buildingRenderer.render(delta);

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
