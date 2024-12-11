package com.vikingz.unitycoon.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.vikingz.unitycoon.util.Achievement;
import com.vikingz.unitycoon.util.AchievementsManager;

import java.util.ArrayList;
import java.util.List;

public class AchievementsRenderer {

    private Stage stage;
    private List<Image> achievementLogos;
    private AchievementsManager achievementsManager;


    public AchievementsRenderer(AchievementsManager achievementsManager, Skin skin) {

        stage = new Stage();
        achievementLogos = new ArrayList<>();
        this.achievementsManager = achievementsManager;

        Table container = new Table();
        container.setFillParent(true);
        container.top();
        container.right();

        for (Achievement achievement : achievementsManager.achievements)
        {
            Image achievementLogo = new Image(achievement.logo);
            achievementLogo.setColor(Color.BLACK);
            achievementLogos.add(achievementLogo);
            container.add(achievementLogo).pad(5);
        }

        stage.addActor(container);
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void update()
    {
        for (int i = 0; i < achievementsManager.achievements.length; i++)
        {
            achievementLogos.get(i).setColor(achievementsManager.achievements[i].hasAchieved() ? Color.WHITE : Color.BLACK);
        }
    }

    public void dispose()
    {
        stage.dispose();
    }
}
