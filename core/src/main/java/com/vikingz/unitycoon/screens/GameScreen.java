package com.vikingz.unitycoon.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.global.GameSkins;
import com.vikingz.unitycoon.render.BackgroundRenderer;
import com.vikingz.unitycoon.render.BuildingRenderer;
import com.vikingz.unitycoon.render.StatsRenderer;
import com.vikingz.unitycoon.ui.BuildMenu;
import com.vikingz.unitycoon.util.StatsCalculator;

public class GameScreen implements Screen {

    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private String mapName;

    // Counter variables
    private float elapsedTime;

    // Font to display counter
    private BitmapFont font;

    // Renderers
    private BackgroundRenderer backgroundRenderer;
    private StatsRenderer statsRenderer;
    private BuildingRenderer buildingRenderer;
    private Viewport viewPort;

    // Menus
    private BuildMenu buildMenu;





    public GameScreen(Game game, String mapName, GameSkins SkinLoader) {

        this.game = game;
        this.mapName = mapName;

        this.viewPort = new FitViewport(GameConfig.getInstance().getWindowWidth(), GameConfig.getInstance().getWindowHeight());

        camera = new OrthographicCamera();
        backgroundRenderer = new BackgroundRenderer(mapName);
        statsRenderer = new StatsRenderer();
        buildingRenderer = new BuildingRenderer();
        buildMenu = new BuildMenu(SkinLoader, buildingRenderer);
        batch = new SpriteBatch();


        // Initialize counter and font
        elapsedTime = 0;

        font = new BitmapFont(); // Create a new BitmapFont (consider loading a specific font if needed)
        font.getData().setScale(2.0f);

    }

    @Override
    public void show() {
        // Initialize game objects here
    }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the camera
        camera.update();


        backgroundRenderer.render(delta);

        // Update the counter
        elapsedTime += delta; // delta is the time elapsed since the last frame
        if (elapsedTime >= 1) { // Increment counter every second

            // Calculate Game Stats

            GameGlobals.BALANCE++;
            GameGlobals.SATISFACTION += StatsCalculator.calculateSatisfaction(GameGlobals.STUDENTS, 0.5f);





            elapsedTime = 0; // Reset elapsed time
        }

        //System.out.println((Gdx.input.getY()));
        //System.out.println((Gdx.input.getX()));


        // Draw game objects
        batch.setProjectionMatrix(camera.combined);
        batch.begin();




        statsRenderer.render(delta);
        buildingRenderer.render(delta);
        buildMenu.render(delta);
        batch.end();
    }



    @Override
    public void resize(int width, int height) {
        // Adjust the viewport when the window size changes

        buildMenu.resize(width, height);
        viewPort.update(width, height);

        //camera.setToOrtho(false, width, height);

        //backgroundRenderer.resize(width, height);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        // Dispose of resources
        batch.dispose();
        font.dispose(); // Dispose the font
    }
}
