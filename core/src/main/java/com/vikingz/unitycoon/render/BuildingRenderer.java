package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.building.BuildingInfo;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.building.buildings.AcademicBuilding;
import com.vikingz.unitycoon.building.buildings.AccommodationBuilding;
import com.vikingz.unitycoon.building.buildings.FoodBuilding;
import com.vikingz.unitycoon.building.buildings.RecreationalBuilding;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.util.GameSounds;
import com.vikingz.unitycoon.util.Point;

import java.util.ArrayList;
import java.util.List;

public class BuildingRenderer{

    private int width;
    private int height;

    private SpriteBatch batch;
    private float previewX, previewY;
    private boolean isPreviewing = false;
    private List<Building> placedBuildings ;

    private TextureRegion selectedTexture;
    private int SCREEN_BUILDING_SIZE = 128;
    private BuildingInfo currentBuildingInfo = null;

    private GameRenderer gameRenderer;

    public BuildingRenderer(GameRenderer gameRenderer) {

        this.width = GameConfig.getInstance().getWindowWidth();
        this.height = GameConfig.getInstance().getWindowHeight();
        this.gameRenderer = gameRenderer;

        batch = new SpriteBatch();
        isPreviewing = false;
        placedBuildings = new ArrayList<>();
        selectedTexture = null;


    }

    public void render(float delta) {
        checkAddingBuildings(delta);
    }


    private void checkAddingBuildings(float delta){
        // Update preview position to follow the mouse cursor
        if (isPreviewing && selectedTexture != null) {

            // Makes sure that the mouse is in the center of the building texture
            Point previewPoint = snapBuildingToGrid(Gdx.input.getX() - SCREEN_BUILDING_SIZE / 2, Gdx.input.getY() + SCREEN_BUILDING_SIZE / 2);

            previewX = previewPoint.getX();
            previewY = previewPoint.getY();

        }

        batch.begin();

        // Draw all placed textures
        for (Building building : placedBuildings) {
            batch.draw(building.getTexture(), building.getX(), building.getY());
        }

        // Draw the preview texture if one is selected
        if (isPreviewing && selectedTexture != null) {
            batch.draw(selectedTexture, previewX, previewY);
        }

        batch.end();


        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && selectedTexture == null){
            System.out.println("RightClick");


            Building buildingToRemove = getBuildingAtPoint(Gdx.input.getX(), Gdx.input.getY());


            if(buildingToRemove != null){
                float value = buildingToRemove.getBuildingInfo().getBuildingCost();
                this.placedBuildings.remove(buildingToRemove);
                GameGlobals.BALANCE += Math.round(value*0.75f);
            }
            else{
                System.out.println("building was null: " + buildingToRemove);
            }
        }

        // Check for left mouse click to place the texture
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && selectedTexture != null) {
            if (checkCollisions(previewX, previewY)) {

                // Check if the uesr has enough money to buy that building
                float balanceAfterPurchase = GameGlobals.BALANCE - currentBuildingInfo.getBuildingCost();
                if (balanceAfterPurchase < 0) {
                    System.out.println("Not enough money to buy building!!");
                    GameSounds.playPlaceError();

                }
                else {
                    GameSounds.playPlacedBuilding();
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
                            System.out.println("This shouldnt have happend hmm");

                        default:
                            break;
                    }

                    GameGlobals.BALANCE -= currentBuildingInfo.getBuildingCost();
                    GameGlobals.STUDENTS += currentBuildingInfo.getNumberOfStudents();


                    incrementBuildingsCount(currentBuildingInfo.getBuildingType());

                }
                isPreviewing = false;
                currentBuildingInfo = null;
                selectedTexture = null;
            }
            else {
                System.err.println("Player Trying to place on a collision piece");
                GameSounds.playPlaceError();
            }
        }

    }


    public void selectBuilding(BuildingStats.BuildingID buildingID){

        isPreviewing = true;
        BuildingInfo newBuilding = BuildingStats.getInfo(buildingID);

        selectedTexture = BuildingStats.getTextureOfBuilding(buildingID);
        if (selectedTexture == null){
            System.err.println("ERROR: Could not select building: " + buildingID);
        }
        currentBuildingInfo = newBuilding;
    }


    private void incrementBuildingsCount(BuildingStats.BuildingType type){

        switch (type) {
            case ACADEMIC:
                GameGlobals.ACADEMIC_BUILDINGS_COUNT ++;
                break;


            case ACCOMODATION:
                GameGlobals.ACCOMODATION_BUILDINGS_COUNT ++;
                break;

            case RECREATIONAL:
                GameGlobals.RECREATIONAL_BUILDINGS_COUNT ++;
                break;

            case FOOD:
                GameGlobals.FOOD_BUILDINGS_COUNT ++;
                break;

            default:
                System.out.println("Building type doesnt exist!");
                break;
        }

    }

    private Point snapBuildingToGrid(float x, float y){

        // 30 rows
        // 56 cols
        int gridSize = 32;
        Point translatedPoint = gameRenderer.translateCoords(new Point(x, y));

        float newX = Math.round(translatedPoint.getX() / gridSize) * gridSize;
        float newY = Math.round(translatedPoint.getY() / gridSize) * gridSize;

        return new Point(newX, newY);
    }


    private boolean checkCollisions(float x, float y){
        float RoundedX = Math.round(x);
        float RoundedY = Math.round(y);
        boolean flag = true;
        for (Building building: this.placedBuildings) {
            if (
                (RoundedX > (building.getX()-SCREEN_BUILDING_SIZE) && RoundedX < (building.getX()+SCREEN_BUILDING_SIZE)) &&
                (RoundedY > (building.getY()-SCREEN_BUILDING_SIZE) && RoundedY < (building.getY()+SCREEN_BUILDING_SIZE))
            ){
                flag = false;
            }
        }
        return flag;
    }

    private Building getBuildingAtPoint(float mouseX, float mouseY){
        Point translatedPoint = gameRenderer.translateCoords(new Point(mouseX, mouseY));

        float x = translatedPoint.getX();
        float y = translatedPoint.getY();

        for (Building building: this.placedBuildings) {

            float bx = building.getX();
            float by = building.getY();

            if(  (x > bx && x < (bx + building.getWidth())) &&
                 (y > by && y < (by + building.getHeight()))  ){
                    return building;
            }
        }
        return null;
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public List<Building> getPlaceBuildings(){
        return placedBuildings;
    }

    public void dispose(){
        batch.dispose();
    }

}
