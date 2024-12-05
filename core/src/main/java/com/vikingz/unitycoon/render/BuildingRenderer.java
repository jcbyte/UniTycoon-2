package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.building.buildings.AcademicBuilding;
import com.vikingz.unitycoon.building.buildings.AccommodationBuilding;
import com.vikingz.unitycoon.building.buildings.FoodBuilding;
import com.vikingz.unitycoon.building.buildings.RecreationalBuilding;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.util.GameSounds;
import com.vikingz.unitycoon.util.Point;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class is in charge of drawing Buildings in the game.
 * <p>
 * This class also does the collision calculations for buildings
 * which make sure that the user is unable to place buildings on top
 * of each other, as well as using right click to be able to remove the
 * buildings from the game.
 */
public class BuildingRenderer{

    //Used to draw buildings textures
    private final SpriteBatch batch;

    //X and Y values used to place buildings
    private float previewX, previewY;

    //If building is being placed by user
    private boolean isPreviewing;

    //List of all buildings placed and needs rendering
    private final List<Building> placedBuildings ;

    //Texture of Building to be placed
    private TextureRegion selectedTexture;

    //Size of the building SCREEN_BUILDING_SIZExSCREEN_BUILDING_SIZE
    private final int SCREEN_BUILDING_SIZE = 128;

    //Current Building being placed information
    private BuildingInfo currentBuildingInfo = null;

    //GameRender used to get mouse position and background tiles
    private final GameRenderer gameRenderer;

    /**
     * Creates a new Building Renderer
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
     * Renders buildings
     * @param delta Time since last frame
     */
    public void render(float delta) {
        checkBuildings(delta);
    }

