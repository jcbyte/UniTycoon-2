package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.audio.GameSounds;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.building.buildings.AcademicBuilding;
import com.vikingz.unitycoon.building.buildings.AccommodationBuilding;
import com.vikingz.unitycoon.building.buildings.FoodBuilding;
import com.vikingz.unitycoon.building.buildings.RecreationalBuilding;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.util.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is in charge of drawing Buildings in the game.
 *
 * <p>This class also does the collision calculations for buildings which make sure that the user
 * is unable to place buildings on top of each other, as well as using right click to be able to
 * remove the buildings from the game.
 */
public class BuildingRenderer {

  // Used to draw buildings textures
  private final SpriteBatch batch;

  // X and Y values used to place buildings
  private float previewX;
  private float previewY;

  // If building is being placed by user
  private boolean isPreviewing;

  // List of all buildings placed and needs rendering
  private final List<Building> placedBuildings;

  // Texture of Building to be placed
  private TextureRegion selectedTexture;

  // Size of the building SCREEN_BUILDING_SIZExSCREEN_BUILDING_SIZE
  private static final int SCREEN_BUILDING_SIZE = 128;

  //Current Building being placed information
  private BuildingInfo currentBuildingInfo = null;

  // GameRender used to get mouse position and background tiles
  private final GameRenderer gameRenderer;

  /**
   * Creates a new Building Renderer.
   *
   * @param gameRenderer Parent renderer {@code GameRenderer}
   */
  public BuildingRenderer(GameRenderer gameRenderer) {
    this.gameRenderer = gameRenderer;
    batch = new SpriteBatch();
    isPreviewing = false;
    placedBuildings = new ArrayList<>();
    selectedTexture = null;
  }

  /**
   * Renders buildings.
   *
   * @param delta Time since last frame
   */
  public void render(float delta) {
    batch.begin();

    // Draw all placed textures
    for (Building building : placedBuildings) {
      batch.draw(building.getTexture(), building.getX(), building.getY());
    }

    checkBuildings();

    batch.end();
  }

  /**
   * Checks if the user is currently adding or removing buildings.
   */
  private void checkBuildings() {
    // Update preview position to follow the mouse cursor
    if (isPreviewing && selectedTexture != null) {

      // Makes sure that the mouse is in the center of the building texture
      float centerOffset = ((float) SCREEN_BUILDING_SIZE / 2) * gameRenderer.getViewportScaling();
      Point previewPoint = snapBuildingToGrid(
          Gdx.input.getX() - centerOffset,
          Gdx.input.getY() + centerOffset
      );

      previewX = previewPoint.getX();
      previewY = previewPoint.getY();

      // If we cannot place here due to collision or balance then give visual feedback
      if (
          !checkCollisions(previewX, previewY)
              || GameGlobals.BALANCE < currentBuildingInfo.getBuildingCost()
      ) {
        // Add tint to sprite
        batch.setColor(new Color(1f, 0.25f, 0.25f, 1f));
      }

      // Draw the preview texture if one is selected
      batch.draw(selectedTexture, previewX, previewY);

      // Remove the tint
      batch.setColor(Color.WHITE);
    }


    // Removes the building the user right clicks on
    if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && selectedTexture == null) {
      System.out.println("RightClick");

      Building buildingToRemove = getBuildingAtPoint(Gdx.input.getX(), Gdx.input.getY());

      if (buildingToRemove != null) {
        // Show confirm box before removing
        gameRenderer.getGameScreen().setPaused(true);
        gameRenderer.getGameScreen().getUiRenderer().showPopup(
            "Confirm " + buildingToRemove.getBuildingInfo().getName() + " Removal",
            "Cancel",
            () -> gameRenderer.getGameScreen().setPaused(false),
            "Remove\n+" + getBuildingRefundAmount(buildingToRemove) + " Money", () -> {
              removeBuilding(buildingToRemove, true);
              gameRenderer.getGameScreen().setPaused(false);
            }
        );
      }
    }

