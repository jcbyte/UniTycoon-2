package com.vikingz.unitycoon.render.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UIRenderer;
import com.vikingz.unitycoon.screens.ScreenMultiplexer;

/**
 * This class represents the pause menu.
 *
 * <p>This is the menu that appears if the user pauses the game.
 *
 * <p>The menu contains a settings button, resume button, and quit button.
 */
public class PauseMenu extends Window {
  /**
   * Creates a new pause menu.
   *
   * @param skin Contains the skin pack to be used with menu
   */
  public PauseMenu(Skin skin, UIRenderer uiRenderer) {
    super("", skin);

    this.setSize(800, 400);
    this.setModal(true);
    this.setMovable(false);
    this.setResizable(false);

    Label message = new Label("Game Paused!", skin);
    message.setFontScale(2f);
    this.add(message).padBottom(20).row();
    this.setBackground(GameGlobals.backGroundDrawable);

    TextButton settingsBtn = new TextButton("Settings", skin);
    settingsBtn.getLabel().setFontScale(0.7f);
    settingsBtn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        ScreenMultiplexer.openSettings(ScreenMultiplexer.Screens.GAME);
      }
    });

    TextButton continueBtn = new TextButton("Resume", skin);
    continueBtn.getLabel().setFontScale(0.7f);
    continueBtn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        uiRenderer.pause();
      }
    });

    TextButton quitBtn = new TextButton("Exit (to menu)", skin);
    quitBtn.getLabel().setFontScale(0.7f);
    quitBtn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        ScreenMultiplexer.closeGame();
      }
    });

    this.add(settingsBtn).padBottom(10);
    this.add(continueBtn).pad(10).row();
    this.add(quitBtn).pad(10).colspan(2);
  }
}
