package com.vikingz.unitycoon.render.menus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.vikingz.unitycoon.global.GameGlobals;

/**
 * This is a generic PopupMenu class that can create user defined popups.
 *
 * <p>The user can assign everything in the popup, all the text displayed as well as the runnable
 * that is called when buttons are pressed.
 */
public class PopupMenu extends Window {

  private final Label message;
  private final TextButton leftBtn;
  private final TextButton rightBtn;

  /**
   * Creates a new Popup menu.
   *
   * @param skin Skin for the menu
   */
  public PopupMenu(Skin skin) {
    super("", skin);

    this.setSize(800, 400);
    this.setModal(true);
    this.setMovable(false);
    this.setResizable(false);

    this.setBackground(GameGlobals.backGroundDrawable);

    message = new Label("", skin);
    message.setFontScale(1.3f);
    this.add(message).align(Align.center).pad(50).row();

    leftBtn = new TextButton("", skin);
    leftBtn.getLabel().setFontScale(0.5f);
    rightBtn = new TextButton("", skin);
    rightBtn.getLabel().setFontScale(0.5f);

    Table buttonTable = new Table();
    buttonTable.add(leftBtn).expandX().fillX().pad(10);
    buttonTable.add(rightBtn).expandX().fillX().pad(10);
    this.add(buttonTable);
  }

  /**
   * Configures two buttons that appear on the popup.
   *
   * @param leftRun       Runnable that will be run if the left button is pressed
   * @param leftText      The text written on the left button
   * @param leftDisabled  if the left button is disabled
   * @param rightRun      Runnable that will be run if the right button is pressed
   * @param rightText     The text written on the right button
   * @param rightDisabled if the right button is disabled
   */
  public void setupButtons(Runnable leftRun, String leftText, boolean leftDisabled,
                           Runnable rightRun, String rightText, boolean rightDisabled) {
    if (leftDisabled && rightDisabled) {
      throw new RuntimeException("Both buttons cannot be disabled");
    }

    setupButton(leftBtn, leftRun, leftText, leftDisabled);
    setupButton(rightBtn, rightRun, rightText, rightDisabled);

    rightBtn.setVisible(true);
  }

  private void setupButton(TextButton btn, Runnable runnable, String text, boolean disabled) {
    btn.setText(text);
    btn.clearListeners();
    if (!disabled) {
      btn.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          runnable.run();
          PopupMenu.this.remove();
        }
      });
      btn.setColor(Color.WHITE);
    } else {
      btn.setColor(Color.GRAY);
    }
  }

  /**
   * Configures one button that appears on the popup.
   *
   * @param run  Runnable that will be run when the button is pressed
   * @param text The text written on the button
   */
  public void setupSingleButton(Runnable run, String text) {
    setupButton(leftBtn, run, text, false);

    rightBtn.setVisible(false);
  }

  /**
   * Set the message to be displayed on the popup.
   */
  public void setMessage(String message) {
    this.message.setText(message);
  }

  /**
   * Disable the popup temporarily, so the user does not immediately click on a button.
   *
   * @param ms time before re-enabling in milliseconds
   */
  public void enableAfter(int ms) {
    setTouchable(Touchable.disabled);

    Timer.schedule(new Timer.Task() {
      @Override
      public void run() {
        System.out.println("enabled");
        setTouchable(Touchable.enabled);

      }
    }, (float) ms / 1000f);
  }
}
