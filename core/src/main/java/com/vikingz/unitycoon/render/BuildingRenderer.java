package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.vikingz.unitycoon.buildings.AcademicBuilding;
import com.vikingz.unitycoon.buildings.AccommodationBuilding;
import com.vikingz.unitycoon.buildings.Building;
import com.vikingz.unitycoon.buildings.BuildingType;
import com.vikingz.unitycoon.util.Point;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.ArrayList;
import java.util.List;

public class BuildingRenderer{

    private SpriteBatch batch;
    private TextureRegion selectedTexture;
    private float previewX, previewY;
    private boolean isPreviewing;
    private BuildingType buildingType;
    private List<Building> placedBuildings;
    private boolean justClickedButton;
    private Texture textureAtlas;
    private TextureRegion piazzaBuilding, ronCookeBuilding;
    private float currentSatisfactionMultiplier;

    private int atlasBuildingSize;
    private int SCREEN_BUILDING_SIZE = 128;

    public BuildingRenderer() {
        // Initialize stage, batch, textures, and UI
        //Gdx.input.setInputProcessor(stage);

        atlasBuildingSize = 128;



        // Adding texture atlas
        textureAtlas = new Texture(Gdx.files.internal("textureAtlases/buildingsAtlas.png")); // Load your 64x64 PNG
        piazzaBuilding = new TextureRegion(textureAtlas, 0, 0,atlasBuildingSize, atlasBuildingSize);   // Tile 1 (Top-left)
        ronCookeBuilding = new TextureRegion(textureAtlas, atlasBuildingSize, 0,atlasBuildingSize, atlasBuildingSize);   // Tile 1 (Top-left)




        batch = new SpriteBatch();
        selectedTexture = null;
        isPreviewing = false;
        placedBuildings = new ArrayList<>();
        justClickedButton = false;
        buildingType = BuildingType.ACADEMIC;
        currentSatisfactionMultiplier = 0;


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

        // Fixes placing buildings on button which doesnt happen any more due to switch to building menu
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && justClickedButton){
            justClickedButton = false;
        }
        // Check for left mouse click to place the texture
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && selectedTexture != null && checkCollisions()) {

            //placedBuildings.add(new AcademicBuilding(selectedTexture, previewX, previewY));

            switch (buildingType) {
                    case ACADEMIC:
                        placedBuildings.add(new AcademicBuilding(selectedTexture, snapBuildingToGrid(previewX, previewY), currentSatisfactionMultiplier));
                        break;

                    case ACCOMODATION:
                        placedBuildings.add(new AccommodationBuilding(selectedTexture, snapBuildingToGrid(previewX, previewY), currentSatisfactionMultiplier));
                        break;

                    default:
                        placedBuildings.add(new AcademicBuilding(selectedTexture, snapBuildingToGrid(previewX, previewY), currentSatisfactionMultiplier));
                        break;
                }

            isPreviewing = false;
            selectedTexture = null;
            currentSatisfactionMultiplier = 0f;
        }
    }


    public void selectBuilding(String buildingID, BuildingType buildingType){

        justClickedButton = true;
        isPreviewing = true;
        this.buildingType = buildingType;


        switch (buildingID) {
            case "piazza":
                selectedTexture = piazzaBuilding;
                currentSatisfactionMultiplier = 1.2f;
                break;

            case "rch":
                selectedTexture = ronCookeBuilding;
                currentSatisfactionMultiplier = 1.1f;
                break;

            default:
                selectedTexture = piazzaBuilding;
                currentSatisfactionMultiplier = 0f;
                break;
        }




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




    public void dispose() {
        batch.dispose();
        textureAtlas.dispose();
    }



}