    // Check for left mouse click to place the texture
    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && selectedTexture != null) {
      if (checkCollisions(previewX, previewY)) {

        // Check if the user has enough money to buy that building
        float balanceAfterPurchase = GameGlobals.BALANCE - currentBuildingInfo.getBuildingCost();
        if (balanceAfterPurchase < 0) {
          System.out.println("Not enough money to buy building!!");
          GameSounds.playPlaceError();
        } else {
          // Plays the sound of a building being places
          GameSounds.playPlacedBuilding();

          // Adds a building of the correct type to the list of buildings that are
          // to be drawn to the screen.
          switch (currentBuildingInfo.getBuildingType()) {
            case ACADEMIC:
              placedBuildings.add(new AcademicBuilding(
                  selectedTexture, new Point(previewX, previewY), currentBuildingInfo));
              break;

            case ACCOMMODATION:
              placedBuildings.add(new AccommodationBuilding(
                  selectedTexture, new Point(previewX, previewY), currentBuildingInfo));
              break;

            case RECREATIONAL:
              placedBuildings.add(new RecreationalBuilding(
                  selectedTexture, new Point(previewX, previewY), currentBuildingInfo,
                  currentBuildingInfo.getCoinsPerSecond()));
              break;

            case FOOD:
              placedBuildings.add(new FoodBuilding(
                  selectedTexture, new Point(previewX, previewY), currentBuildingInfo,
                  currentBuildingInfo.getCoinsPerSecond()));
              break;

            case NONE:
              System.out.println("This shouldn't have happened hmm");
              break;

            default:
              break;
          }

          // Update stats
          GameGlobals.BALANCE -= currentBuildingInfo.getBuildingCost();
          GameGlobals.STUDENTS += currentBuildingInfo.getNumberOfStudents();
          incrementBuildingsCount(currentBuildingInfo.getBuildingType(), 1);

        }

        // The building is no longer being placed
        clearSelectedBuilding();
      } else {
        // If building is colliding with something
        System.err.println("Player Trying to place on a collision piece");
        GameSounds.playPlaceError();
      }
    }
  }

  /**
   * Get the amount a building should be refunded for.
   */
  private int getBuildingRefundAmount(Building building) {
    return Math.round(building.getBuildingInfo().getBuildingCost() * 0.75f);
  }

  /**
   * Remove a building.
   */
  public void removeBuilding(Building buildingToRemove, boolean refund) {
    if (buildingToRemove != null) {
      incrementBuildingsCount(buildingToRemove.getBuildingType(), -1);
      placedBuildings.remove(buildingToRemove);
      if (refund) {
        GameGlobals.BALANCE += getBuildingRefundAmount(buildingToRemove);
      }
    } else {
      System.out.println("building was null: " + null);
    }
  }

  /**
   * Selects a building by building id.
   *
   * @param buildingType buildingType of the building that the user wants to place down
   * @param index        int the index of where it is in the dictionary
   */
  public void selectBuilding(BuildingStats.BuildingType buildingType, int index) {
    isPreviewing = true;
    BuildingInfo newBuilding = BuildingStats.getInfo(buildingType, index);
    selectedTexture = BuildingStats.getTextureOfBuilding(
        BuildingStats.BuildingDict.get(buildingType)[index]);

    if (selectedTexture == null) {
      System.err.println("ERROR: Could not select building: "
          + BuildingStats.BuildingDict.get(buildingType)[index]);
    }

    currentBuildingInfo = newBuilding;
  }

  /**
   * Increments the counter on the screen for the corresponding building that has been placed.
   *
   * @param type   Type of the building that has been added
   * @param amount to increase it by, this can be negative to decrease
   */
  private void incrementBuildingsCount(BuildingStats.BuildingType type, int amount) {
    switch (type) {
      case ACADEMIC -> GameGlobals.ACADEMIC_BUILDINGS_COUNT += amount;
      case ACCOMMODATION -> GameGlobals.ACCOMMODATION_BUILDINGS_COUNT += amount;
      case RECREATIONAL -> GameGlobals.RECREATIONAL_BUILDINGS_COUNT += amount;
      case FOOD -> GameGlobals.FOOD_BUILDINGS_COUNT += amount;
      default -> System.err.println("Building type doesnt exist!");
    }
  }

  /**
   * Snaps the position to coordinates in the grid.
   *
   * @param x screen X coordinate
   * @param y screen Y coordinate
   * @return Point new coordinates that occur on an intersection of the tiles in the background
   */
  private Point snapBuildingToGrid(float x, float y) {
    // 30 rows
    // 56 cols
    int gridSize = 32;
    Point translatedPoint = gameRenderer.translateCoords(new Point(x, y));

    float newX = Math.round(translatedPoint.getX() / gridSize) * gridSize;
    float newY = Math.round(translatedPoint.getY() / gridSize) * gridSize;

    return new Point(newX, newY);
  }

  /**
   * Checks whether the user is trying to place a building on another building or not on grass.
   *
   * @param x grid X coordinate
   * @param y grid Y coordinate
   * @return if it is clear to place the building
   */
  private boolean checkCollisions(float x, float y) {
    //Checks building exists in spot
    float roundedX = Math.round(x);
    float roundedY = Math.round(y);

    for (Building building : this.placedBuildings) {
      if (
          (
              roundedX > (building.getX() - SCREEN_BUILDING_SIZE)
                  && roundedX < (building.getX() + SCREEN_BUILDING_SIZE)
          )
              && (roundedY > (building.getY() - SCREEN_BUILDING_SIZE)
              && roundedY < (building.getY() + SCREEN_BUILDING_SIZE)
          )
      ) {
        return false;
      }
    }

    BackgroundRenderer backgroundRenderer = gameRenderer.getBackgroundRenderer();
    String map = backgroundRenderer.getMap();

    //CheckTiles on the ground are grassBlocks
    int indexLowY = Math.round((roundedY - 64) / 32) + 3;
    int indexLowX = Math.round((roundedX - 64) / 32) + 2;
    int lengthTiles = map.split("\n").length;
    char[][] tileSet = new char[4][4];
    for (int coordY = 0; coordY < 4; coordY++) {
      for (int coordX = 0; coordX < 4; coordX++) {
        try {
          tileSet[coordY][coordX] = map.split("\n")[lengthTiles - (indexLowY + coordY)]
              .charAt(indexLowX + coordX);
        } catch (Exception ignored) {
          continue;
        }
      }
    }
    for (char[] itemI : tileSet) {
      for (char itemJ : itemI) {
        if (!backgroundRenderer.isGrass(itemJ)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Gets the building at a screen coordinate.
   *
   * @param mouseX screen X coordinate
   * @param mouseY screen Y coordinate
   * @return Building at the coords
   */
  private Building getBuildingAtPoint(float mouseX, float mouseY) {
    Point translatedPoint = gameRenderer.translateCoords(new Point(mouseX, mouseY));

    float x = translatedPoint.getX();
    float y = translatedPoint.getY();

    for (Building building : this.placedBuildings) {

      float bx = building.getX();
      float by = building.getY();

      if (
          (x > bx && x < (bx + building.getWidth()))
              && (y > by && y < (by + building.getHeight()))
      ) {
        return building;
      }
    }
    return null;
  }

  /**
   * Call on window resize.
   */
  public void resize(int width, int height) {
  }

  /**
   * Clear currently selected building to place.
   */
  public void clearSelectedBuilding() {
    isPreviewing = false;
    currentBuildingInfo = null;
    selectedTexture = null;
  }

  /**
   * Gets the current list of placed buildings.
   */
  public List<Building> getPlaceBuildings() {
    return placedBuildings;
  }

  /**
   * Dispose building for garbage collection.
   */
  public void dispose() {
    batch.dispose();
  }


}
