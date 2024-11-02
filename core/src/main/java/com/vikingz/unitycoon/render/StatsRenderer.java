package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.util.TimeUtil;

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
        font.draw(batch, "Balance: " + GameGlobals.BALANCE, 10, GameConfig.getInstance().getWindowHeight() - 30); // Draw at position (10, 470)
        font.draw(batch, "Students: " + GameGlobals.STUDENTS, 10, GameConfig.getInstance().getWindowHeight() - 60); // Draw at position (10, 470)
        font.draw(batch, "Satisfaction: " + GameGlobals.SATISFACTION, 10, GameConfig.getInstance().getWindowHeight() - 90); // Draw at position (10, 470)
        font.draw(batch, "Number Of Buildings: " + GameGlobals.BUILDINGS_COUNT, 10, GameConfig.getInstance().getWindowHeight() -120); // Draw at position (10, 470)
        
        TimeUtil.Time timer = TimeUtil.secondsToMinSecs(GameGlobals.ELAPSED_TIME);
        font.draw(batch, "Time: " + timer, 10, GameConfig.getInstance().getWindowHeight() -150); // Draw at position (10, 470)

        batch.end();
    }


}
