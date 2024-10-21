package com.vikingz.unitycoon.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.vikingz.unitycoon.global.GameSkins;
import com.vikingz.unitycoon.screens.GameScreen;
import com.vikingz.unitycoon.ui.BuildMenu;
import com.vikingz.unitycoon.util.BackgroundRenderer;

public class MapSelectorScreen implements Screen {

    private Game game;
    private Stage stage;
    private Skin skin;

    // Map images (for example purposes, add your own map textures)
    private Texture map1Texture;
    private Texture map2Texture;
    private Texture map3Texture;

    public MapSelectorScreen(Game game, GameSkins skinLoader) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = skinLoader.getDefaultSkin();

        // Load map textures (replace with your own textures)
        map1Texture = new Texture(Gdx.files.internal("png/map1Texture.png"));
        map2Texture = new Texture(Gdx.files.internal("png/map2Texture.png"));
        map3Texture = new Texture(Gdx.files.internal("png/map3Texture.png"));

        // Create buttons for selecting the maps
        TextButton map1Button = new TextButton("Map 1", skin);
        TextButton map2Button = new TextButton("Map 2", skin);
        TextButton map3Button = new TextButton("Map 3", skin);

        // Add listeners for buttons
        map1Button.addListener(e -> {
            if (!map1Button.isPressed()) return false;
            game.setScreen(new GameScreen(game, "map1",skinLoader)); // Start GameScreen for Map 1
            return true;
        });

        map2Button.addListener(e -> {
            if (!map2Button.isPressed()) return false;
            game.setScreen(new GameScreen(game, "map2",skinLoader)); // Start GameScreen for Map 2
            return true;
        });

        map3Button.addListener(e -> {
            if (!map3Button.isPressed()) return false;
            game.setScreen(new GameScreen(game, "map3",skinLoader)); // Start GameScreen for Map 3
            return true;
        });

        // Create table for layout
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        // Add the map buttons and images to the table
        table.add(map1Button).pad(10);
        table.add(map2Button).pad(10);
        table.add(map3Button).pad(10);
        table.row();

        table.add(new Image(map1Texture)).width(150).height(350).pad(10);
        table.add(new Image(map2Texture)).width(150).height(350).pad(10);
        table.add(new Image(map3Texture)).width(150).height(350).pad(10);

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
        map1Texture.dispose();
        map2Texture.dispose();
        map3Texture.dispose();
    }
}
