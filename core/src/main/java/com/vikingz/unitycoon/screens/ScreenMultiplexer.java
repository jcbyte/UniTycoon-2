package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameGlobals;


/**
 * This class allows us to change screens anywhere within the project.
 *
 * On init(Game newGame) this class instanciates all of the classes so that
 * they are ready in memory and waiting to be switched to.
 *
 * This class also contains the enum Screens which is what is used
 * to differenciate between the diferent screens available so that when the
 * user wants to change screen all they have to do is call
 * ScreenMultiplexer.switchScreens(Screens screen), which is much easier than
 * having to keep track at which point each screen is instanciated.
 *
 * This class also enables us to access the settings screen from any screen in the game
 * as when the settings screen is displayed, this class keeps track from which screen
 * it came from so that the back button on the settings screen will always send the
 * user back to the screen they just came from.
 */
public class ScreenMultiplexer {

    public enum Screens{
        GAME,
        MENU,
        SETTINGS,
        MAPSELECTION,
    }

    private static Game game;
    public static GameScreen gameScreen;
    public static MenuScreen menuScreen;
    public static SettingsScreen settingsScreen;
    public static MapSelectorScreen mapSelectorScreen;


    public static void init(Game newGame){

        game = newGame;

        menuScreen = new MenuScreen();
        settingsScreen = new SettingsScreen();
        mapSelectorScreen = new MapSelectorScreen();


    }

    public static void runGame(String map){
        //This is a cursed way of fixing the fullScreen on startup Bug
        int startHeight = Gdx.graphics.getHeight();
        int startWidth = Gdx.graphics.getWidth();
        boolean fullScreen = Gdx.graphics.isFullscreen();
        boolean firstTickNeed = false;
        if (Gdx.graphics.getHeight() != GameConfig.getInstance().getWindowHeight() && Gdx.graphics.getHeight() != GameConfig.getInstance().getWindowHeight()) {
            Gdx.graphics.setWindowedMode(GameConfig.getInstance().getWindowWidth(), GameConfig.getInstance().getWindowHeight());
            firstTickNeed = true;
        }
        gameScreen = new GameScreen(map);
        gameScreen.startHeight = startHeight;
        gameScreen.startWidth = startWidth;
        gameScreen.fullScreen = fullScreen;
        gameScreen.FirstTick = firstTickNeed;
        gameScreen.takeInput();
        game.setScreen(gameScreen);
        settingsScreen.setGameScreen(gameScreen);
    }

    public static void openSettings(Screens prevScreen){
        settingsScreen.setPrevScreen(prevScreen);
        game.setScreen(settingsScreen);
        settingsScreen.takeInput();
    }

    public static void closeGame(){
        gameScreen.dispose();
        openMenu();
    }

    public static void openMenu(){
        menuScreen.dispose();
        menuScreen = new MenuScreen();
        game.setScreen(menuScreen);
    }

    public static void switchScreens(Screens screen){

        switch (screen) {
            case GAME:
                game.setScreen(gameScreen);
                gameScreen.takeInput();
                break;

            case SETTINGS:
                game.setScreen(settingsScreen);
                settingsScreen.takeInput();
                break;

            case MENU:
                game.setScreen(menuScreen);
                menuScreen.takeInput();
                break;

            case MAPSELECTION:
                game.setScreen(mapSelectorScreen);
                mapSelectorScreen.takeInput();
                break;

            default:
                break;
        }
    }


}
