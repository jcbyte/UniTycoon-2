package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Game;
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
    private static GameSkins skinLoader;

    public static GameScreen gameScreen;
    public static MenuScreen menuScreen;
    public static SettingsScreen settingsScreen;
    public static MapSelectorScreen mapSelectorScreen;



    public ScreenMultiplexer(Game game, GameSkins skinLoader){

        this.game = game;
        this.skinLoader = skinLoader;

        menuScreen = new MenuScreen(game, skinLoader);
        settingsScreen = new SettingsScreen(game, skinLoader);
        mapSelectorScreen = new MapSelectorScreen(game, skinLoader);

        
    }

    public void runGame(String map){
        gameScreen = new GameScreen(game, map, skinLoader);
        gameScreen.takeInput();
        game.setScreen(gameScreen);
    }


    public void switchScreens(Screens screen){

        switch (screen) {
            case GAME:
                gameScreen.takeInput();
                game.setScreen(gameScreen);
                break;

            case SETTINGS:
                settingsScreen.takeInput();
                game.setScreen(settingsScreen);
                break;

            case MENU:
                menuScreen.takeInput();
                game.setScreen(menuScreen);
                break;

            case MAPSELECTION:
                mapSelectorScreen.takeInput();
                game.setScreen(mapSelectorScreen);
                break;
            
            default:
                break;
        }
    }


}
