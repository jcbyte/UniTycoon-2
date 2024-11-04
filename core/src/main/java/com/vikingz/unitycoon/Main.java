package com.vikingz.unitycoon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.global.GameSkins;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.screens.MapSelectorScreen;
import com.vikingz.unitycoon.screens.MenuScreen;
import com.vikingz.unitycoon.screens.SettingsScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {

        GameSkins skinLoader = new GameSkins();
        GameConfigManager.loadGameConfig();

        //Uncomment for funny numbers
        //Gdx.graphics.setForegroundFPS(99999999);
        //Gdx.graphics.setVSync(false);


        GameGlobals.menuScreen = new MenuScreen(this, skinLoader);
        GameGlobals.gameScreen = new GameScreen(this, "map1", skinLoader);
        GameGlobals.settingsScreen = new SettingsScreen(this, skinLoader);
        GameGlobals.mapSelectorScreen = new MapSelectorScreen(this, skinLoader);



        // If SKIP_MENUS is enabled in GameConfig, the game will load straight into the game.
        if(GameConfig.getInstance().isSkipMenus()){
            GameGlobals.settingsScreen.takeInput();
            setScreen(GameGlobals.gameScreen);
        }
        else{
            setScreen(GameGlobals.menuScreen);
        }
    }
}
