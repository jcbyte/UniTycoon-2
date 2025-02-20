package com.vikingz.unitycoon.render.menus;

import static com.vikingz.unitycoon.building.BuildingStats.BuildingCoinDict;
import static com.vikingz.unitycoon.building.BuildingStats.BuildingSatisfactionDict;
import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.ACADEMIC;
import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.ACCOMMODATION;
import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.FOOD;
import static com.vikingz.unitycoon.building.BuildingStats.BuildingType.RECREATIONAL;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.vikingz.unitycoon.building.BuildingStats;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.BuildingRenderer;


/**
 * This class is what creates the build menu in the game.
 *
 * <p>It contains a single constructor that takes a Skin, BuildingRenderer and a Stage as
 * parameters to create the Building Menu.
 *
 * <p>This class also creates the 4 buttons at the bottom of the game screen by which the build
 * menu is accessed.
 */
public class BuildMenu {
  // Renders buildings and handles placing them
  private final BuildingRenderer buildingRenderer;

  // The stage where the menu will be added to
  private final Stage stage;

  // The skin of the window
  private final Skin skin;

  // Current displayed in game Menu
  private Window currentMenu;

  // Building type counters
  private final Label academicBuildingsCount;
  private final Label accommodationBuildingsCount;
  private final Label recreationalBuildingsCount;
  private final Label foodBuildingsCount;

