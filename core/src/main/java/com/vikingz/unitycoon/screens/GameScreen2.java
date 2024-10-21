package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.vikingz.unitycoon.global.GameSkins;
import com.vikingz.unitycoon.ui.BuildMenu;
import com.vikingz.unitycoon.util.StatsRenderer;

public class GameScreen2 implements Screen {
    private final BitmapFont font;
    Texture img;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;


    // Menus
    private BuildMenu buildMenu;
    private StatsRenderer statsRenderer;

    public GameScreen2(Game game, String mapName, GameSkins SkinLoader){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        tiledMap = new TmxMapLoader().load("maps/".concat(mapName).concat(".tmx"));
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        statsRenderer = new StatsRenderer();
        buildMenu = new BuildMenu(SkinLoader);
        font = new BitmapFont(); // Create a new BitmapFont (consider loading a specific font if needed)
        font.getData().setScale(2.0f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Reminder Rendering order is Very important for UI
        // UI SHOULD COME LAST
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        KeyCheck();
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        statsRenderer.render(delta);
        buildMenu.render(delta);

    }

    public boolean KeyCheck() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            camera.translate(-32,0);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            camera.translate(32,0);
        if(Gdx.input.isKeyPressed(Input.Keys.UP)
        )           camera.translate(0,32);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            camera.translate(0,-32);
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1))
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2))
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        return false;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
