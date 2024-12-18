package com.vikingz.unitycoon.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.BuildingRenderer;

import static com.vikingz.unitycoon.building.BuildingStats.BuildingCoinDict;
import static com.vikingz.unitycoon.building.BuildingStats.BuildingSatisfactionDict;
import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.*;


/**
 * This class is what creates the build menu in the game.
 *
 * It contains a single constructor that takes a Skin, BuildingRenderer
 * and a Stage as parameters to create the Building Menu.
 *
 * This class also creates the 4 buttons at the bottom of the game screen
 *  by which the build menu is accessed
 */
public class BuildMenu{
    //renders buildings and handles placing them
    private final BuildingRenderer buildingRenderer;

    //the stage where the menu will be added to
    private final Stage stage;

    //The skin of the window
    private final Skin skin;

    //Determines if there is already an active window open
    private boolean windowActive = false;

    //Allows BuildMenu to be placed in the center
    private int width = GameConfig.getInstance().getWindowWidth();
    private int height = GameConfig.getInstance().getWindowHeight();

    //Current displayed in game Menu
    private Window currentMenu;

    /**
     * Creates a new BuildMenu
     * @param skin SKin of the buttons on the menu
     * @param buildingRenderer  BuildingRenderer instance that renders the buildings in the game
     * @param stage The stage on which the menu is drawn
     */
    public BuildMenu(Skin skin, BuildingRenderer buildingRenderer, Stage stage) {


        this.stage = stage;
        this.buildingRenderer =  buildingRenderer;
        this.skin = skin;

        //Texture atlas of building menu bar
        Texture textureAtlas = new Texture(Gdx.files.internal("textureAtlases/buildMenuButtonsAtlas.png")); // Load your 64x64 PNG

        //Sets the pixel size of tiles used for build menu bar
        int atlasTileSize = 64;
        TextureRegion btn1Texture = new TextureRegion(textureAtlas, 0, 0, atlasTileSize, atlasTileSize);
        TextureRegion btn2Texture = new TextureRegion(textureAtlas, atlasTileSize, 0, atlasTileSize, atlasTileSize);
        TextureRegion btn3Texture = new TextureRegion(textureAtlas, atlasTileSize * 2, 0, atlasTileSize, atlasTileSize);
        TextureRegion btn4Texture = new TextureRegion(textureAtlas, atlasTileSize * 3, 0, atlasTileSize, atlasTileSize);

        TextureRegion btn1Texture_hover = new TextureRegion(textureAtlas, 0, atlasTileSize, atlasTileSize, atlasTileSize);
        TextureRegion btn2Texture_hover = new TextureRegion(textureAtlas, atlasTileSize, atlasTileSize, atlasTileSize, atlasTileSize);
        TextureRegion btn3Texture_hover = new TextureRegion(textureAtlas, atlasTileSize * 2, atlasTileSize, atlasTileSize, atlasTileSize);
        TextureRegion btn4Texture_hover = new TextureRegion(textureAtlas, atlasTileSize * 3, atlasTileSize, atlasTileSize, atlasTileSize);

        // Create ImageButtons
        ImageButton btn1 = new ImageButton(new ImageButton.ImageButtonStyle());
        btn1.getStyle().imageUp = new TextureRegionDrawable(btn1Texture);
        btn1.getStyle().imageOver = new TextureRegionDrawable(btn1Texture_hover);

        ImageButton btn2 = new ImageButton(new ImageButton.ImageButtonStyle());
        btn2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(btn2Texture));
        btn2.getStyle().imageOver = new TextureRegionDrawable(new TextureRegion(btn2Texture_hover));

