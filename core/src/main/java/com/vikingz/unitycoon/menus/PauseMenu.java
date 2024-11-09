package com.vikingz.unitycoon.menus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.ScreenMultiplexer;

/**
 *  This class represents a PauseMenu in the game.
 * 
 * This is the menu that appears if the user pressed the esc button
 * during the game.
 * 
 * This menu contains a settings button which sends the user to the settings
 * screen from which they can edit the settings.
 * 
 * To close the pause menu, the user has to press the esc button again.
 */
public class PauseMenu extends Window {


    /**
     * Creates a new pause menu
     * This menu is shown when the user pauses the game / presses
     *  the esc button during the game. 
     * @param skin
     */
    public PauseMenu(Skin skin) {

        super("", skin);

        this.setSize(800, 400);
        this.setModal(true);
        this.setMovable(false);
        this.setResizable(false);

        Label message = new Label("Game Paused\n(Click esc to un-pause) ", skin);
        this.add(message).padBottom(20).row();
        this.setBackground(GameGlobals.backGroundDrawable);


        TextButton settingsBtn = new TextButton("Settings", skin);

        TextButton quitBtn = new TextButton("Quit", skin);
        this.add(settingsBtn).pad(10);
        this.add(quitBtn).pad(10);

        // Created for yes - no game events
        // The Popup needs to call back to parent object in someway

        settingsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ScreenMultiplexer.openSettings(ScreenMultiplexer.Screens.GAME);

                //PopupMenu.this.remove();
            }
        });

        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenMultiplexer.closeGame();
            }
        });
    }
}