    /**
     * Checks if the user is currently adding or removing buildings
     * @param delta Time since last frame
     */
    private void checkBuildings(float delta){
        batch.begin();

        // Draw all placed textures
        for (Building building : placedBuildings) {
            batch.draw(building.getTexture(), building.getX(), building.getY());
        }

        // Update preview position to follow the mouse cursor
        if (isPreviewing && selectedTexture != null) {

            // Makes sure that the mouse is in the center of the building texture
            float centerOffset = ((float) SCREEN_BUILDING_SIZE / 2) * gameRenderer.getViewportScaling();
            Point previewPoint = snapBuildingToGrid(Gdx.input.getX() - centerOffset, Gdx.input.getY() + centerOffset);

            previewX = previewPoint.getX();
            previewY = previewPoint.getY();

            // If we cannot place here due to collision or balance then give visual feedback
            if (!checkCollisions(previewX, previewY) || GameGlobals.BALANCE < currentBuildingInfo.getBuildingCost())
            {
                // Add tint to sprite
                batch.setColor(new Color(1f, 0.25f, 0.25f, 1f));
            }

            // Draw the preview texture if one is selected
            batch.draw(selectedTexture, previewX, previewY);

            // Remove the tint
            batch.setColor(Color.WHITE);
        }

        batch.end();

        // Removes the building the user right clicks on
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && selectedTexture == null){
            System.out.println("RightClick");

            Building buildingToRemove = getBuildingAtPoint(Gdx.input.getX(), Gdx.input.getY());

            if(buildingToRemove != null){
                float value = buildingToRemove.getBuildingInfo().getBuildingCost();
                this.placedBuildings.remove(buildingToRemove);
                GameGlobals.BALANCE += Math.round(value*0.75f);
            }
            else{
                System.out.println("building was null: " + null);
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

                }
                else {
                    // Plays the sound of a building being places
                    GameSounds.playPlacedBuilding();

                    // Adds a building of the correct type to the list of buildings that are
                    // to be drawn to the screen.
                    switch (currentBuildingInfo.getBuildingType()) {
                        case ACADEMIC:
                            placedBuildings.add(new AcademicBuilding(selectedTexture, new Point(previewX, previewY), currentBuildingInfo));
                            break;

                        case ACCOMODATION:
                            placedBuildings.add(new AccommodationBuilding(selectedTexture, new Point(previewX, previewY), currentBuildingInfo, currentBuildingInfo.getNumberOfStudents()));
                            break;


                        case RECREATIONAL:
                            placedBuildings.add(new RecreationalBuilding(selectedTexture, new Point(previewX, previewY), currentBuildingInfo, currentBuildingInfo.getCoinsPerSecond()));
                            break;

                        case FOOD:
                            placedBuildings.add(new FoodBuilding(selectedTexture, new Point(previewX, previewY),currentBuildingInfo, currentBuildingInfo.getCoinsPerSecond()));
                            break;

                        case NONE:
                            System.out.println("This shouldn't have happened hmm");

                        default:
                            break;
                    }

                    //Updates stats
                    GameGlobals.BALANCE -= currentBuildingInfo.getBuildingCost();
                    GameGlobals.STUDENTS += currentBuildingInfo.getNumberOfStudents();
                    incrementBuildingsCount(currentBuildingInfo.getBuildingType());

                }
                //the building is no longer being placed
                isPreviewing = false;
                currentBuildingInfo = null;
                selectedTexture = null;
            }
            else {
                //if building is colliding with something
                System.err.println("Player Trying to place on a collision piece");
                GameSounds.playPlaceError();
            }
        }

    }

    /**
     * Selects a building by building ID
     * @param buildingType buildingType of the building that the user wants to place down
     * @param index int the index of where it is in the dictionary
     */
    public void selectBuilding(BuildingStats.BuildingType buildingType, int index){

        isPreviewing = true;
        BuildingInfo newBuilding = BuildingStats.getInfo(buildingType,index);
        selectedTexture = BuildingStats.getTextureOfBuilding(BuildingStats.BuildingDict.get(buildingType)[index]);
        if (selectedTexture == null){
            System.err.println("ERROR: Could not select building: " + BuildingStats.BuildingDict.get(buildingType)[index]);
        }
        currentBuildingInfo = newBuilding;
    }

    /**
     * Increments the counter on the screen for the
     * corresponding building that has been placed down
     * @param type Type of the building that has been added
     */
    private void incrementBuildingsCount(BuildingStats.BuildingType type){

        switch (type) {
            case ACADEMIC -> GameGlobals.ACADEMIC_BUILDINGS_COUNT++;
            case ACCOMODATION -> GameGlobals.ACCOMODATION_BUILDINGS_COUNT++;
            case RECREATIONAL -> GameGlobals.RECREATIONAL_BUILDINGS_COUNT++;
            case FOOD -> GameGlobals.FOOD_BUILDINGS_COUNT++;
            default -> System.out.println("Building type doesnt exist!");
        }

    }

    /**
     * Snaps the coordinates passed in to the grid
     * @param x X
     * @param y Y
     * @return Point new coordinates that occur on an intersection of the tiles in the background
     */
    private Point snapBuildingToGrid(float x, float y){

        // 30 rows
        // 56 cols
        int gridSize = 32;
        Point translatedPoint = gameRenderer.translateCoords(new Point(x, y));

        float newX = Math.round(translatedPoint.getX() / gridSize) * gridSize;
        float newY = Math.round(translatedPoint.getY() / gridSize) * gridSize;

        return new Point(newX, newY);
    }


    /**
     * Checks whether the user is trying to place a building
     * on another building or not on grass
     * @param x X
     * @param y Y
     * @return Boolean if there exists a building at the spot the user is trying to place the building at
     *          or if non grass is present in the buildings spot
     */
    private boolean checkCollisions(float x, float y){
        //Checks building exists in spot
        float RoundedX = Math.round(x);
        float RoundedY = Math.round(y);
        boolean flag = true;
        for (Building building: this.placedBuildings) {
            if (
                (RoundedX > (building.getX() - SCREEN_BUILDING_SIZE) && RoundedX < (building.getX() + SCREEN_BUILDING_SIZE)) &&
                    (RoundedY > (building.getY() - SCREEN_BUILDING_SIZE) && RoundedY < (building.getY() + SCREEN_BUILDING_SIZE))
            ) {
                flag = false;
                break;
            }
        }
        BackgroundRenderer checkBackGround = gameRenderer.getBackgroundRenderer();
        String hold = checkBackGround.getMap();

        //CheckTiles on the ground are grassBlocks
        int yIndexLow = Math.round((RoundedY-64)/32) + 3;
        int xIndexLow = Math.round((RoundedX-64)/32) + 2;
        int lengthTiles = hold.split("\n").length;
        char[][] TileSet = new char[4][4];
        for (int yCord=0;yCord<4;yCord++){
            for (int xCord=0;xCord<4;xCord++){
                try {
                    TileSet[yCord][xCord] = hold.split("\n")[lengthTiles - (yIndexLow + yCord)].charAt(xIndexLow + xCord);
                }catch (Exception ignored){

                }
            }
        }
        for (char[] itemI: TileSet) {
            for (char itemJ: itemI) {
                if (itemJ != checkBackGround.getGRASS() && itemJ != checkBackGround.getGRASS2()) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * Gets the building at the point paused in
     * @param mouseX Mouse X
     * @param mouseY Mouse Y
     * @return Building that was at the coords
     */
    private Building getBuildingAtPoint(float mouseX, float mouseY){
        Point translatedPoint = gameRenderer.translateCoords(new Point(mouseX, mouseY));

        float x = translatedPoint.getX();
        float y = translatedPoint.getY();

        for (Building building: this.placedBuildings) {

            float bx = building.getX();
            float by = building.getY();

            if(  (x > bx && x < (bx + building.getWidth())) &&
                 (y > by && y < (by + building.getHeight())) ){
                    return building;
            }
        }
        return null;
    }

    /**
     * Updates the width and height when the window
     * is resized
     */
    public void resize() {
    }

    // Getter and setters

    /**
     * gets the current list of placed buildings
     * @return placedBuildings List<Building>
     */
    public List<Building> getPlaceBuildings(){
        return placedBuildings;
    }

    /**
     * disposes building being drawn for garbage collection
     */
    public void dispose(){
        batch.dispose();
    }


}
