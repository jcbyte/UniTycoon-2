package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Timer;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.building.buildings.FoodBuilding;
import com.vikingz.unitycoon.building.buildings.RecreationalBuilding;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.GameRenderer;
import com.vikingz.unitycoon.render.StatsRenderer;
import com.vikingz.unitycoon.render.UIRenderer;

public class GameScreen extends SuperScreen implements Screen {

    private boolean isPaused;

    // Counter variables
    private float elapsedTime;

    // Renderers
    GameRenderer gameRenderer;
    UIRenderer uiRenderer;




    public GameScreen(String mapName){
        super();

        this.isPaused = false;
        gameRenderer = new GameRenderer(mapName);
        uiRenderer = new UIRenderer(skin, gameRenderer.getBuildingRenderer(), this);
        elapsedTime = 0;
        //5 minutes
        GameGlobals.resetGlobals(300);
        new Timer().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if(!isPaused){
                    GameGlobals.ELAPSED_TIME--;
                }
            }
        }, 0, 1);
    }


    @Override
    public void show() {
        // Initialize game objects here
    }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            pause();
        }

        if(!isPaused){

            elapsedTime += delta; // delta is the time elapsed since the last frame
            if (elapsedTime >= 1) { // Increment counter every second

                // Calculate Game Stats

                for (Building building : gameRenderer.getBuildingRenderer().getPlaceBuildings()){
                    GameGlobals.SATISFACTION += building.calculateSatisfaction(GameGlobals.STUDENTS);

                    if(building.getBuildingType() == BuildingStats.BuildingType.FOOD){
                        FoodBuilding foodBuilding = (FoodBuilding) building;
                        GameGlobals.BALANCE += foodBuilding.calcuateProfitMade();
                    }

                    if(building.getBuildingType() == BuildingStats.BuildingType.RECREATIONAL){
                        RecreationalBuilding foodBuilding = (RecreationalBuilding) building;
                        GameGlobals.BALANCE += foodBuilding.calcuateProfitMade();
                    }

                }
                elapsedTime = 0; // Reset elapsed time
            }


        }

        if(GameGlobals.ELAPSED_TIME <= 0){
            endGame();
        }

        // Draw
        batch.begin();
        gameRenderer.render(delta);
        uiRenderer.render(delta);
        batch.end();
    }


    @Override
    public void resize(int width, int height) {
        uiRenderer.resize(width, height);
        gameRenderer.resize(width, height);

    }

    @Override
    public void pause() {
        uiRenderer.pause(isPaused);

    }

    private void endGame(){
        isPaused = true;
        if (GameConfig.getInstance().getTopSatisfaction() < GameGlobals.SATISFACTION){
            uiRenderer.endGame(true);
        }
        uiRenderer.endGame(false);

    }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        batch.dispose();
        gameRenderer.dispose();
        uiRenderer.dispose();
    }

    @Override
    public void takeInput() {
        uiRenderer.takeInput();
    }

    public void setPaused(boolean isPaused){
        this.isPaused = isPaused;
    }


}
