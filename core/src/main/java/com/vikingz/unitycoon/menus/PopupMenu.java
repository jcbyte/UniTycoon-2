package com.vikingz.unitycoon.menus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameGlobals;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class PopupMenu extends Window {

    private String Message = "";

/*
 * TODO:
 * Make a constructor to create multiple different popups
 */

    public PopupMenu(Skin skin, String Message) {

        super("Popup", skin);
        this.setSize(600, 400);
        this.setModal(true);
        this.setMovable(false);
        this.setResizable(false);



        Label message = new Label(Message, skin);
        this.add(message).padBottom(20).row();

        // Idk change this later
        TextButton yesButton = new TextButton("Yes", skin);
        TextButton noButton = new TextButton("No", skin);

        this.add(yesButton).pad(10);
        this.add(noButton).pad(10);

        // Created for yes - no game events
        // The Popup needs to call back to parent object in someway

        yesButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Yes clicked");
                PopupMenu.this.remove();
            }
        });

        noButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("No clicked");
                PopupMenu.this.remove();
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
