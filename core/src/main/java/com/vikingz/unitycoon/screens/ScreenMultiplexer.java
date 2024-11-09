package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.vikingz.unitycoon.global.GameSkins;



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
        gameScreen = new GameScreen(map);
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
        menuScreen.dispose();
        menuScreen = new MenuScreen();
        switchScreens(Screens.MENU);
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
