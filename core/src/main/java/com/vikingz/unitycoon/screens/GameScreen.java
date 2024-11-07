package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.building.buildings.FoodBuilding;
import com.vikingz.unitycoon.building.buildings.RecreationalBuilding;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.GameRenderer;
import com.vikingz.unitycoon.render.UIRenderer;

public class GameScreen extends SuperScreen implements Screen {



    private boolean isPaused;



    // Counter variables
    private float elapsedTime;

    // Font to display counter
    private BitmapFont font;

    // Renderers
    GameRenderer gameRenderer;
    UIRenderer uiRenderer;




    public GameScreen(String mapName){
        super();

        this.isPaused = false;

        
        gameRenderer = new GameRenderer(mapName);
        uiRenderer = new UIRenderer(skin, gameRenderer.getBuildingRenderer(), this);
        

        font = new BitmapFont(); 
        font.getData().setScale(2.0f);


        elapsedTime = 0;
        GameGlobals.ELAPSED_TIME = 300000000;
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


        //System.out.println((Gdx.input.getY()));
        //System.out.println((Gdx.input.getX()));

        // Draw game objects

        batch.begin();
        gameRenderer.render(delta);
        uiRenderer.render(delta);
        batch.end();
    }


    @Override
    public void resize(int width, int height) {
        // Adjust the viewport when the window size changes
        System.out.println("Resized");
        uiRenderer.resize(width, height);
        gameRenderer.resize(width, height);

    }

    @Override
    public void pause() {

        uiRenderer.pause(isPaused);

    }


    private void endGame(){
        
        isPaused = true;
        uiRenderer.endGame();

    }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {

        batch.dispose();
        font.dispose(); // Dispose the font
    }

    @Override
    public void takeInput() {
        uiRenderer.takeInput();
    }

    public void setPaused(boolean isPaused){
        this.isPaused = isPaused;
    }


}
