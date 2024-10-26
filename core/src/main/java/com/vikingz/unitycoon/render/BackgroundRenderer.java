package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.util.FileHandler;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BackgroundRenderer{

    private final char GRASS = 'G';
    private final char WATER = 'W';
    private final char COBBLE_STONE = 'C';
    private final char ROAD = 'R';


    private SpriteBatch batch;
    private String mapName;
    private String map;
    private Texture texture;
    private TextureRegion grassTile, waterTile, cobbleTile, roadTile;

    private final int tileSize = 32; // Size of each tile in game
    private final int atlasTileSize = 64;
    private int screenWidth;
    private int screenHeight;

    public BackgroundRenderer(String mapName) {
        this.mapName = mapName;

        this.map = FileHandler.loadMap(mapName);

        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal("textureAtlases/backgroundAtlas.png")); // Load your 64x64 PNG

        // Create TextureRegions for each tile
        grassTile = new TextureRegion(texture, 0, 0,                     atlasTileSize, atlasTileSize);  
        waterTile = new TextureRegion(texture, atlasTileSize, 0,            atlasTileSize, atlasTileSize); 
        cobbleTile = new TextureRegion(texture, atlasTileSize * 2, 0,    atlasTileSize, atlasTileSize); 
        roadTile = new TextureRegion(texture, atlasTileSize * 3, 0,         atlasTileSize, atlasTileSize); 

        
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }




    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Set background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear the screen

        batch.begin();

        //drawTiledBackground();
        drawTiledBackgroundFromMap();

        // Draw the tiles at specific positions
        //batch.draw(grassTile, 10, 10);  // Draw Tile 1
        //batch.draw(waterTile, 70, 10);  // Draw Tile 1
        //batch.draw(cobbleTile, 150, 10);  // Draw Tile 1

        batch.end();
    }

    private void drawTiledBackground() {
        // Calculate the number of tiles needed
        //int rows = (int) Math.ceil((double) screenHeight / tileSize);
        //int cols = (int) Math.ceil((double) screenWidth / tileSize);

        int rows = 31;
        int cols = 57;

        // Render the tiles
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Draw each tile at the appropriate position
                batch.draw(grassTile, col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }
    }

    private void drawTiledBackgroundFromMap() {
        // Calculate the number of tiles needed
        //int rows = (int) Math.ceil((double) screenHeight / tileSize);
        //int cols = (int) Math.ceil((double) screenWidth / tileSize);

        int rowsMax = 31;
        int colsMax = 57;

        int rows = map.split("\n").length;
        int cols = map.split("\n")[0].length();



        for(int i = 0; i < rows ; i++){
            for(int j = 0; j < cols; j++){

                // The list has to be reversed as the file is read top to bottom
                // but the tiles are drawn bottom to top.

                List<String> strRowsList = Arrays.asList(map.split("\n"));
                Collections.reverse(strRowsList);

                switch (strRowsList.get(i).charAt(j)){
                    case GRASS:
                        batch.draw(grassTile, j * tileSize, i * tileSize, tileSize, tileSize);
                        break;

                    case WATER:
                        batch.draw(waterTile, j * tileSize, i * tileSize, tileSize, tileSize);
                        break;

                    case COBBLE_STONE:
                        batch.draw(cobbleTile, j * tileSize, i * tileSize, tileSize, tileSize);
                        break;

                    case ROAD:
                        batch.draw(roadTile, j * tileSize, i * tileSize, tileSize, tileSize);
                        break;                  


                }

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
