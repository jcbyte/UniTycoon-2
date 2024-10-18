package com.vikingz.unitycoon.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BackgroundRenderer{
    private SpriteBatch batch;
    private Texture texture;
    private TextureRegion grassTile, waterTile, cobbleTile, tile4;

    private final int tileSize = 32; // Size of each tile in game
    private final int atlasTileSize = 64;
    private int screenWidth;
    private int screenHeight;

    public BackgroundRenderer() {
        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal("textureAtlas/backgroundAtlas.png")); // Load your 64x64 PNG

        // Create TextureRegions for each tile
        grassTile = new TextureRegion(texture, 0, 0,                     atlasTileSize, atlasTileSize);   // Tile 1 (Top-left)
        waterTile = new TextureRegion(texture, atlasTileSize, 0,            atlasTileSize, atlasTileSize);  // Tile 2 (Top-right)
        cobbleTile = new TextureRegion(texture, atlasTileSize * 2, 0,    atlasTileSize, atlasTileSize);  // Tile 3 (Bottom-left)
        tile4 = new TextureRegion(texture, atlasTileSize * 3, 0,         atlasTileSize, atlasTileSize); // Tile 4 (Bottom-right)

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }




    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Set background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear the screen

        batch.begin();

        drawTiledBackground();

        // Draw the tiles at specific positions
        batch.draw(grassTile, 10, 10);  // Draw Tile 1
        batch.draw(waterTile, 70, 10);  // Draw Tile 1
        batch.draw(cobbleTile, 150, 10);  // Draw Tile 1

        batch.end();
    }

    private void drawTiledBackground() {
        // Calculate the number of tiles needed
        int rows = (int) Math.ceil((double) screenHeight / tileSize);
        int cols = (int) Math.ceil((double) screenWidth / tileSize);

        //int rows = 10;
        //int cols = 10;

        // Render the tiles
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Draw each tile at the appropriate position
                batch.draw(grassTile, col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }
    }



    public void resize(int width, int height) {
        // Update the screen dimensions
        screenWidth = width;
        screenHeight = height;
    }


    public void dispose() {
        // Dispose of resources
        batch.dispose();
    }
}
