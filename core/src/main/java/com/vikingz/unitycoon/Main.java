package com.vikingz.unitycoon;

import com.badlogic.gdx.Game;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.global.GameSkins;
import com.vikingz.unitycoon.screens.GameScreenOld;
import com.vikingz.unitycoon.screens.MenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {

        GameSkins skinLoader = new GameSkins();
        GameConfigManager.loadGameConfig();

        // If SKIP_MENUS is enabled in GameConfig, the game will load straight into the game.
        if(GameConfig.getInstance().isSkipMenus()){
            setScreen(new GameScreenOld(this, "map1",skinLoader));
        }
        else{
            setScreen(new MenuScreen(this,skinLoader));
        }
    }
}
