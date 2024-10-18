package com.vikingz.unitycoon;

import com.badlogic.gdx.Game;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.screens.MenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {
        // If SKIP_MENUS is enabled in GameConfig, the game will load straight into the game.
        if(GameConfig.SKIP_MENUS){
            setScreen(new GameScreen(this, "Map1"));
        }
        else{
            setScreen(new MenuScreen(this));
        }
    }
}
