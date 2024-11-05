package com.vikingz.unitycoon;

import com.badlogic.gdx.Game;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.global.GameSkins;
import com.vikingz.unitycoon.screens.ScreenMultiplexer;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {

        
        GameSkins skinLoader = new GameSkins();
        GameConfigManager.loadGameConfig();
        ScreenMultiplexer screenMux = new ScreenMultiplexer(this, skinLoader);

        //Uncomment for funny numbers
        //Gdx.graphics.setForegroundFPS(99999999);
        //Gdx.graphics.setVSync(false);


        // If SKIP_MENUS is enabled in GameConfig, the game will load straight into the game.
        if(GameConfig.getInstance().isSkipMenus()){
            screenMux.switchScreens(ScreenMultiplexer.Screens.MENU);
        }
        else{
            screenMux.switchScreens(ScreenMultiplexer.Screens.MENU);
        }
    }
}
