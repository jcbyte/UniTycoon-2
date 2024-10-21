package com.vikingz.unitycoon.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameGlobals;

public class StatsRenderer {

    private SpriteBatch batch;
    private BitmapFont font;


    // Background

    public StatsRenderer() {

        batch = new SpriteBatch();

        // Load the font directly without AssetManager
        //font = new BitmapFont(Gdx.files.internal("ui/font-big-export.fnt"));


        font = new BitmapFont(); // Create a new BitmapFont (consider loading a specific font if needed)
        font.getData().setScale(1.5f);

    }

    public void show() {
        // Initialize game objects here
    }

    public void render(float delta) {

        batch.begin();

        font.draw(batch, "Balance: " + GameGlobals.BALANCE, 10, GameConfig.getInstance().getWindowHeight() - 10); // Draw at position (10, 470)
        font.draw(batch, "Students: " + GameGlobals.STUDENTS, 10, GameConfig.getInstance().getWindowHeight() - 40); // Draw at position (10, 470)
        font.draw(batch, "Satisfaction: " + GameGlobals.SATISFACTION, 10, GameConfig.getInstance().getWindowHeight() - 70); // Draw at position (10, 470)

        font.draw(batch, "Number Of Buildings: " + GameGlobals.BUILDINGS_COUNT, 10, GameConfig.getInstance().getWindowHeight() - 120); // Draw at position (10, 470)

        batch.end();
    }


}
