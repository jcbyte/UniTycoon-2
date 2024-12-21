package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.util.FileHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class is in charge of loading and drawing the textured tiles for the background.
 *
 * <p>The constructor is passed a mapName which then can be read using the {@code FileHandler}
 * which is then used to draw the map as defined in the map file.
 *
 * <p>This class also contains the constants that assign the characters that represent the tiles in
 * the map file.
 */
public class BackgroundRenderer {

  // Constants that map the tiles used to draw the background
  // to characters that can be encoded in the map file
  private static final char GRASS = 'G';
  private static final char WATER = 'W';
  private static final char COBBLE_STONE = 'C';
  private static final char ROAD = 'R';
  private static final char GRASS2 = 'g';
  private static final char WATER2 = 'w';
  private static final char COBBLE_STONE2 = 'c';
  private static final char ROAD2 = 'r';

  private static final int TILE_WIDTH = 32; // Size of each tile in game
  private static final int TILE_HEIGHT = 32; // Size of each tile in game

  //batch that the tiles will be drawn on
  private final SpriteBatch batch;

  //String of the map from file
  private final String map;

  // Textures of tiles
  private TextureRegion grassTile;
  private TextureRegion waterTile;
  private TextureRegion cobbleTile;
  private TextureRegion roadTile;
  private TextureRegion grassTile2;
  private TextureRegion waterTile2;
  private TextureRegion cobbleTile2;
  private TextureRegion roadTile2;

  /**
   * Creates a new background renderer.
   *
   * @param mapName The name of the map that will be drawn
   */
  public BackgroundRenderer(String mapName) {
    this.map = FileHandler.loadMap(mapName);
    batch = new SpriteBatch();

    //Texture of all tiles
    // Load your 64x64 PNG
    Texture texture = new Texture(Gdx.files.internal(
        "textures/textureAtlases/backgroundAtlas.png"));

    // Create TextureRegions for each tile
    int atlasTileSize = 64;
    grassTile = new TextureRegion(texture,
        0, 0, atlasTileSize, atlasTileSize);
    waterTile = new TextureRegion(texture,
        atlasTileSize, 0, atlasTileSize, atlasTileSize);
    cobbleTile = new TextureRegion(texture,
        atlasTileSize * 2, 0, atlasTileSize, atlasTileSize);
    roadTile = new TextureRegion(texture,
        atlasTileSize * 3, 0, atlasTileSize, atlasTileSize);
    grassTile2 = new TextureRegion(texture,
        0, atlasTileSize, atlasTileSize, atlasTileSize);
    waterTile2 = new TextureRegion(texture,
        atlasTileSize, atlasTileSize, atlasTileSize, atlasTileSize);
    cobbleTile2 = new TextureRegion(texture,
        atlasTileSize * 2, atlasTileSize, atlasTileSize, atlasTileSize);
    roadTile2 = new TextureRegion(texture,
        atlasTileSize * 3, atlasTileSize, atlasTileSize, atlasTileSize);
  }

  /**
   * Draws the background to the screen.
   *
   * @param delta Time since last frame
   */
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1); // Set background color
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear the screen

    batch.begin();
    drawTiledBackgroundFromMap();
    batch.end();
  }

  /**
   * Draws the background to the screen using the string map and char case switch.
   */
  private void drawTiledBackgroundFromMap() {
    int rows = map.split("\n").length;
    int cols = map.split("\n")[0].length();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        // The list has to be reversed as the file is read top to bottom but the tiles are drawn
        // bottom to top.

        List<String> strRowsList = Arrays.asList(map.split("\n"));
        Collections.reverse(strRowsList);

        switch (strRowsList.get(i).charAt(j)) {
          case GRASS ->
              batch.draw(grassTile, j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
          case WATER ->
              batch.draw(waterTile, j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
          case COBBLE_STONE ->
              batch.draw(cobbleTile, j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
          case ROAD ->
              batch.draw(roadTile, j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
          case GRASS2 ->
              batch.draw(grassTile2, j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
          case WATER2 ->
              batch.draw(waterTile2, j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
          case COBBLE_STONE2 ->
              batch.draw(cobbleTile2, j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
          case ROAD2 ->
              batch.draw(roadTile2, j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
          default -> {
          }
        }
      }
    }
  }

  /**
   * Call when the window is resized.
   */
  public void resize(int width, int height) {
  }

  /**
   * Disposes tile assets for garbage collection.
   */
  public void dispose() {
    batch.dispose();
    roadTile = null;
    grassTile = null;
    waterTile = null;
    cobbleTile = null;
    grassTile2 = null;
    waterTile2 = null;
    cobbleTile2 = null;
    roadTile2 = null;
  }

  public String getMap() {
    return map;
  }

  /**
   * Check if the tile is a grass tile.
   */
  public boolean isGrass(char tile) {
    return tile == GRASS || tile == GRASS2;
  }
}
