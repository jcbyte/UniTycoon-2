package com.vikingz.unitycoon.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vikingz.unitycoon.util.Achievement;
import com.vikingz.unitycoon.util.AchievementsManager;

import java.util.ArrayList;
import java.util.List;

public class AchievementsRenderer {
    final Color DISABLED_COLOUR = new Color(0.3f, 0.3f, 0.3f, 0.85f);

    private final Stage stage;
    private final List<Image> achievementLogos;
    private final AchievementsManager achievementsManager;


    public AchievementsRenderer(AchievementsManager achievementsManager, Skin skin) {

        stage = new Stage();
        achievementLogos = new ArrayList<>();
        this.achievementsManager = achievementsManager;

        Table container = new Table();
        container.setFillParent(true);
        container.top();
        container.right();

        Label label = new Label("Achievements:", skin);
        label.setColor(Color.BLACK);
        label.setFontScale(1.5f);
        container.add(label).pad(10).colspan(2).right().row();

        for (Achievement achievement : achievementsManager.achievements)
        {
            Image achievementLogo = new Image(achievement.logo);
            Label achivementLabel = new Label(achievement.name, skin);
            achivementLabel.setColor(Color.BLACK);
            achivementLabel.setVisible(false);

            achievementLogo.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    achivementLabel.setVisible(true);
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    achivementLabel.setVisible(false);
                }
            });

            achievementLogo.setColor(DISABLED_COLOUR);
            achievementLogos.add(achievementLogo);
            container.add(achivementLabel).pad(10);
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

    public Stage getInputProcessor()
    {
        return stage;
    }
}
