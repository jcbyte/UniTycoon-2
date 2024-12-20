package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.building.buildings.FoodBuilding;
import com.vikingz.unitycoon.building.buildings.RecreationalBuilding;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.GameRenderer;
import com.vikingz.unitycoon.render.UIRenderer;
import com.vikingz.unitycoon.achievement.AchievementsManager;
import com.vikingz.unitycoon.event.EventsManager;

/**
 * This is the main game class from which the game is run.
 * <p>
 * This game instantiates the 2 renderers which are the GameRenderer
 * and the UIRenderer, as well as contains the game loop that control how the game
 * runs.
 * <p>
 * The game loop contains a section where everything in that section is updated
 * every second which is where all of our game stats are updated.
 * <p>
 * Inherits Screen, SuperScreen
 */
public class GameScreen extends SuperScreen implements Screen {

    //Determines if the game had been loaded from fullScreen
    public boolean fullScreen;

    //determines if the game is paused
    private boolean isPaused;

    // Counter variables
    private float elapsedTime;

    // Renderers
    private final GameRenderer gameRenderer;
    private final UIRenderer uiRenderer;

    //Used to fix incorrect initial Renderer size
    public int startWidth;
    public int startHeight;

    //Determines if first tick of game has passed
    public boolean FirstTick;

    private final EventsManager eventsManager;
    private final AchievementsManager achievementsManager;


    /**
     * Creates a new Game Screen
     * @param mapName The name of the map that will be used
     */
    public GameScreen(String mapName){
        super();

        gameRenderer = new GameRenderer(this, mapName);
        achievementsManager = new AchievementsManager(this);
        uiRenderer = new UIRenderer(skin, gameRenderer.getBuildingRenderer(), this);
        eventsManager = new EventsManager(this);

        elapsedTime = 0;
        //5 minutes
        GameGlobals.resetGlobals(5 * 60);

        // Show instructions
        isPaused = true;
        uiRenderer.startGame();
    }


    @Override
    public void show() {
        // Initialize game objects here

    }

    /**
     * Contains the game loop, renders game all game content from this loop
     * @param delta Time since last frame
     */
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
                GameGlobals.ELAPSED_TIME --;

                // Calculate Game Stats

                for (Building building : gameRenderer.getBuildingRenderer().getPlaceBuildings()){
                    GameGlobals.SATISFACTION += building.calculateSatisfaction(GameGlobals.STUDENTS);

                    if(building.getBuildingType() == BuildingStats.BuildingType.FOOD){
                        FoodBuilding foodBuilding = (FoodBuilding) building;
                        GameGlobals.BALANCE += (int) foodBuilding.calculateProfitMade();
                    }

                    if(building.getBuildingType() == BuildingStats.BuildingType.RECREATIONAL){
                        RecreationalBuilding foodBuilding = (RecreationalBuilding) building;
                        GameGlobals.BALANCE += (int) foodBuilding.calculateProfitMade();
                    }

                }
                elapsedTime = 0; // Reset elapsed time
            }


        }

        eventsManager.render();
        achievementsManager.update();

        if(GameGlobals.ELAPSED_TIME <= 0){
            endGame();
        }


        // Draw
        batch.begin();
        gameRenderer.render(delta);
        uiRenderer.render(delta);
        batch.end();

        //resizes to previous starting resolution
        if (FirstTick){
            if (fullScreen){
                GameConfigManager.setFullScreen();
            }
            else {
                Gdx.graphics.setWindowedMode(startWidth, startHeight);
            }
            FirstTick = false;
        }
    }


    /**
     * Checks if window has been resized
     */
    @Override
    public void resize(int width, int height) {
        uiRenderer.resize(width, height);
        gameRenderer.resize(width, height);


    }

    /**
     * Pauses the game and calls the UI renderer to display the
     * pause menu UI
     */
    @Override
    public void pause() {
        uiRenderer.pause();

    }

    boolean endCalled = false;
    /**
     * This is called when the game finishes, ie when the timer runs out
     */
    private void endGame(){
        if (!endCalled) {
            isPaused = true;
            uiRenderer.endGame();
            endCalled = true;
        }

    }

    @Override
    public void resume() { }

    @Override
    public void hide() { }


    /**
     * disposes Renderers being drawn for garbage collection
     */
    @Override
    public void dispose() {
        batch.dispose();
        gameRenderer.dispose();
        uiRenderer.dispose();
    }

    /**
     * Sets ui Renderer to having input control
     */
    @Override
    public void takeInput() {
        uiRenderer.takeInput();
    }

    /**
     * Sets the game to be paused
     * @param isPaused boolean of if the game is paused
     */
    public void setPaused(boolean isPaused){
        this.isPaused = isPaused;
    }

    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }

    public UIRenderer getUIRenderer() {
        return uiRenderer;
    }

    public AchievementsManager getAchievementsManager() {
        return achievementsManager;
    }
}
