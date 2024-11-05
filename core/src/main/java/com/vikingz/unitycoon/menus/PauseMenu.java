package com.vikingz.unitycoon.menus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vikingz.unitycoon.screens.ScreenMultiplexer;

public class PauseMenu extends Window {


    public PauseMenu(Skin skin) {



        super("Pause Menu", skin);

        this.setSize(600, 400);
        this.setModal(true);
        this.setMovable(false);
        this.setResizable(false);

        Label message = new Label("Game Paused\n(Click esc to un-pause) ", skin);
        this.add(message).padBottom(20).row();



        TextButton leftBtn = new TextButton("Settings", skin);

        this.add(leftBtn).pad(10);

        // Created for yes - no game events
        // The Popup needs to call back to parent object in someway

        leftBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
                ScreenMultiplexer.openSettings(ScreenMultiplexer.Screens.GAME);

                //PopupMenu.this.remove();
            }
        });


    }
}
