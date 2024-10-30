package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
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
import com.vikingz.unitycoon.util.Point;

import java.util.ArrayList;
import java.util.List;

public class BuildingRenderer{

    private SpriteBatch batch;
    private float previewX, previewY;
    private boolean isPreviewing;
    private List<Building> placedBuildings;
    private Texture textureAtlas;
    
    private TextureRegion selectedTexture;

    // Buildings: Academic
    private TextureRegion piazzaBuilding, ronCookeBuilding;
    // Buildings: Accomodation
    private TextureRegion davidKatoBuilding, anneListerBuilding;
    // Buildings: Recreational
    private TextureRegion ysvBuilding;
    // Buildings: Food
    private TextureRegion mcdBuilding;
    


    private int atlasBuildingSize;
    private int SCREEN_BUILDING_SIZE = 128;


    private BuildingInfo currentBuildingInfo;

    public BuildingRenderer() {
        // Initialize stage, batch, textures, and UI
        //Gdx.input.setInputProcessor(stage);

        atlasBuildingSize = 128;


        // Adding texture atlas
        textureAtlas = new Texture(Gdx.files.internal("textureAtlases/buildingsAtlas.png")); // Load your 64x64 PNG

        // Academic
        piazzaBuilding = new TextureRegion(textureAtlas, 0, 0,atlasBuildingSize, atlasBuildingSize);   
        ronCookeBuilding = new TextureRegion(textureAtlas, atlasBuildingSize, 0,atlasBuildingSize, atlasBuildingSize);   

        // Accomodation
        davidKatoBuilding = new TextureRegion(textureAtlas, 0, atlasBuildingSize, atlasBuildingSize, atlasBuildingSize);
        anneListerBuilding = new TextureRegion(textureAtlas, atlasBuildingSize, atlasBuildingSize, atlasBuildingSize, atlasBuildingSize);

        // Recreational
        ysvBuilding = new TextureRegion(textureAtlas, 0, atlasBuildingSize * 2, atlasBuildingSize, atlasBuildingSize);

        // Food
        mcdBuilding = new TextureRegion(textureAtlas, 0, atlasBuildingSize * 3, atlasBuildingSize, atlasBuildingSize);



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

            Point previewPoint = snapBuildingToGrid(Gdx.input.getX() - SCREEN_BUILDING_SIZE / 2, Gdx.graphics.getHeight() - Gdx.input.getY() - SCREEN_BUILDING_SIZE / 2);

            //previewX = Gdx.input.getX() - SCREEN_BUILDING_SIZE / 2;
            //previewY = Gdx.graphics.getHeight() - Gdx.input.getY() - SCREEN_BUILDING_SIZE / 2;

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


        // Check for left mouse click to place the texture
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && selectedTexture != null && checkCollisions()) {


            // Check if the uesr has enough money to buy that building
            float balanceAfterPurchase = GameGlobals.BALANCE - currentBuildingInfo.getBuildingCost();
            if(balanceAfterPurchase < 0){
                System.out.println("Not enough money to buy building!!");
                
            }

            else{

                switch (currentBuildingInfo.getBuildingType()) {
                    case ACADEMIC:
                        placedBuildings.add(new AcademicBuilding(selectedTexture, snapBuildingToGrid(previewX, previewY), currentBuildingInfo.getBuildingType(), currentBuildingInfo.getSatisfactionMultiplier()));
                        break;
    
                    case ACCOMODATION:
                        placedBuildings.add(new AccommodationBuilding(selectedTexture, snapBuildingToGrid(previewX, previewY), currentBuildingInfo.getBuildingType(), currentBuildingInfo.getSatisfactionMultiplier(), currentBuildingInfo.getNumberOfStudents()));
                        break;
    
    
                    case RECREATIONAL:
                        placedBuildings.add(new RecreationalBuilding(selectedTexture, snapBuildingToGrid(previewX, previewY), currentBuildingInfo.getBuildingType(), currentBuildingInfo.getSatisfactionMultiplier(), currentBuildingInfo.getCoinsPerSecond()));
                        break;
    
                    case FOOD:
                        placedBuildings.add(new FoodBuilding(selectedTexture, snapBuildingToGrid(previewX, previewY), currentBuildingInfo.getBuildingType(), currentBuildingInfo.getSatisfactionMultiplier(), currentBuildingInfo.getCoinsPerSecond()));
                        break;
                }
    
                GameGlobals.BALANCE -= currentBuildingInfo.getBuildingCost();
                GameGlobals.STUDENTS += currentBuildingInfo.getNumberOfStudents();
                GameGlobals.BUILDINGS_COUNT ++;

            }

            isPreviewing = false;
            currentBuildingInfo = null;
            selectedTexture = null;
        }
    }


    public void selectBuilding(BuildingStats.BuildingID buildingID){

        isPreviewing = true;

        BuildingInfo newBuilding = BuildingStats.getInfo(buildingID);



        switch (buildingID) {

            // Academic
            case PZA:
                selectedTexture = piazzaBuilding;
                break;

            case RCH:
                selectedTexture = ronCookeBuilding;
                break;

            // Accomodation
            case KATO:
                selectedTexture = davidKatoBuilding;
                break;

            case LISTER:
                selectedTexture = anneListerBuilding;
                break;

            // Recreational
            case YSV:
                selectedTexture = ysvBuilding;
                break;

            // Food
            case MCD:
                selectedTexture = mcdBuilding;
                break;


            default:
                System.out.println("ERROR: Could not select building: " + buildingID);
                break;
        }

        currentBuildingInfo = newBuilding;


    }


    private Point snapBuildingToGrid(float x, float y){

        int gridSize = 32;

        float newX = Math.round(x / gridSize) * gridSize;
        float newY = Math.round(y / gridSize) * gridSize;


        return new Point(newX, newY);

    }


    private boolean checkCollisions(){

        return true;
    }

    public void resize(int width, int height) {
        
    }

    public List<Building> getPlaceBuildings(){
        return placedBuildings;
    }



    public void dispose() {
        batch.dispose();
        textureAtlas.dispose();
    }


}
