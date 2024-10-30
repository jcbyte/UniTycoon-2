package com.vikingz.unitycoon.menus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameGlobals;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class PauseMenu extends Window {

/* 
 * TODO:
 * Make a constructor to create multiple different popups
 */

    public PauseMenu(Skin skin) {



        super("Pause Menu", skin);
        
        this.setSize(600, 400);
        this.setModal(true);
        this.setMovable(false);
        this.setResizable(false);
    


        Label message = new Label("Game Paused\n(Click esc to un-pause) ", skin);
        this.add(message).padBottom(20).row();
        

        

    }
}
