package com.vikingz.unitycoon.menus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vikingz.unitycoon.global.GameGlobals;

/**
 * This is a generic PopupMenu class that can create user defined
 * popups. This class is crucial for the implementation of random events 
 * in the game during later development.
 * 
 * The user can assign everythign in the popup, all of the text displayed
 * as well as the runnables that run when the 2 buttons are pressed.
 */
public class PopupMenu extends Window {

    private String Message = "";



    private Skin skin;

    private Runnable leftRun;
    private Runnable rightRun;


    public PopupMenu(Skin skin, String Message) {

        super("Popup", skin);

        this.setSize(600, 400);
        this.setModal(true);
        this.setMovable(false);
        this.setResizable(false);

        this.skin = skin;
        this.setBackground(GameGlobals.backGroundDrawable);


        Label message = new Label(Message, skin);
        this.add(message).padBottom(20).row();

    }

    public PopupMenu(Skin skin, String Message, Runnable leftRun, String leftText, Runnable rightRun, String rightText) {

        super("Popup", skin);
        this.setSize(600, 400);
        this.setModal(true);
        this.setMovable(false);
        this.setResizable(false);

        this.skin = skin;

        this.leftRun = leftRun;
        this.rightRun = rightRun;


        Label message = new Label(Message, skin);
        this.add(message).padBottom(20).row();

        setupButtons(leftRun, leftText, rightRun, rightText);


    }


    public void setupButtons(Runnable leftRun, String leftText, Runnable rightRun, String rightText){

        // Idk change this later
        TextButton leftBtn = new TextButton(leftText, skin);
        TextButton rightBtn = new TextButton(rightText, skin);

        this.add(leftBtn).pad(10);
        this.add(rightBtn).pad(10);

        // Created for yes - no game events
        // The Popup needs to call back to parent object in someway

        leftBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                leftRun.run();
                //PopupMenu.this.remove();
            }
        });

        rightBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rightRun.run();
                //PopupMenu.this.remove();
            }
        });
    }


    // Keeps left button as close
    public void setupRightBtn(Runnable rightRun, String rightText){


        // Idk change this later
        TextButton leftBtn = new TextButton("Close", skin);
        TextButton rightBtn = new TextButton(rightText, skin);

        this.add(leftBtn).pad(10);
        this.add(rightBtn).pad(10);

        // Created for yes - no game events
        // The Popup needs to call back to parent object in someway

        leftBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PopupMenu.this.remove();
            }
        });

        rightBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rightRun.run();
            }
        });

    }

    //Getters and Setters
    public String getMessage() {
        return Message;
    }
    public void setMessage(String message) {
        Message = message;
    }



}
