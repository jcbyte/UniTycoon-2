package com.vikingz.unitycoon.render.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.ScreenMultiplexer;
import com.vikingz.unitycoon.util.LeaderboardUtil;
import com.vikingz.unitycoon.util.StatsCalculator;

/**
 * This class is the menu that pops up at the end of the game.
 *
 * <p>This menu also contains a quit button that sends the user back to the main menu as well as
 * the leaderboard and a way to add your score to the leaderboard.
 */
public class EndMenu extends Window {
  // Label containing the leaderboard text
  private final Label leaderboardLabel;

  // Label containing your score
  private final Label scoreLabel;

  // Table containing the component to add our score to the leaderboard
  private final Table updateLeaderboardTable;

  // TextField containing the name of the user to add to the leaderboard
  private final TextField leaderboardTextField;

  /**
   * Creates a new EndMenu.
   *
   * @param skin       The skin used to style the popup
   * @param endMessage The message that will be shown on the popup
   */
  public EndMenu(Skin skin, String endMessage) {
    super("", skin);

    this.setSize(800, 400);
    this.setModal(true);
    this.setMovable(false);
    this.setResizable(false);
    this.setBackground(GameGlobals.backGroundDrawable);

    Label message = new Label(endMessage, skin);
    this.add(message).padLeft(-35).row();

    Table leaderboardTable = new Table();

    leaderboardLabel = new Label(null, skin);
    leaderboardTable.add(leaderboardLabel);
    this.add(leaderboardTable);

    Table yourScoreTable = new Table();
    updateLeaderboardTable = new Table();

    scoreLabel = new Label("Your Score: NA", skin);
    yourScoreTable.add(scoreLabel).left().padLeft(35).row();

    Label leaderboardLabelLabel = new Label("Name:", skin);
    updateLeaderboardTable.add(leaderboardLabelLabel).left().padTop(5).padBottom(-5).row();
    leaderboardTextField = new TextField("", skin);
    leaderboardTextField.setPosition(200, 200);
    leaderboardTextField.setSize(200, 100);
    updateLeaderboardTable.add(leaderboardTextField);

    Table addButtonTable = new Table();
    TextButton addButton = new TextButton("Add", skin);
    addButton.setTransform(true);
    addButton.setScale(0.5f);
    addButtonTable.add(addButton).size(200, 100).padLeft(5).padTop(-50);
    updateLeaderboardTable.add(addButtonTable);

    yourScoreTable.add(updateLeaderboardTable);

    // If add button is pressed then add to the leaderboard and hide the section
    addButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        // Add to leaderboard and save to file
        LeaderboardUtil.LeaderboardRecord record = new LeaderboardUtil.LeaderboardRecord(
            leaderboardTextField.getText(),
            GameGlobals.SATISFACTION
        );
        LeaderboardUtil.updateLeaderboard(GameConfig.getInstance().getLeaderboard(), record);
        GameConfigManager.saveGameConfig();

        // Update visual leaderboard and hide the panel to only allow one to be added
        refreshLeaderboardText();
        updateLeaderboardTable.setVisible(false);
      }
    });

    this.add(yourScoreTable).row();

    TextButton quitBtn = new TextButton("Exit (to menu)", skin);
    quitBtn.getLabel().setFontScale(0.7f);
    this.add(quitBtn).pad(10);
    quitBtn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        ScreenMultiplexer.closeGame();
      }
    });
  }

  /**
   * Update leaderboard text with data from GameConfig.
   */
  private void refreshLeaderboardText() {
    leaderboardLabel.setText(
        "Leaderboard:\n"
            + LeaderboardUtil.leaderboardToString(GameConfig.getInstance().getLeaderboard())
    );
  }

  /**
   * Refresh the window to display the leaderboard table only if your score is on the leaderboard.
   *
   * @param scoreOnLeaderboard if the players score is on the leaderboard
   */
  public void refresh(boolean scoreOnLeaderboard) {
    refreshLeaderboardText();
    scoreLabel.setText(
        "Your Score: " + StatsCalculator.getFormattedSatisfaction(GameGlobals.SATISFACTION)
    );
    leaderboardTextField.setText("");
    updateLeaderboardTable.setVisible(scoreOnLeaderboard);
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
