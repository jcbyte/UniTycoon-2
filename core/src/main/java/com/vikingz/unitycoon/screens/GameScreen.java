package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.building.buildings.FoodBuilding;
import com.vikingz.unitycoon.building.buildings.RecreationalBuilding;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.global.GameSkins;
import com.vikingz.unitycoon.menus.PauseMenu;
import com.vikingz.unitycoon.menus.PopupMenu;
import com.vikingz.unitycoon.render.BackgroundRenderer;
import com.vikingz.unitycoon.render.BuildingRenderer;
import com.vikingz.unitycoon.render.StatsRenderer;
import com.vikingz.unitycoon.ui.BuildMenu;

public class GameScreen implements Screen {



    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private String mapName;
    private boolean isPaused;

    private Stage stage;
    private Skin skin;
    private PauseMenu pauseMenu;
    private PopupMenu endOfTimerPopup;
    private boolean continuingGameAfterTheEnd;

    // Counter variables
    private float elapsedTime;

    // Font to display counter
    private BitmapFont font;

    // Renderers
    private BackgroundRenderer backgroundRenderer;
    private StatsRenderer statsRenderer;
    private BuildingRenderer buildingRenderer;

    // Menus
    private BuildMenu buildMenu;





    public GameScreen(Game game, String mapName, GameSkins SkinLoader) {

        skin = SkinLoader.getQuantumSkin();

        this.game = game;
        this.mapName = mapName;
        this.isPaused = false;

        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        backgroundRenderer = new BackgroundRenderer(mapName);
        statsRenderer = new StatsRenderer();
        buildingRenderer = new BuildingRenderer();
        buildMenu = new BuildMenu(SkinLoader, buildingRenderer, stage);
        batch = new SpriteBatch();

        this.continuingGameAfterTheEnd = false;
        this.pauseMenu = new PauseMenu(skin);

        // Initialize counter and font
        elapsedTime = 0;
        GameGlobals.ELAPSED_TIME = 300;
        new Timer().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if(!isPaused){
                    GameGlobals.ELAPSED_TIME--;
                }
            }
        }, 0, 1);

        font = new BitmapFont(); // Create a new BitmapFont (consider loading a specific font if needed)
        font.getData().setScale(2.0f);

        System.out.println("Init Game Screen Complete");

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
        camera.update();


        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.pause();
        }




        if(!isPaused){

            elapsedTime += delta; // delta is the time elapsed since the last frame
            if (elapsedTime >= 1) { // Increment counter every second

                // Calculate Game Stats

                for (Building building : buildingRenderer.getPlaceBuildings()){
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
        if(continuingGameAfterTheEnd){
            endOfTimerPopup.remove();
        }


        //System.out.println((Gdx.input.getY()));
        //System.out.println((Gdx.input.getX()));

        // Draw game objects
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        backgroundRenderer.render(delta);
        statsRenderer.render(delta);
        buildingRenderer.render(delta);
        buildMenu.render(delta);
        batch.end();
        stage.act();
        stage.draw();
    }


    //TODO This needs to be fixed due to requirement of resizable screens
    @Override
    public void resize(int width, int height) {
        // Adjust the viewport when the window size changes

        buildMenu.resize(width, height);
        backgroundRenderer.resize(width, height);

        //camera.setToOrtho(false, width, height);

        //backgroundRenderer.resize(width, height);
    }

    @Override
    public void pause() {
        System.out.println("Pressed ESC");

        if(!pauseMenu.hasParent()){
            stage.addActor(pauseMenu);
            pauseMenu.setPosition((stage.getWidth() - pauseMenu.getWidth()) / 2, (stage.getHeight() - pauseMenu.getHeight()) / 2);
            isPaused = true;
        }
        else{
            pauseMenu.remove();
            isPaused = false;
        }

    }


    private void endGame(){
        
        
        isPaused = true;
        
        this.endOfTimerPopup = new PopupMenu(skin, "End of Game");

        Runnable leftBtn = new Runnable() {
            
            @Override
            public void run(){
                Gdx.app.exit();
            }

        };

        Runnable rightBtn = new Runnable() {
            
            @Override
            public void run(){
                // funny
                GameGlobals.ELAPSED_TIME = (int) Double.POSITIVE_INFINITY;
                isPaused = false;

                // TODO: I cant get the end of timer popup to go away even after .remove()ing it ?!?!
                continuingGameAfterTheEnd = true;
                boolean res = endOfTimerPopup.remove();
                System.out.println("result: " + res + "continue:" + continuingGameAfterTheEnd);
            }
            

        };

        //endOfTimerPopup.setupRightBtn(rightBtn, "Continue");
        endOfTimerPopup.setupButtons(leftBtn, "Quit", rightBtn, "Continue");
        
        endOfTimerPopup.setPosition((stage.getWidth() - endOfTimerPopup.getWidth()) / 2, (stage.getHeight() - endOfTimerPopup.getHeight()) / 2);
        stage.addActor(endOfTimerPopup);
    
    }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        // Dispose of resources
        batch.dispose();
        font.dispose(); // Dispose the font
    }



}
