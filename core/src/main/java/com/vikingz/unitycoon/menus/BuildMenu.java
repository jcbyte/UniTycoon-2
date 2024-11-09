package com.vikingz.unitycoon.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.BuildingRenderer;

import java.util.Dictionary;

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
    private final BuildingRenderer buildingRenderer;
    private Stage stage;
    private Skin skin;
    private Texture textureAtlas;
    private boolean windowActive = false;
    private int atlasTileSize = 64;
    private int MENU_WINDOW_WIDTH = 1000;
    private int MENU_WINDOW_HEIGHT = 800;

    private int width = GameConfig.getInstance().getWindowWidth();
    private int height = GameConfig.getInstance().getWindowHeight();

    private Window currentMenu;

    //Temp
    private Dictionary<BuildingStats.BuildingType, String[]> BuildingNameDict = BuildingStats.BuildingNameDict;
    private Dictionary<BuildingStats.BuildingType, String[]> BuildingPriceDict = BuildingStats.BuildingPriceDict;
    private Dictionary<BuildingStats.BuildingType, String[]> BuildingStudentDict = BuildingStats.BuildingStudentDict;
    private Dictionary<BuildingStats.BuildingType, BuildingStats.BuildingID[]> BuildingDict = BuildingStats.BuildingDict;
    private int index = 0;

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


        textureAtlas = new Texture(Gdx.files.internal("textureAtlases/buildMenuButtonsAtlas.png")); // Load your 64x64 PNG

        TextureRegion btn1Texture = new TextureRegion(textureAtlas, 0, 0,                     atlasTileSize, atlasTileSize);
        TextureRegion btn2Texture = new TextureRegion(textureAtlas, atlasTileSize, 0,            atlasTileSize, atlasTileSize);
        TextureRegion btn3Texture = new TextureRegion(textureAtlas, atlasTileSize * 2, 0,    atlasTileSize, atlasTileSize);
        TextureRegion btn4Texture = new TextureRegion(textureAtlas, atlasTileSize * 3, 0,         atlasTileSize, atlasTileSize);

        TextureRegion btn1Texture_hover = new TextureRegion(textureAtlas, 0, atlasTileSize,                     atlasTileSize, atlasTileSize);
        TextureRegion btn2Texture_hover = new TextureRegion(textureAtlas, atlasTileSize, atlasTileSize,            atlasTileSize, atlasTileSize);
        TextureRegion btn3Texture_hover = new TextureRegion(textureAtlas, atlasTileSize * 2, atlasTileSize,    atlasTileSize, atlasTileSize);
        TextureRegion btn4Texture_hover = new TextureRegion(textureAtlas, atlasTileSize * 3, atlasTileSize,         atlasTileSize, atlasTileSize);

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

                showMenu(BuildingStats.BuildingType.ACCOMODATION);
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



    private void showMenu(BuildingStats.BuildingType buildingType) {
        // Create a window (menu)
        index = 0;

        Window window = new Window("Build Menu", skin);
        window.getTitleTable().padTop(25).padLeft(437);
        this.currentMenu = window;
        window.setMovable(false);
        window.setBackground(GameGlobals.backGroundDrawable);

        //Create buttons on menu load
        //CreateButtons(buildingType, window);


        //Building UI

        //Building name Label
        Label buildingNameLabel = new Label(BuildingNameDict.get(buildingType)[0], skin);
        window.add((Actor) null);
        window.add(buildingNameLabel);
        window.row().padTop(10);

        //Image Of Building
        window.add((Actor) null);
        Image buildingImage = new Image(BuildingStats.getTextureOfBuilding(BuildingDict.get(buildingType)[0]));
        window.add(buildingImage);
        window.row().padTop(20);

        //satisfaction Label
        window.add((Actor) null);
        Label buildingSatisfaction = new Label(BuildingStudentDict.get(buildingType)[0],skin);
        window.add(buildingSatisfaction).expandX();
        window.row();

        //Price Label
        window.add((Actor) null);
        Label buildingPrice = new Label("Price: " + BuildingPriceDict.get(buildingType)[0],skin);
        window.add(buildingPrice);

        window.row().padTop(20);



        //Back Building Button
        TextButton backButton = new TextButton("Back", skin);
        backButton.setSize(100, 30); // Set size for the back button
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    index--;
                    buildingNameLabel.setText(BuildingNameDict.get(buildingType)[index]);
                    buildingPrice.setText("Price: " + BuildingPriceDict.get(buildingType)[index]);
                    buildingSatisfaction.setText(BuildingStudentDict.get(buildingType)[index]);
                    buildingImage.setDrawable(BuildingStats.getTextureDrawableOfBuilding((BuildingDict.get(buildingType)[index])));
                }
                catch (ArrayIndexOutOfBoundsException e){
                    index = BuildingNameDict.get(buildingType).length-1;
                    buildingNameLabel.setText(BuildingNameDict.get(buildingType)[index]);
                    buildingPrice.setText("Price: " + BuildingPriceDict.get(buildingType)[index]);
                    buildingSatisfaction.setText(BuildingStudentDict.get(buildingType)[index]);
                    buildingImage.setDrawable(BuildingStats.getTextureDrawableOfBuilding((BuildingDict.get(buildingType)[index])));
                }
            }
        });
        window.add(backButton).padLeft(50);

        // Create the Buy Button
        TextButton buyButton = new TextButton("Buy", skin);
        buyButton.setSize(100, 30);
        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buildingRenderer.selectBuilding(BuildingDict.get(buildingType)[index]);
                window.remove();
            }
        });
        window.add(buyButton);


        //Next Building Button
        TextButton nextButton = new TextButton("Next", skin);
        nextButton.setSize(100, 30); // Set size for the next button
        nextButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    index++;
                    buildingNameLabel.setText(BuildingNameDict.get(buildingType)[index]);
                    buildingPrice.setText("Price: " + BuildingPriceDict.get(buildingType)[index]);
                    buildingSatisfaction.setText(BuildingStudentDict.get(buildingType)[index]);
                    buildingImage.setDrawable(BuildingStats.getTextureDrawableOfBuilding((BuildingDict.get(buildingType)[index])));
                }
                catch (ArrayIndexOutOfBoundsException e){
                    index = 0;
                    buildingNameLabel.setText(BuildingNameDict.get(buildingType)[index]);
                    buildingPrice.setText("Price: " + BuildingPriceDict.get(buildingType)[index]);
                    buildingSatisfaction.setText(BuildingStudentDict.get(buildingType)[index]);
                    buildingImage.setDrawable(BuildingStats.getTextureDrawableOfBuilding((BuildingDict.get(buildingType)[index])));
                }
            }
        });
        window.add(nextButton).padRight(50);


        // Create the close button
        TextButton closeButton = new TextButton("Close", skin);
        closeButton.setSize(100, 30); // Set size for the close button

        closeButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                setWindowActive(false);
                window.remove();  // Remove window from the stage
            }
        });

        // Add close button to the window
        window.row().padTop(10); // Add a row before adding the close button
        System.out.println(window.getColumns());
        window.add((Actor) null);
        window.add(closeButton);

        // Set size and position of the window
        window.setSize(MENU_WINDOW_WIDTH, MENU_WINDOW_HEIGHT);
        window.setPosition(this.width / 2f - (MENU_WINDOW_WIDTH / 2), this.height / 2f - (MENU_WINDOW_HEIGHT / 2));

        //shows debug lines of window table
        //window.setDebug(true);
        // Add window to the stage
        stage.addActor(window);

    }

    public boolean isWindowActive() {
        return windowActive;
    }

    
    public void setWindowActive(boolean windowActive) {
        this.windowActive = windowActive;
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        this.width = width;
        this.height = height;
    }

    public void dispose() {
        stage.dispose();

    }
}
