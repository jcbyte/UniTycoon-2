package com.vikingz.unitycoon.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vikingz.unitycoon.achievement.Achievement;
import com.vikingz.unitycoon.achievement.AchievementsManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to render the achievement badges.
 */
public class AchievementsRenderer {
  private static final Color DISABLED_COLOUR = new Color(0.3f, 0.3f, 0.3f, 0.85f);

  private final Viewport viewport;
  private final Stage stage;
  private final List<Image> achievementLogos;
  private final AchievementsManager achievementsManager;

  /**
   * Initialise the achievement's renderer.
   *
   * <p>Creates the badges displayed for each achievement.
   */
  public AchievementsRenderer(AchievementsManager achievementsManager, Skin skin) {
    viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    stage = new Stage(viewport);
    achievementLogos = new ArrayList<>();
    this.achievementsManager = achievementsManager;

    Table container = new Table();
    container.setFillParent(true);
    container.right();

    Label label = new Label("Achievements", skin);
    label.setColor(Color.BLACK);
    container.add(label).colspan(2).pad(5).right().row();

    for (Achievement achievement : achievementsManager.achievements) {
      Label achivementLabel = new Label(achievement.name, skin);
      achivementLabel.setColor(Color.BLACK);
      achivementLabel.setFontScale(1.2f);
      achivementLabel.setVisible(false);

      Image achievementLogo = new Image(achievement.logo);
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
      container.add(achivementLabel).right().pad(5, 0, 5, 10);
      container.add(achievementLogo).size(90, 90).pad(5, 10, 5, 15).row();
    }

    stage.addActor(container);
  }

  /**
   * Draw onto the screen.
   *
   * <p>This should be run every render
   */
  public void render(float delta) {
    viewport.apply();
    stage.act(delta);
    stage.draw();
  }

  /**
   * Update the badges colours, this should be called when an achievement has been reached.
   */
  public void update() {
    for (int i = 0; i < achievementsManager.achievements.length; i++) {
      achievementLogos.get(i).setColor(
          achievementsManager.achievements[i].hasAchieved() ? Color.WHITE : DISABLED_COLOUR);
    }
  }

  /**
   * Dispose of the resources.
   */
  public void dispose() {
    stage.dispose();
  }

  /**
   * Get the input processor used.
   */
  public Stage getInputProcessor() {
    return stage;
  }

  /**
   * Call when the window is resized.
   */
  public void resize(int width, int height) {
    // Update the viewport when the window is resized
    viewport.update(width, height, true);
  }
}