  /**
   * Creates a new BuildMenu.
   *
   * @param skin             Skin of the buttons on the menu
   * @param buildingRenderer BuildingRenderer instance that renders the buildings in the game
   * @param stage            The stage on which the menu is drawn
   */
  public BuildMenu(Skin skin, BuildingRenderer buildingRenderer, Stage stage) {
    this.stage = stage;
    this.buildingRenderer = buildingRenderer;
    this.skin = skin;

    // Building count labels
    academicBuildingsCount = new Label("0", skin);
    academicBuildingsCount.setColor(Color.BLACK);
    academicBuildingsCount.setFontScale(1.8f);

    accommodationBuildingsCount = new Label("0", skin);
    accommodationBuildingsCount.setColor(Color.BLACK);
    accommodationBuildingsCount.setFontScale(1.8f);

    recreationalBuildingsCount = new Label("0", skin);
    recreationalBuildingsCount.setColor(Color.BLACK);
    recreationalBuildingsCount.setFontScale(1.8f);

    foodBuildingsCount = new Label("0", skin);
    foodBuildingsCount.setColor(Color.BLACK);
    foodBuildingsCount.setFontScale(1.8f);

    //Texture atlas of building menu bar
    // Load your 64x64 PNG
    Texture textureAtlas = new Texture(
        Gdx.files.internal("textures/textureAtlases/buildMenuButtonsAtlas.png"));

    //Sets the pixel size of tiles used for build menu bar
    int atlasTileSize = 64;

    // Create ImageButtons
    TextureRegion academicBtnTexture = new TextureRegion(textureAtlas,
        0, 0,
        atlasTileSize, atlasTileSize
    );
    TextureRegion academicBtnTextureHover = new TextureRegion(textureAtlas,
        0, atlasTileSize,
        atlasTileSize, atlasTileSize
    );
    ImageButton academicBtn = new ImageButton(new ImageButton.ImageButtonStyle());
    academicBtn.getStyle().imageUp = new TextureRegionDrawable(academicBtnTexture);
    academicBtn.getStyle().imageOver = new TextureRegionDrawable(academicBtnTextureHover);

    TextureRegion accommodationTexture = new TextureRegion(textureAtlas,
        atlasTileSize, 0,
        atlasTileSize, atlasTileSize
    );
    TextureRegion accommodationTextureHover = new TextureRegion(textureAtlas,
        atlasTileSize, atlasTileSize,
        atlasTileSize, atlasTileSize
    );
    ImageButton accommodationBtn = new ImageButton(new ImageButton.ImageButtonStyle());
    accommodationBtn.getStyle().imageUp = new TextureRegionDrawable(accommodationTexture);
    accommodationBtn.getStyle().imageOver = new TextureRegionDrawable(accommodationTextureHover);

    TextureRegion recreationalBtnTexture = new TextureRegion(textureAtlas,
        atlasTileSize * 2, 0,
        atlasTileSize, atlasTileSize
    );
    TextureRegion recreationalBtnTextureHover = new TextureRegion(textureAtlas,
        atlasTileSize * 2, atlasTileSize,
        atlasTileSize, atlasTileSize
    );
    ImageButton recreationalBtn = new ImageButton(new ImageButton.ImageButtonStyle());
    recreationalBtn.getStyle().imageUp = new TextureRegionDrawable(recreationalBtnTexture);
    recreationalBtn.getStyle().imageOver = new TextureRegionDrawable(recreationalBtnTextureHover);

    TextureRegion foodBtnTexture = new TextureRegion(textureAtlas,
        atlasTileSize * 3, 0,
        atlasTileSize, atlasTileSize
    );
    TextureRegion foodBtnTextureHover = new TextureRegion(textureAtlas,
        atlasTileSize * 3, atlasTileSize,
        atlasTileSize, atlasTileSize
    );
    ImageButton foodBtn = new ImageButton(new ImageButton.ImageButtonStyle());
    foodBtn.getStyle().imageUp = new TextureRegionDrawable(foodBtnTexture);
    foodBtn.getStyle().imageOver = new TextureRegionDrawable(foodBtnTextureHover);

    // Building category labels
    Label acedemicBuildingsLabel = new Label("Academic", skin);
    acedemicBuildingsLabel.setColor(Color.BLACK);
    acedemicBuildingsLabel.setFontScale(1.2f);

    Label accommodationBuildingsLabel = new Label("Accom", skin);
    accommodationBuildingsLabel.setColor(Color.BLACK);
    accommodationBuildingsLabel.setFontScale(1.2f);

    Label recreationalBuildingsLabel = new Label("Recreational", skin);
    recreationalBuildingsLabel.setColor(Color.BLACK);
    recreationalBuildingsLabel.setFontScale(1.2f);

    Label foodBuildingsLabel = new Label("Food", skin);
    foodBuildingsLabel.setColor(Color.BLACK);
    foodBuildingsLabel.setFontScale(1.2f);

    // Table for layout
    Table table = new Table();
    table.setFillParent(true);
    table.bottom();

    // Add count labels to table
    table.add(academicBuildingsCount);
    table.add(accommodationBuildingsCount);
    table.add(recreationalBuildingsCount);
    table.add(foodBuildingsCount).row();

    // Add buttons to table
    table.add(academicBtn).pad(5, 0, 5, 0);
    table.add(accommodationBtn).pad(5, 0, 5, 0);
    table.add(recreationalBtn).pad(5, 0, 5, 0);
    table.add(foodBtn).pad(5, 0, 5, 0).row();

    // Add category labels to table
    table.add(acedemicBuildingsLabel).pad(0, 10, 5, 10).uniformX();
    table.add(accommodationBuildingsLabel).pad(0, 10, 5, 10).uniformX();
    table.add(recreationalBuildingsLabel).pad(0, 10, 5, 10).uniformX();
    table.add(foodBuildingsLabel).pad(0, 10, 5, 10).uniformX();

    // Add table to stage
    stage.addActor(table);

    // Set up click listeners for buttons
    // Do not need to `enableAfter` as the user should know that they are opening the build menu
    academicBtn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hideMenu();
        showMenu(ACADEMIC);
      }
    });

    accommodationBtn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hideMenu();
        showMenu(ACCOMMODATION);
      }
    });

    recreationalBtn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hideMenu();
        showMenu(RECREATIONAL);
      }
    });

    foodBtn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hideMenu();
        showMenu(FOOD);
      }
    });
  }

  /**
   * Hide the current menu.
   */
  private void hideMenu() {
    if (currentMenu != null) {
      currentMenu.remove();
    }
  }

  // Variables for the build menu window,
  private int index = 0;
  private BuildingStats.BuildingType buildingType;
  private Label buildingNameLabel;
  private Image buildingImage;
  private Label buildingPriceValue;
  private Label buildingSatisfactionValue;
  private Label buildingStudentsValue;
  private Label buildingCoinsValue;

  /**
   * Creates a new window and sets up all the contents of the window
   * so that when the user presses one of the buttons at the bottom of the game
   * screen the corresponding menu is shown.
   *
   * @param buildingType contains Type of building from BuildingStats
   */
  private void showMenu(BuildingStats.BuildingType buildingType) {
    this.buildingType = buildingType;
    index = 0;

    Window window = new Window("", skin);
    this.currentMenu = window;
    window.setModal(true);
    window.setMovable(false);
    window.setResizable(false);
    window.setBackground(GameGlobals.backGroundDrawable);

    // Building name Label
    buildingNameLabel = new Label("", skin);
    buildingNameLabel.setFontScale(1.8f);

    // Image Of Building
    buildingImage = new Image();

    Table statsTable = new Table();
    statsTable.center();

    // Price
    Label buildingPriceLabel = new Label("Cost:", skin);
    buildingPriceLabel.setFontScale(1.4f);
    buildingPriceValue = new Label("", skin);
    buildingPriceValue.setFontScale(1.4f);
    statsTable.add(buildingPriceLabel).left().padBottom(2).padRight(15);
    statsTable.add(buildingPriceValue).left().row();

    // Satisfaction
    Label buildingSatisfactionLabel = new Label("Satisfaction/s:", skin);
    buildingSatisfactionLabel.setFontScale(1.4f);
    buildingSatisfactionValue = new Label("", skin);
    buildingSatisfactionValue.setFontScale(1.4f);
    statsTable.add(buildingSatisfactionLabel).left().padBottom(2).padRight(15);
    statsTable.add(buildingSatisfactionValue).left().row();

    // Students
    Label buildingStudentsLabel = new Label("Student Capacity:", skin);
    buildingStudentsLabel.setFontScale(1.4f);
    buildingStudentsValue = new Label("", skin);
    buildingStudentsValue.setFontScale(1.4f);
    statsTable.add(buildingStudentsLabel).left().padBottom(2).padRight(15);
    statsTable.add(buildingStudentsValue).left().row();

    // Coins
    Label buildingCoinsLabel = new Label("Coins/s:", skin);
    buildingCoinsLabel.setFontScale(1.4f);
    buildingCoinsValue = new Label("", skin);
    buildingCoinsValue.setFontScale(1.4f);
    statsTable.add(buildingCoinsLabel).left().padBottom(2).padRight(15);
    statsTable.add(buildingCoinsValue).left().row();

    //Back Building Button
    TextButton backButton = new TextButton("<", skin);
    backButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        index = Math.floorMod(index - 1, BuildingStats.BuildingNameDict.get(buildingType).length);
        updateBuildingWindow();
      }
    });

    //Next Building Button
    TextButton nextButton = new TextButton(">", skin);
    nextButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        index = Math.floorMod(index + 1, BuildingStats.BuildingNameDict.get(buildingType).length);
        updateBuildingWindow();
      }
    });

    // Create the Buy Button
    TextButton buyButton = new TextButton("Buy", skin);
    buyButton.getLabel().setFontScale(0.8f);
    buyButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        buildingRenderer.selectBuilding(buildingType, index);
        hideMenu();
      }
    });

    // Create the close button
    TextButton closeButton = new TextButton("Close", skin);
    closeButton.getLabel().setFontScale(0.8f);
    closeButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        hideMenu();
      }
    });

    updateBuildingWindow();

    window.add(buildingNameLabel).colspan(3).padBottom(10).row();
    window.add(backButton).size(100, 100);
    window.add(buildingImage).size(200, 200).padRight(25).padLeft(25);
    window.add(nextButton).size(100, 100).row();
    window.add(statsTable).colspan(3).padTop(20).padBottom(25).row();
    window.add(buyButton).colspan(3).padBottom(10).row();
    window.add(closeButton).colspan(3);

    // Set size and position of the window
    window.setSize(800, 800);
    window.setPosition(
        (stage.getWidth() - window.getWidth()) / 2,
        (stage.getHeight() - window.getHeight()) / 2
    );

    // Add window to the stage
    stage.addActor(window);

    // Remove current selected building to place
    buildingRenderer.clearSelectedBuilding();
  }

  /**
   * Sets the text of each label to current index.
   */
  private void updateBuildingWindow() {
    buildingNameLabel.setText(BuildingStats.BuildingNameDict.get(buildingType)[index]);
    buildingImage.setDrawable(new TextureRegionDrawable(BuildingStats.getTextureOfBuilding(
        BuildingStats.BuildingDict.get(buildingType)[index])));
    buildingPriceValue.setText(BuildingStats.BuildingPriceDict.get(buildingType)[index]);
    buildingSatisfactionValue.setText(BuildingSatisfactionDict.get(buildingType)[index]);
    buildingStudentsValue.setText(BuildingStats.BuildingStudentDict.get(buildingType)[index]);
    buildingCoinsValue.setText(BuildingCoinDict.get(buildingType)[index]);
  }

  /**
   * BuildingMenu render actors objects.
   */
  public void render(float delta) {
    stage.act(delta);
    stage.draw();

    academicBuildingsCount.setText(GameGlobals.ACADEMIC_BUILDINGS_COUNT);
    accommodationBuildingsCount.setText(GameGlobals.ACCOMMODATION_BUILDINGS_COUNT);
    recreationalBuildingsCount.setText(GameGlobals.RECREATIONAL_BUILDINGS_COUNT);
    foodBuildingsCount.setText(GameGlobals.FOOD_BUILDINGS_COUNT);
  }

  /**
   * Called when on window resize.
   *
   * @param width  New width
   * @param height New height
   */
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  /**
   * Disposes of the build menu.
   */
  public void dispose() {
    stage.dispose();
  }
}
