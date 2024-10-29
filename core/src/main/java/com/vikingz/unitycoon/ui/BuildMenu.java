package com.vikingz.unitycoon.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.vikingz.unitycoon.buildings.BuildingType;
import com.vikingz.unitycoon.global.GameSkins;
import com.vikingz.unitycoon.render.BuildingRenderer;

import java.util.ArrayList;

public class BuildMenu{
    private final BuildingRenderer buildingRenderer;
    private final Skin oldSkin;
    private Stage stage;
    private Skin skin;
    private Skin quantumSkin;
    private Texture textureAtlas;
    private boolean windowActive = false;
    private int atlasTileSize = 64;

    private int WINDOW_WIDTH = 500;
    private int WINDOW_HEIGHT = 700;

    public BuildMenu(GameSkins SkinLoader, BuildingRenderer buildingRenderer) {

        stage = new Stage(new ScreenViewport());
        //Sets input for LIBGDX ui system to this ui
        Gdx.input.setInputProcessor(stage);

        this.buildingRenderer =  buildingRenderer;

        //Imports skins
        oldSkin = SkinLoader.getDefaultSkin();
        skin = SkinLoader.getQuantumSkin();



        textureAtlas = new Texture(Gdx.files.internal("textureAtlases/buildMenuButtonsAtlas.png")); // Load your 64x64 PNG

        TextureRegion btn1Texture = new TextureRegion(textureAtlas, 0, 0,                     atlasTileSize, atlasTileSize);   // Tile 1 (Top-left)
        TextureRegion btn2Texture = new TextureRegion(textureAtlas, atlasTileSize, 0,            atlasTileSize, atlasTileSize);  // Tile 2 (Top-right)
        TextureRegion btn3Texture = new TextureRegion(textureAtlas, atlasTileSize * 2, 0,    atlasTileSize, atlasTileSize);  // Tile 3 (Bottom-left)
        TextureRegion btn4Texture = new TextureRegion(textureAtlas, atlasTileSize * 3, 0,         atlasTileSize, atlasTileSize); // Tile 4 (Bottom-right)
        TextureRegion btn5Texture = new TextureRegion(textureAtlas, atlasTileSize * 4, 0,         atlasTileSize, atlasTileSize); // Tile 4 (Bottom-right)


        // Load textures for buttons (btn1.png to btn5.png)
        //TextureRegion btn1Texture = new Texture(Gdx.files.internal("btnIcons/btn1.png"));
        //Texture btn2Texture = new Texture(Gdx.files.internal("btnIcons/btn2.png"));
        //Texture btn3Texture = new Texture(Gdx.files.internal("btnIcons/btn3.png"));
        //Texture btn4Texture = new Texture(Gdx.files.internal("btnIcons/btn4.png"));
        //Texture btn5Texture = new Texture(Gdx.files.internal("btnIcons/btn5.png"));

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
                showMenu("ACADEMIC");
            }
        });

        btn2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showMenu("ACCOMODATION");
            }
        });

        btn3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showMenu("RECREATIONAL");
            }
        });

        btn4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showMenu("FOOD");
            }
        });

        btn5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showMenu("5");
            }
        });
    }

    private void showMenu(String buttonNumber) {
        // Create a window (menu)
        Window window = new Window("Menu", skin);
        window.setMovable(false);

        // Create a Table to organize buttons
        Table buttonTable = new Table();

        // List of button texts
        String[] buttonTexts = {
            "piazza"
        };
        BuildingType buttonType = BuildingType.valueOf(buttonNumber);
        ArrayList<TextButton> buttonStore = new ArrayList<TextButton>();

        /*
        // Iterate through the list of button texts and create buttons
        for (String text : buttonTexts) {
            // Create a new button with the text
            TextButton button = new TextButton(text, skin);

            // Set a smaller size for the buttons
            button.setSize(50, 15); // Set width and height
            button.scaleBy(0.5f);

            // Add listener to print text when the button is clicked
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println(text + " clicked!");  // Print the text to the console
                }
            });

            // Add the button to the table
            window.add(button).pad(5);  // Add padding for spacing
            window.row();  // Move to the next row after each button
        }
        */

        /*
        // Create a ScrollPane to hold the button table
        ScrollPane scrollPane = new ScrollPane(buttonTable, skin);
        scrollPane.setFillParent(true);  // Make the scroll pane fill the available space

        // Add the scroll pane to the window
        window.add(scrollPane).expand().fill(); // Fill the window with the scroll pane
        */

        //For loop button creation
        for (int i=0;i<buttonTexts.length;i++) {
            //Button Creation
            TextButton testButton = new TextButton(buttonTexts[0], skin); //Creates a new Button
            testButton.setSize(100, 30); // Set size for the close button
            int finalI = i;
            testButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    buildingRenderer.selectBuilding(buttonTexts[finalI], buttonType); // Adds Building Creator
                    setWindowActive(false);
                    window.remove();
                }
            });
            window.row().padTop(10); // Add a row before adding the close button
            window.add(testButton).center(); // Center the close button
            buttonStore.add(testButton); // Stores active button
        }


        // ADDED TO TEST MULTIPLE BUILDINGS

        //Ron cooke hub building
        TextButton rchBtn = new TextButton("Ron Cooke Building", skin);
        rchBtn.setSize(100, 30); // Set size for the close button
        rchBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buildingRenderer.selectBuilding("rch", BuildingType.ACADEMIC);

                // Should close the window after a button in the menu is pressed otherwise the user cant access
                // the canvas behind the menu - Damian
                window.remove();
            }
        });
        window.row().padTop(10); // Add a row before adding the close button
        window.add(rchBtn).center(); // Center the close button





        // Create the close button
        TextButton closeButton = new TextButton("Close", skin);
        closeButton.setSize(100, 30); // Set size for the close button

        // Add listener to close the window when the close button is clicked
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setWindowActive(false);
                window.remove();  // Remove window from the stage
            }
        });

        // Add close button to the window
        window.row().padTop(10); // Add a row before adding the close button
        window.add(closeButton).center(); // Center the close button

        // Set size and position of the window
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setPosition(Gdx.graphics.getWidth() / 2f - (WINDOW_WIDTH / 2), Gdx.graphics.getHeight() / 2f - (WINDOW_HEIGHT / 2));

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
    }

    public void dispose() {
        stage.dispose();

    }
}
