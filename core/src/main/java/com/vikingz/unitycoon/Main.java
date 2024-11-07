package com.vikingz.unitycoon;

import com.badlogic.gdx.Game;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.screens.ScreenMultiplexer;
import com.vikingz.unitycoon.util.GameMusic;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {

        GameMusic music = new GameMusic();
        music.play();

        GameConfigManager.loadGameConfig();
        ScreenMultiplexer.init(this);



        //Uncomment for funny numbers
        //Gdx.graphics.setForegroundFPS(99999999);
        //Gdx.graphics.setVSync(false);


        // If SKIP_MENUS is enabled in GameConfig, the game will load straight into the game.
        if(GameConfig.getInstance().isSkipMenus()){
            ScreenMultiplexer.switchScreens(ScreenMultiplexer.Screens.MENU);
        }
        else{
            ScreenMultiplexer.switchScreens(ScreenMultiplexer.Screens.MENU);
        }
    }
}