        ImageButton btn3 = new ImageButton(new ImageButton.ImageButtonStyle());
        btn3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(btn3Texture));
        btn3.getStyle().imageOver = new TextureRegionDrawable(new TextureRegion(btn3Texture_hover));

        ImageButton btn4 = new ImageButton(new ImageButton.ImageButtonStyle());
        btn4.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(btn4Texture));
        btn4.getStyle().imageOver = new TextureRegionDrawable(new TextureRegion(btn4Texture_hover));


        // Table for layout
        Table table = new Table();
        table.setFillParent(true);
        table.bottom().center();
        table.bottom();

        // Add buttons to table
        table.add(btn1).pad(10);
        table.add(btn2).pad(10);
        table.add(btn3).pad(10);
        table.add(btn4).pad(10);

        // Add table to stage
        stage.addActor(table);

        // Set up click listeners for buttons
        btn1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(currentMenu != null) { currentMenu.remove(); }
                showMenu(ACADEMIC);
            }
        });

        btn2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(currentMenu != null) { currentMenu.remove(); }

                showMenu(ACCOMODATION);
            }
        });

        btn3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(currentMenu != null) { currentMenu.remove(); }

                showMenu(RECREATIONAL);
            }
        });

        btn4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(currentMenu != null) { currentMenu.remove(); }

                showMenu(FOOD);
            }
        });


    }

    //Selects which building of BuildingType,
    //should be displayed currently
    private int index = 0;
    Label buildingNameLabel;
    Image buildingImage;
    Label buildingPriceValue;
    Label buildingSatisfactionValue;
    Label buildingStudentsValue;
    Label buildingCoinsValue;

    /**
     * Creates a new window and sets up all the contents of the window
     * so that when the user presses one of the buttons at the bottom of the game
     * screen the corresponding menu is shown.
     * @param buildingType contains Type of building from BuildingStats
     */
    private void showMenu(BuildingStats.BuildingType buildingType) {
        // Create a window (menu)
        index = 0;

        Window window = new Window("Build Menu", skin);
        window.getTitleTable().padTop(25).padLeft(437);
        this.currentMenu = window;
        window.setModal(true);
        window.setMovable(false);
        window.setResizable(false);
        window.setBackground(GameGlobals.backGroundDrawable);

        // Building name Label
        buildingNameLabel = new Label("", skin);

        // Image Of Building
        buildingImage = new Image();

        Table statsTable = new Table();

        // Price
        Label buildingPriceLabel = new Label("Price:",skin);
        buildingPriceValue = new Label("",skin);
        statsTable.add(buildingPriceLabel);
        statsTable.add(buildingPriceValue).row();

        // Satisfaction
        Label buildingSatisfactionLabel = new Label("Satisfaction:", skin);
        buildingSatisfactionValue = new Label("",skin);
        statsTable.add(buildingSatisfactionLabel);
        statsTable.add(buildingSatisfactionValue).row();

        // Students
        Label buildingStudentsLabel = new Label("Student Space:", skin);
        buildingStudentsValue = new Label("",skin);
        statsTable.add(buildingStudentsLabel);
        statsTable.add(buildingStudentsValue).row();

        // Coins
        Label buildingCoinsLabel = new Label("Coins Per Second:",skin);
        buildingCoinsValue = new Label("", skin);
        statsTable.add(buildingCoinsLabel);
        statsTable.add(buildingCoinsValue).row();

        //Back Building Button
        TextButton backButton = new TextButton("<", skin);
//        backButton.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                try {
//                    index--;
//                    SetLabelText(buildingNameLabel, buildingType, buildingPriceValue, buildingSatisfactionValue, buildingStudentValue, buildingCoinsValue, buildingImage);
//                }
//                catch (ArrayIndexOutOfBoundsException e){
//                    index = BuildingStats.BuildingNameDict.get(buildingType).length-1;
//                    SetLabelText(buildingNameLabel, buildingType, buildingPriceValue, buildingSatisfactionValue, buildingStudentValue, buildingCoinsValue, buildingImage);
//                }
//            }
//        });

        //Next Building Button
        TextButton nextButton = new TextButton(">", skin);
//        nextButton.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                try {
//                    index++;
//                    SetLabelText(buildingNameLabel, buildingType, buildingPriceValue, buildingSatisfactionValue, buildingStudentValue, buildingCoinsValue, buildingImage);
//                }
//                catch (ArrayIndexOutOfBoundsException e){
//                    index = 0;
//                    SetLabelText(buildingNameLabel, buildingType, buildingPriceValue, buildingSatisfactionValue, buildingStudentValue, buildingCoinsValue, buildingImage);
//                }
//            }
//        });

        // Create the Buy Button
        TextButton buyButton = new TextButton("Buy", skin);
        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buildingRenderer.selectBuilding(buildingType,index);
                window.remove();
            }
        });

        // Create the close button
        TextButton closeButton = new TextButton("Close", skin);

        closeButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                setWindowActive(false);
                window.remove();  // Remove window from the stage
            }
        });

        updateBuildingWindow(buildingType);

        window.add(buildingNameLabel).colspan(3).row();
        window.add(backButton);
        window.add(buildingImage);
        window.add(nextButton).row();
        window.add(statsTable).colspan(3).expandX().row();
        window.add(buyButton).colspan(3).row();
        window.add(closeButton).colspan(3);

        // Set size and position of the window
        window.setSize(1000, 800);
        window.setPosition((stage.getWidth() - window.getWidth()) / 2, (stage.getHeight() - window.getHeight()) / 2);

        // Add window to the stage
        stage.addActor(window);

//        window.debugAll(); // todo remove, for debugging only
    }

    /**
     * Sets the text of each label to current index
     * @param buildingType Type of Building used, for dictionary lookup
     */
    private void updateBuildingWindow(BuildingStats.BuildingType buildingType) {
        buildingNameLabel.setText(BuildingStats.BuildingNameDict.get(buildingType)[index]);
        buildingImage.setDrawable(BuildingStats.getTextureDrawableOfBuilding(BuildingStats.BuildingDict.get(buildingType)[index]));
        buildingPriceValue.setText(BuildingStats.BuildingPriceDict.get(buildingType)[index]);
        buildingSatisfactionValue.setText(BuildingSatisfactionDict.get(buildingType)[index]);
        buildingStudentsValue.setText(BuildingStats.BuildingStudentDict.get(buildingType)[index]);
        buildingCoinsValue.setText(BuildingCoinDict.get(buildingType)[index]);
    }

    /**
     * returns if the window is currently open
     * @return windowActive boolean
     */
    public boolean isWindowActive() {
        return windowActive;
    }


    /**
     * Sets the windowActive, used when the menu is opened or closed,
     * to prevent two windows being opened at same time.
     * @param windowActive boolean
     */
    public void setWindowActive(boolean windowActive) {
        this.windowActive = windowActive;
    }

    /**
     * BuildingMenu render actors objects
     * @param delta
     */
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    /**
     * Called when the window resizes
     * @param width New width
     * @param height New height
     */
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        this.width = width;
        this.height = height;
    }

    /**
     * Disposes of the build menu
     */
    public void dispose() {
        stage.dispose();

    }
}
