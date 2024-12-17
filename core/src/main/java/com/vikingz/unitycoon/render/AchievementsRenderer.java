package com.vikingz.unitycoon.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.vikingz.unitycoon.util.Achievement;
import com.vikingz.unitycoon.util.AchievementsManager;

import java.util.ArrayList;
import java.util.List;

public class AchievementsRenderer {
    final Color DISABLED_COLOUR = new Color(0.3f, 0.3f, 0.3f, 0.85f);

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

        Label label = new Label("Achievements", skin);
        container.add(label).pad(10).row();

        for (Achievement achievement : achievementsManager.achievements)
        {
            Image achievementLogo = new Image(achievement.logo);
            achievementLogo.setColor(DISABLED_COLOUR);
            achievementLogos.add(achievementLogo);
            container.add(achievementLogo).size(90, 90).pad(5).row();
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
            achievementLogos.get(i).setColor(achievementsManager.achievements[i].hasAchieved() ? Color.WHITE : DISABLED_COLOUR);
        }
    }

    public void dispose()
    {
        stage.dispose();
    }
}
