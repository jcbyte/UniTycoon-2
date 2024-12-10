package com.vikingz.unitycoon.menus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.vikingz.unitycoon.global.GameGlobals;

/**
 * This is a generic PopupMenu class that can create user defined
 * popups. This class is crucial for the implementation of random events
 * in the game during later development.
 *
 * The user can assign everything in the popup, all of the text displayed
 * as well as the runnable that run when the 2 buttons are pressed.
 */
public class PopupMenu extends Window {

    private final Label message;
    private final TextButton leftBtn;
    private final TextButton rightBtn;

    // Skin for the popup
    private final Skin skin;

    /**
     * Creates a new Popup menu
     * @param skin Skin for the menu
     */
    public PopupMenu(Skin skin) {
        super("", skin);

        this.setSize(800, 400);
        this.setModal(true);
        this.setMovable(false);
        this.setResizable(false);

        this.skin = skin;
        this.setBackground(GameGlobals.backGroundDrawable);

        message = new Label("", skin);
        message.setFontScale(1.3f);
        this.add(message).padBottom(20).row();

        leftBtn = new TextButton("", skin);
        rightBtn = new TextButton("", skin);

        this.add(leftBtn).pad(10);
        this.add(rightBtn).pad(10);
    }

    /**
     * Configures the 2 buttons that appear on the popup
     * @param leftRun Runnable that will be run if the left button is pressed
     * @param leftText The text written on the left button
     * @param rightRun Runnable that will be run if the right button is pressed
     * @param rightText The text written on the right button
     */
    public void setupButtons(Runnable leftRun, String leftText, Runnable rightRun, String rightText) {
        leftBtn.setText(leftText);
        leftBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                leftRun.run();
                PopupMenu.this.remove();
            }
        });

        rightBtn.setText(rightText);
        rightBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rightRun.run();
                PopupMenu.this.remove();
            }
        });
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }
}
