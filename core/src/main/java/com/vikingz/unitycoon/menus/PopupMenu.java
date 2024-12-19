package com.vikingz.unitycoon.menus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.vikingz.unitycoon.global.GameGlobals;

/**
 * This is a generic PopupMenu class that can create user defined
 * popups. This class is crucial for the implementation of random events
 * in the game during later development.
 * <p>
 * The user can assign everything in the popup, all of the text displayed
 * as well as the runnable that run when the 2 buttons are pressed.
 */
public class PopupMenu extends Window {

    private final Label message;
    private final TextButton leftBtn;
    private final TextButton rightBtn;

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

        this.setBackground(GameGlobals.backGroundDrawable);

        message = new Label("", skin);
        message.setFontScale(1.3f);
        this.add(message).align(Align.center).pad(50).row();

        leftBtn = new TextButton("", skin);
        leftBtn.getLabel().setFontScale(0.5f);
        rightBtn = new TextButton("", skin);
        rightBtn.getLabel().setFontScale(0.5f);

        Table buttonTable = new Table();
        buttonTable.add(leftBtn).expandX().fillX().pad(10);
        buttonTable.add(rightBtn).expandX().fillX().pad(10);
        this.add(buttonTable);
    }

    /**
     * Configures the 2 buttons that appear on the popup
     * @param leftRun Runnable that will be run if the left button is pressed
     * @param leftText The text written on the left button
     * @param leftDisabled if the left button is disabled
     * @param rightRun Runnable that will be run if the right button is pressed
     * @param rightText The text written on the right button
     * @param rightDisabled if the right button is disabled
     */
    public void setupButtons(Runnable leftRun, String leftText, boolean leftDisabled, Runnable rightRun, String rightText, boolean rightDisabled) {
        if (leftDisabled && rightDisabled)
        {
            throw new RuntimeException("Both buttons cannot be disabled");
        }

        leftBtn.setText(leftText);
        leftBtn.clearListeners();
        if (!leftDisabled) {
            leftBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    leftRun.run();
                    PopupMenu.this.remove();
                }
            });
            leftBtn.setColor(Color.WHITE);
        }
        else
        {
            leftBtn.setColor(Color.GRAY);
        }

        rightBtn.setText(rightText);
        rightBtn.clearListeners();
        if (!rightDisabled) {
            rightBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    rightRun.run();
                    PopupMenu.this.remove();
                }
            });
            rightBtn.setColor(Color.WHITE);
        }
        else
        {
            rightBtn.setColor(Color.GRAY);
        }

        rightBtn.setVisible(true);
    }

    /**
     * Configures 1 button that appears on the popup
     * @param run Runnable that will be run when the button is pressed
     * @param text The text written on the button
     */
    public void setupSingleButton(Runnable run, String text)
    {
        leftBtn.setText(text);
        leftBtn.clearListeners();
        leftBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                run.run();
                PopupMenu.this.remove();
            }
        });
        leftBtn.setColor(Color.WHITE);

        rightBtn.setVisible(false);
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }

    /**
     * Disable the popup temporarily, so the user does not immediately click on a button
     * @param ms time before re-enabling in milliseconds
     */
    public void enableAfter(int ms) {
        setTouchable(Touchable.disabled);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                System.out.println("enabled");
                setTouchable(Touchable.enabled);

            }
        }, (float)ms / 1000f);
    }
}
