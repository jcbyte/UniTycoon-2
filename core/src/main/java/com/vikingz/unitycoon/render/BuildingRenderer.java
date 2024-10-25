package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.vikingz.unitycoon.buildings.AcademicBuilding;
import com.vikingz.unitycoon.buildings.Building;
import com.vikingz.unitycoon.buildings.BuildingType;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.ArrayList;
import java.util.List;

public class BuildingRenderer{

    private Stage stage;
    private SpriteBatch batch;
    private TextureRegion selectedTexture;
    private float previewX, previewY;
    private boolean isPreviewing;
    private BuildingType buildingType;
    private List<Building> placedBuildings;
    private boolean justClickedButton;
    private Texture textureAtlas;
    private TextureRegion building1, building2;

    private int atlasBuildingSize;
    private int SCREEN_BUILDING_SIZE = 128;

    public BuildingRenderer() {
        // Initialize stage, batch, textures, and UI
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        atlasBuildingSize = 128;
        


        // Adding texture atlas
        textureAtlas = new Texture(Gdx.files.internal("textureAtlases/buildingsAtlas.png")); // Load your 64x64 PNG
        building1 = new TextureRegion(textureAtlas, 0, 0,atlasBuildingSize, atlasBuildingSize);   // Tile 1 (Top-left)
        building2 = new TextureRegion(textureAtlas, 0, 0,atlasBuildingSize, atlasBuildingSize);   // Tile 1 (Top-left)



        
        batch = new SpriteBatch();
        selectedTexture = null;
        isPreviewing = false;
        placedBuildings = new ArrayList<>();
        justClickedButton = false;
        buildingType = BuildingType.ACADEMIC;

        // Skin for buttons
        Skin skin = new Skin(Gdx.files.internal("ui/glassy-ui.json")); // Replace with your own skin or create custom button textures

        // Button for png1
        TextButton button1 = new TextButton("addTtest", skin);
        button1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selectedTexture = building1;
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
                selectedTexture = building1;
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

    public void render(float delta) {
        // Clear screen and update stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        checkAddingBuildings(delta);
    }


    private void checkAddingBuildings(float delta){
        // Update preview position to follow the mouse cursor
        if (isPreviewing && selectedTexture != null) {
            previewX = Gdx.input.getX() - SCREEN_BUILDING_SIZE / 2;
            previewY = Gdx.graphics.getHeight() - Gdx.input.getY() - SCREEN_BUILDING_SIZE / 2;
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

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && justClickedButton){
            justClickedButton = false;
        }
        // Check for left mouse click to place the texture
        else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && selectedTexture != null && checkCollisions()) {
            
            //placedBuildings.add(new AcademicBuilding(selectedTexture, previewX, previewY));
            
            switch (buildingType) {
                    case ACADEMIC:
                        placedBuildings.add(new AcademicBuilding(selectedTexture, previewX, previewY));
                        break;
                
                    default:
                        placedBuildings.add(new AcademicBuilding(selectedTexture, previewX, previewY));
                        break;
                }
                
            isPreviewing = false;
            selectedTexture = null;
        }
    }


    public void selectBuilding(String buildingID, BuildingType buildingType){

        justClickedButton = true;
        isPreviewing = true;
        this.buildingType = buildingType;


        switch (buildingID) {
            case "piazza":
                selectedTexture = building1;
                break;
        
            default:
                selectedTexture = building1;
                break;
        }
        
        


    }

    private boolean checkCollisions(){

        return true;
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    public void hide() {
        Gdx.input.setInputProcessor(null);
    }


    public void dispose() {
        stage.dispose();
        batch.dispose();
        textureAtlas.dispose();
    }

}
