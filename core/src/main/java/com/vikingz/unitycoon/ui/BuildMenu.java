package com.vikingz.unitycoon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class BuildMenu{
    private Stage stage;
    private Skin skin;
    private Texture textureAtlas;
    private int atlasTileSize = 64;

    private int WINDOW_WIDTH = 500;
    private int WINDOW_HEIGHT = 700;

    public BuildMenu() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json")); // Default UI skin


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
                showMenu("1");
            }
        });

        btn2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showMenu("2");
            }
        });

        btn3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showMenu("3");
            }
        });

        btn4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showMenu("4");
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
            "Building 1", "Building 2"
        };

        // Iterate through the list of button texts and create buttons
        for (String text : buttonTexts) {
            // Create a new button with the text
            TextButton button = new TextButton(text, skin);

            // Set a smaller size for the buttons
            button.setSize(50, 15); // Set width and height
            button.scaleBy(-1.5f);

            // Add listener to print text when the button is clicked
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println(text + " clicked!");  // Print the text to the console
                }
            });

            // Add the button to the table
            buttonTable.add(button).pad(5);  // Add padding for spacing
            buttonTable.row();  // Move to the next row after each button
        }

        // Create a ScrollPane to hold the button table
        ScrollPane scrollPane = new ScrollPane(buttonTable, skin);
        scrollPane.setFillParent(true);  // Make the scroll pane fill the available space

        // Add the scroll pane to the window
        window.add(scrollPane).expand().fill(); // Fill the window with the scroll pane

        // Create the close button
        TextButton closeButton = new TextButton("Close", skin);
        closeButton.setSize(100, 30); // Set size for the close button

        // Add listener to close the window when the close button is clicked
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
