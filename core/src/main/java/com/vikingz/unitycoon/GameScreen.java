package com.vikingz.unitycoon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {

    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture texture; // Example texture, replace with your game assets
    private String map;

    public GameScreen(Game game, String map) {
        this.game = game;
        this.map = map;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480); // Adjust camera settings for your game's resolution
        batch = new SpriteBatch();

        // Example texture, replace with your own game objects or background
        texture = new Texture(Gdx.files.internal("png/logo.png")); // Replace with your texture
    }

    @Override
    public void show() {
        // Initialize game objects here
    }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the camera
        camera.update();

        // Draw game objects
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texture, 0, 0 ,500, 500);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Adjust the viewport when the window size changes
        camera.setToOrtho(false, width, height);
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
        batch.dispose();
        texture.dispose();
    }
}
