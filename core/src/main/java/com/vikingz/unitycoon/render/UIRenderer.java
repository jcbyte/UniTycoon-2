package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.menus.BuildMenu;
import com.vikingz.unitycoon.menus.EndMenu;
import com.vikingz.unitycoon.menus.PauseMenu;
import com.vikingz.unitycoon.menus.PopupMenu;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.screens.ScreenMultiplexer;

public class UIRenderer {

    private Stage stage;
    private Camera camera;
    private Viewport viewport;

    private Skin skin;

    private BuildMenu buildMenu;
    private StatsRenderer statsRenderer;

    // Popup Menus
    private PauseMenu pauseMenu;
    private EndMenu endOfTimerPopup;

    private boolean newHighScore = true;
    GameScreen gameScreen;

    public UIRenderer(Skin skin, BuildingRenderer buildingRenderer, GameScreen gameScreen){

        this.skin = skin;
        this.gameScreen = gameScreen;

        camera = new OrthographicCamera();
        //viewport = new FillViewport(1824, 1026);
        viewport = new FitViewport(1824, 1026);
        //viewport = new ScreenViewport();
        stage = new Stage(viewport);


        statsRenderer = new StatsRenderer(skin);
        buildMenu = new BuildMenu(skin, buildingRenderer, stage);

        pauseMenu = new PauseMenu(skin);
        endOfTimerPopup = new EndMenu(skin, "End of Game");

        Runnable leftBtn = new Runnable() {
            @Override
            public void run(){
                ScreenMultiplexer.closeGame();
            }

        };
        Runnable rightBtn = new Runnable() {
            @Override
            public void run(){
                // funny
                GameGlobals.ELAPSED_TIME = (int) Double.POSITIVE_INFINITY;
                gameScreen.setPaused(false);
                endOfTimerPopup.remove();
            }
        };

        endOfTimerPopup.setupButtons(leftBtn, "Quit", rightBtn, "Continue");

    }

    public void endGame(boolean newScore){

        endOfTimerPopup.setPosition((stage.getWidth() - endOfTimerPopup.getWidth()) / 2, (stage.getHeight() - endOfTimerPopup.getHeight()) / 2);
        if (newScore && newHighScore){
            endOfTimerPopup.addNewHighScore();
            GameConfig.getInstance().setTopSatisfaction(GameGlobals.SATISFACTION);
            GameConfigManager.saveGameConfig();
            newHighScore = false;
        }
        stage.addActor(endOfTimerPopup);

    }

    public void pause(boolean isPaused) {
        System.out.println("Pressed ESC");

        if(!pauseMenu.hasParent()){
            stage.addActor(pauseMenu);
            pauseMenu.setPosition((stage.getWidth() - pauseMenu.getWidth()) / 2, (stage.getHeight() - pauseMenu.getHeight()) / 2);
            gameScreen.setPaused(true);
        }
        else{
            pauseMenu.remove();
            gameScreen.setPaused(false);
        }

    }

    public void render(float delta){
        viewport.apply();
        statsRenderer.render(delta);
        buildMenu.render(delta);

    }


    public void resize(int width, int height){
        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
        buildMenu.resize(width, height);
        statsRenderer.resize(width, height);

    }


    public void takeInput(){
        Gdx.input.setInputProcessor(stage);

    }

    public void dispose(){
        stage.dispose();
    }

}
