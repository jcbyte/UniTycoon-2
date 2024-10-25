package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.ArrayList;
import java.util.List;

public class BuildingRenderer implements Screen {

    private Stage stage;
    private Texture png1, png2;
    private SpriteBatch batch;
    private Texture selectedTexture;
    private float previewX, previewY;
    private boolean isPreviewing;
    private List<PlacedTexture> placedTextures;
    private boolean justClickedButton;

    public BuildingRenderer() {
        // Initialize stage, batch, textures, and UI
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        png1 = new Texture(Gdx.files.internal("png1.png"));
        png2 = new Texture(Gdx.files.internal("png2.png"));
        
        batch = new SpriteBatch();
        selectedTexture = null;
        isPreviewing = false;
        placedTextures = new ArrayList<>();
        justClickedButton = false;

        // Skin for buttons
        Skin skin = new Skin(Gdx.files.internal("ui/glassy-ui.json")); // Replace with your own skin or create custom button textures

        // Button for png1
        TextButton button1 = new TextButton("addTtest", skin);
        button1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selectedTexture = png1;
                isPreviewing = true;
                justClickedButton = true;
                return true;
            }
        });

        // Button for png2
        TextButton button2 = new TextButton("test2", skin);
        button2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selectedTexture = png2;
                justClickedButton = true;
                isPreviewing = true;
                return true;
            }
        });

        // Arrange buttons at the bottom
        Table table = new Table();
        table.bottom();
        table.add(button1).padRight(10);
        table.add(button2);
        table.setFillParent(true);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        // Clear screen and update stage
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        checkAddingBuildings(delta);
    }


    private void checkAddingBuildings(float delta){
        // Update preview position to follow the mouse cursor
        if (isPreviewing && selectedTexture != null) {
            previewX = Gdx.input.getX() - selectedTexture.getWidth() / 2;
            previewY = Gdx.graphics.getHeight() - Gdx.input.getY() - selectedTexture.getHeight() / 2;
        }

        batch.begin();

        // Draw all placed textures
        for (PlacedTexture pt : placedTextures) {
            batch.draw(pt.texture, pt.x, pt.y);
        }

        // Draw the preview texture if one is selected
        if (isPreviewing && selectedTexture != null) {
            batch.draw(selectedTexture, previewX, previewY);
        }

        batch.end();

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && justClickedButton){
            justClickedButton = false;
        }
        // Check for left mouse click to place the texture
        else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && selectedTexture != null && checkCollisions()) {
            placedTextures.add(new PlacedTexture(selectedTexture, previewX, previewY));
            isPreviewing = false;
            selectedTexture = null;

        }
    }

    private boolean checkCollisions(){

        return true;
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        png1.dispose();
        png2.dispose();
    }

    // Helper class to store placed textures
    private static class PlacedTexture {
        Texture texture;
        float x, y;

        PlacedTexture(Texture texture, float x, float y) {
            this.texture = texture;
            this.x = x;
            this.y = y;
        }
    }
}
