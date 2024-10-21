package com.vikingz.unitycoon.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.global.GameSkins;

public class MapSelectorScreen implements Screen {

    private TextField mapText;
    private TextureRegionDrawable[] mapArray;
    private Game game;
    private Stage stage;
    private Skin skin;

    // Map images (for example purposes, add your own map textures)

    private GameSkins skinLoader;
    private int mapSelection = 1;

    public MapSelectorScreen(Game game, GameSkins skinLoader) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.skinLoader = skinLoader;

        skin = skinLoader.getQuantumSkin();

        //Loads maps from global config

        mapArray = GameGlobals.mapArray;



        // Create buttons for selecting the maps
        TextButton map1Button = new TextButton("Map 1", skin);
        TextButton map2Button = new TextButton("Map 2", skin);
        TextButton map3Button = new TextButton("Map 3", skin);


        // Add listeners for buttons
        map1Button.addListener(e -> {
            if (!map1Button.isPressed()) return false;
            game.setScreen(new GameScreenOld(game, "map1",skinLoader)); // Start GameScreen for Map 1
            return true;
        });

        map2Button.addListener(e -> {
            if (!map2Button.isPressed()) return false;
            game.setScreen(new GameScreenOld(game, "map2",skinLoader)); // Start GameScreen for Map 2
            return true;
        });

        map3Button.addListener(e -> {
            if (!map3Button.isPressed()) return false;
            game.setScreen(new GameScreenOld(game, "map3",skinLoader)); // Start GameScreen for Map 3
            return true;
        });

        TextButton goBack = new TextButton("Go Back",skin);
        TextButton startGame = new TextButton("Start Game",skin);
        TextButton nextMap = new TextButton("+",skin);
        TextButton previousMap = new TextButton("-",skin);

        //Gp back Button
        goBack.addListener(e -> {
            if (!goBack.isPressed()) return false;
            game.setScreen(new MenuScreen(game, skinLoader));
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
            System.out.println("Starting... ".concat(mapText.getText()));


            game.setScreen(new GameScreen(game, mapText.getText().toLowerCase(),skinLoader));

            //game.setScreen(new GameScreen(game, "map1",skinLoader));
            return true;
        });

        // Create table for layout
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        // Add the map buttons and images to the table
        //table.add(map1Button).pad(10);
        //table.add(map2Button).pad(10);
        //table.add(map3Button).pad(10);
        //table.row();

        table.add((Actor) null);
        table.add(mapImage).width(300).height(350).pad(10);
        //table.add(new Image(map2Texture)).width(150).height(350).pad(10);
        //table.add(new Image(map3Texture)).width(150).height(350).pad(10);
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        mapText.setText("Map".concat(Integer.toString(mapSelection)));


        //Key bindings Escape
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MenuScreen(game,skinLoader)); // Navigate back to MenuScreen
        }


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
