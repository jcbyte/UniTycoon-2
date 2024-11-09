package com.vikingz.unitycoon.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.vikingz.unitycoon.global.GameGlobals;

/**
 * This class represents the screen where the user chooses the
 * map they want to play.
 * 
 * This class contains a bunch of buttons that allow the user to
 * cycle through the different maps available as well as go back 
 * to the main menu or play game.
 * 
 * Inherits Screen, SuperScreen
 * 
 */
public class MapSelectorScreen extends SuperScreen implements Screen {

    private TextField mapText;
    private TextureRegionDrawable[] mapArray;

    // Map images (for example purposes, add your own map textures)

    private int mapSelection = 1;

    public MapSelectorScreen() {
        super();

        //Loads maps from global config
        mapArray = GameGlobals.mapArray;

        TextButton goBack = new TextButton("Go Back",skin);
        TextButton startGame = new TextButton("Start Game",skin);
        TextButton nextMap = new TextButton("->",skin);
        TextButton previousMap = new TextButton("<-",skin);

        //Gp back Button
        goBack.addListener(e -> {
            if (!goBack.isPressed()) return false;
            ScreenMultiplexer.switchScreens(ScreenMultiplexer.Screens.MENU);
            return true;
        });


        Image mapImage = new Image(GameGlobals.map1Texture);

        //Selects the next map
        nextMap.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapSelection++;
                if (mapSelection > 3) {
                    mapSelection = 1;
                }
                mapImage.setDrawable(mapArray[mapSelection-1]);
                return true;
            }
        });


        //Selects the previous map
        previousMap.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapSelection--;
                if (mapSelection < 1){
                    mapSelection = mapArray.length;
                }
                mapImage.setDrawable(mapArray[mapSelection-1]);
                return true;
            }
        });


        mapText = new TextField("map".concat(Integer.toString(mapSelection)),skin);

        startGame.addListener(e -> {
            if (!startGame.isPressed()) return false;

            ScreenMultiplexer.runGame(mapText.getText().toLowerCase());

            return true;
        });

        // Create table for layout
        Table table = new Table();
        table.setFillParent(true);
        table.center();


        table.add((Actor) null);
        table.add(mapImage).width(300).height(350).pad(10);

        table.row();

        table.add(previousMap).pad(10);
        table.add(mapText).pad(10);
        table.add(nextMap).pad(10);
        table.row();
        table.row();

        table.add(goBack).pad(10);
        table.add((Actor) null);
        table.add(startGame).pad(10);


        // Add the table to the stage
        stage.addActor(table);
    }

    @Override
    public void show() {
        // This method is called when the screen is shown
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(25/255f, 25/255f, 25/255f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        mapText.setText("Map".concat(Integer.toString(mapSelection)));



        // Draw the stage (the UI components)
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        // Dispose of resources
        stage.dispose();
        skin.dispose();
    }
}
