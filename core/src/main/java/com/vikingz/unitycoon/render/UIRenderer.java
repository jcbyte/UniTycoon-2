package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.menus.BuildMenu;
import com.vikingz.unitycoon.menus.EndMenu;
import com.vikingz.unitycoon.menus.PauseMenu;
import com.vikingz.unitycoon.menus.PopupMenu;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.util.Achievement;
import com.vikingz.unitycoon.events.Event;

/**
 * This class renders all the UI elements to the Screen.
 * <p>
 * This enables us to control how the UI is draw and resized
 * differently from how the rest of the game is drawn.
 * <p>
 * This class essentially forms another layer on the screen that
 * renders all the UI elements on this layer as opposed to the
 * game layer.
 */
public class UIRenderer {

    private final Stage stage;
    private final Viewport viewport;

    private final BuildMenu buildMenu;
    private final StatsRenderer statsRenderer;
    private final AchievementsRenderer achievementsRenderer;

    // Popup Menus
    private final PauseMenu pauseMenu;
    private final EndMenu endOfTimerPopup;
    private final PopupMenu popupMenu; // For events

    GameScreen gameScreen;

    /**
     * Creates a new UIRenderer
     * @param skin Skin used to style content
     * @param buildingRenderer Building renderer
     * @param gameScreen Game screen
     */
    public UIRenderer(Skin skin, BuildingRenderer buildingRenderer, GameScreen gameScreen){
        this.gameScreen = gameScreen;

        //viewport = new FillViewport(1824, 1026);
        viewport = new FitViewport(1824, 1026);
        //viewport = new ScreenViewport();
        stage = new Stage(viewport);


        statsRenderer = new StatsRenderer(skin);
        achievementsRenderer = new AchievementsRenderer(gameScreen.getAchievementsManager(), skin);

        buildMenu = new BuildMenu(skin, buildingRenderer, stage);

        pauseMenu = new PauseMenu(skin, this);
        endOfTimerPopup = new EndMenu(skin, "End of Game");
        popupMenu = new PopupMenu(skin);

        Table pauseContainer = new Table();
        pauseContainer.setFillParent(true);
        pauseContainer.bottom().left();
        Texture pauseTex = new Texture(Gdx.files.internal("png/pause.png"));
        Image pauseButton = new Image(pauseTex);
        pauseButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pause();
            }
        });
        pauseContainer.add(pauseButton).size(90, 90).pad(10);
        stage.addActor(pauseContainer);
    }

    public void showEvent(Event event) {
        popupMenu.setPosition((stage.getWidth() - popupMenu.getWidth()) / 2, (stage.getHeight() - popupMenu.getHeight()) / 2);

        popupMenu.setMessage(event.message);
        if (event.choice) {
            popupMenu.setupButtons(event.opt1.action, event.opt1.text, event.opt1.disabled, event.opt2.action, event.opt2.text, event.opt2.disabled);
        } else {
            popupMenu.setupSingleButton(event.opt1.action, event.opt1.text);
        }
        stage.addActor(popupMenu);

        // Remove current selected building to place
        gameScreen.getGameRenderer().getBuildingRenderer().clearSelectedBuilding();
    }

    public void showAchievement(Achievement achievement)
    {
        popupMenu.setPosition((stage.getWidth() - popupMenu.getWidth()) / 2, (stage.getHeight() - popupMenu.getHeight()) / 2);

        popupMenu.setMessage("Achievement Unlocked\n\n" + achievement.name);
        popupMenu.setupSingleButton(achievement.reward, achievement.rewardText);
        stage.addActor(popupMenu);

        achievementsRenderer.update();

        // Remove current selected building to place
        gameScreen.getGameRenderer().getBuildingRenderer().clearSelectedBuilding();
    }

    public void showPopup(String text, String btnText, Runnable runnable)
    {
        popupMenu.setPosition((stage.getWidth() - popupMenu.getWidth()) / 2, (stage.getHeight() - popupMenu.getHeight()) / 2);

        popupMenu.setMessage(text);
        popupMenu.setupSingleButton(runnable, btnText);
        stage.addActor(popupMenu);

        // Remove current selected building to place
        gameScreen.getGameRenderer().getBuildingRenderer().clearSelectedBuilding();
    }

    public void showPopup(String text, String leftBtnText, Runnable leftRunnable, String rightBtnText, Runnable rightRunnable)
    {
        popupMenu.setPosition((stage.getWidth() - popupMenu.getWidth()) / 2, (stage.getHeight() - popupMenu.getHeight()) / 2);

        popupMenu.setMessage(text);
        popupMenu.setupButtons(leftRunnable, leftBtnText, false, rightRunnable, rightBtnText, false);
        stage.addActor(popupMenu);

        // Remove current selected building to place
        gameScreen.getGameRenderer().getBuildingRenderer().clearSelectedBuilding();
    }

    /**
     * When the game screen has decided the game has finished the game
     * will call this function which will show the end of game popup
     */
    public void endGame(){
        // Refresh the end menu showing the leaderboard section if the users score can be added
        endOfTimerPopup.refresh(GameConfig.getInstance().isOnLeaderboard(GameGlobals.SATISFACTION));

        endOfTimerPopup.setPosition((stage.getWidth() - endOfTimerPopup.getWidth()) / 2, (stage.getHeight() - endOfTimerPopup.getHeight()) / 2);
        stage.addActor(endOfTimerPopup);

        // Remove current selected building to place
        gameScreen.getGameRenderer().getBuildingRenderer().clearSelectedBuilding();
    }

    /**
     * Pauses the game displays the pause menu
     */
    public void pause() {
        System.out.println("Pressed ESC");

        if(!pauseMenu.hasParent()){
            stage.addActor(pauseMenu);
            pauseMenu.setPosition((stage.getWidth() - pauseMenu.getWidth()) / 2, (stage.getHeight() - pauseMenu.getHeight()) / 2);
            gameScreen.setPaused(true);

            // Remove current selected building to place
            gameScreen.getGameRenderer().getBuildingRenderer().clearSelectedBuilding();
        }
        else{
            pauseMenu.remove();
            gameScreen.setPaused(false);
        }

    }

    /**
     * Calls all render functions in the renderers
     */
    public void render(float delta){
        viewport.apply();
        stage.act(delta);
        stage.draw();
        statsRenderer.render(delta);
        achievementsRenderer.render(delta);
        buildMenu.render(delta);
    }


    /**
     * Resizes UI content when the window is resized
     * @param width New width
     * @param height New height
     */
    public void resize(int width, int height){
        viewport.update(width, height, true);
        buildMenu.resize(width, height);
        achievementsRenderer.resize(width, height);
    }

    /**
     * Sets the input process to this class when called
     */
    public void takeInput() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(achievementsRenderer.getInputProcessor());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * Disposes of content in this screen
     */
    public void dispose(){
        stage.dispose();
        achievementsRenderer.dispose();
    }

}
