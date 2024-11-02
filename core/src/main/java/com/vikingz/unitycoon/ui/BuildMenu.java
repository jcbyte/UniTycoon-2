package com.vikingz.unitycoon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.global.GameSkins;
import com.vikingz.unitycoon.render.BuildingRenderer;

import java.util.Dictionary;
import java.util.Hashtable;

import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.*;


public class BuildMenu{
    private final BuildingRenderer buildingRenderer;
    private Stage stage;
    private Skin skin;
    private Texture textureAtlas;
    private boolean windowActive = false;
    private int atlasTileSize = 64;

    private int MENU_WINDOW_WIDTH = 1000;
    private int MENU_WINDOW_HEIGHT = 800;

    private Window currentMenu;


    //Temp
    private Dictionary<BuildingStats.BuildingType, String[]> BuildingNameDict = BuildingStats.BuildingNameDict;
    private Dictionary<BuildingStats.BuildingType, String[]> BuildingPriceDict = BuildingStats.BuildingPriceDict;
    private Dictionary<BuildingStats.BuildingType, String[]> BuildingStudentDict = BuildingStats.BuildingStudentDict;
    private Dictionary<BuildingStats.BuildingType, BuildingStats.BuildingID[]> BuildingDict = BuildingStats.BuildingDict;



    public int index = 0;


    public BuildMenu(GameSkins SkinLoader, BuildingRenderer buildingRenderer, Stage stage) {


        //stage = new Stage(new ScreenViewport());

        this.stage = stage;
        //Sets input for LIBGDX ui system to this ui
        //Gdx.input.setInputProcessor(stage);

        this.buildingRenderer =  buildingRenderer;

        //Imports skins
        skin = SkinLoader.getQuantumSkin();


        textureAtlas = new Texture(Gdx.files.internal("textureAtlases/buildMenuButtonsAtlas.png")); // Load your 64x64 PNG

        TextureRegion btn1Texture = new TextureRegion(textureAtlas, 0, 0,                     atlasTileSize, atlasTileSize);
        TextureRegion btn2Texture = new TextureRegion(textureAtlas, atlasTileSize, 0,            atlasTileSize, atlasTileSize);
        TextureRegion btn3Texture = new TextureRegion(textureAtlas, atlasTileSize * 2, 0,    atlasTileSize, atlasTileSize);
        TextureRegion btn4Texture = new TextureRegion(textureAtlas, atlasTileSize * 3, 0,         atlasTileSize, atlasTileSize);
        TextureRegion btn5Texture = new TextureRegion(textureAtlas, atlasTileSize * 4, 0,         atlasTileSize, atlasTileSize);


        // Create ImageButtons
        ImageButton btn1 = new ImageButton(new ImageButton.ImageButtonStyle());
        btn1.getStyle().imageUp = new TextureRegionDrawable(btn1Texture);

        ImageButton btn2 = new ImageButton(new ImageButton.ImageButtonStyle());
        btn2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(btn2Texture));

        ImageButton btn3 = new ImageButton(new ImageButton.ImageButtonStyle());
        btn3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(btn3Texture));

        ImageButton btn4 = new ImageButton(new ImageButton.ImageButtonStyle());
        btn4.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(btn4Texture));

        ImageButton btn5 = new ImageButton(new ImageButton.ImageButtonStyle());
        btn5.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(btn5Texture));

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
        table.add(btn5).pad(10);

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

        btn5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(currentMenu != null) { currentMenu.remove(); }


                showMenu(BuildingStats.BuildingType.NONE);
            }
        });
    }
    private void CreateButtons(BuildingStats.BuildingType buildingType, Window window ){
        BuildingNameDict.put(ACADEMIC, new String[]{"Ron Cooke","Piazza"});
        BuildingNameDict.put(ACCOMODATION, new String[]{"David Kato","Anne Lister"});
        BuildingNameDict.put(RECREATIONAL, new String[]{"YSV"});
        BuildingNameDict.put(FOOD, new String[]{"McD"});
        BuildingNameDict.put(NONE, new String[]{"NONE"});

        BuildingPriceDict.put(ACADEMIC, new String[]{"100","150"});
        BuildingPriceDict.put(ACCOMODATION, new String[]{"100","100"});
        BuildingPriceDict.put(RECREATIONAL, new String[]{"200"});
        BuildingPriceDict.put(FOOD, new String[]{"200"});
        BuildingPriceDict.put(NONE, new String[]{"NONE"});

        BuildingStudentDict.put(ACADEMIC, new String[]{"Satisfaction: 0.5 students/second","Satisfaction: 0.8 students/second"});
        BuildingStudentDict.put(ACCOMODATION, new String[]{"Students: +100","Students: +150"});
        BuildingStudentDict.put(RECREATIONAL, new String[]{"Satisfaction: 0.5 students/second"});
        BuildingStudentDict.put(FOOD, new String[]{"Satisfaction: 0.5 students/second"});
        BuildingStudentDict.put(NONE, new String[]{"NONE"});

        BuildingDict.put(ACADEMIC,new BuildingStats.BuildingID[]{BuildingStats.BuildingID.RCH, BuildingStats.BuildingID.PZA});
        BuildingDict.put(ACCOMODATION,new BuildingStats.BuildingID[]{BuildingStats.BuildingID.KATO, BuildingStats.BuildingID.LISTER});
        BuildingDict.put(RECREATIONAL,new BuildingStats.BuildingID[]{BuildingStats.BuildingID.YSV});
        BuildingDict.put(FOOD,new BuildingStats.BuildingID[]{BuildingStats.BuildingID.MCD});
        BuildingDict.put(NONE,new BuildingStats.BuildingID[]{null});

        /*
        switch (buildingType) {
            case ACADEMIC:

                addMenuBtnForABuilding(window, "[Ron Cooke] [Price: 100] [Satisfaciton: 0.5/student/second]", BuildingStats.BuildingID.RCH);
                addMenuBtnForABuilding(window, "[Piazza]    [Price: 150] [Satisfaciton: 0.8/student/second]", BuildingStats.BuildingID.PZA);
                break;

            case ACCOMODATION:
                addMenuBtnForABuilding(window, "[David Kato]  [Price: 100] [Students: +100]", BuildingStats.BuildingID.KATO);
                addMenuBtnForABuilding(window, "[Anne Lister] [Price: 100] [Students: +150]", BuildingStats.BuildingID.LISTER);
                break;

            case RECREATIONAL:
                addMenuBtnForABuilding(window, "[YSV]  [Price: 200] [Satisfaciton: 0.5/student/second]", BuildingStats.BuildingID.YSV);
                break;

            case FOOD:
                addMenuBtnForABuilding(window, "[McD]  [Price: 200] [Satisfaciton: 0.5/student/second]", BuildingStats.BuildingID.MCD);
                break;

            case NONE:

                break;

            default:
                break;
        }
        */
    }


    private void showMenu(BuildingStats.BuildingType buildingType) {
        // Create a window (menu)
        index = 0;
        Window window = new Window("Build Menu", skin);
        this.currentMenu = window;
        window.setMovable(false);

        //Create buttons on menu load
        //CreateButtons(buildingType, window);



        Label buildingNameLabel = new Label(BuildingNameDict.get(buildingType)[0], skin);
        System.out.println(BuildingNameDict);


        //Building UI

        //Building name Label
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
        window.add(backButton);

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
        window.add(nextButton);


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
        window.setPosition(Gdx.graphics.getWidth() / 2f - (MENU_WINDOW_WIDTH / 2), Gdx.graphics.getHeight() / 2f - (MENU_WINDOW_HEIGHT / 2));

        //shows debug lines of window table
        //window.setDebug(true);
        // Add window to the stage
        stage.addActor(window);
    }


    private void addMenuBtnForABuilding(Window window, String btnText, BuildingStats.BuildingID buildingID){

        TextButton button = new TextButton(btnText, skin);
        button.setSize(100, 30);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buildingRenderer.selectBuilding(buildingID);
                window.remove();
            }
        });
        window.row().padTop(10);
        window.add(button);

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
    }

    public void dispose() {
        stage.dispose();

    }
}
