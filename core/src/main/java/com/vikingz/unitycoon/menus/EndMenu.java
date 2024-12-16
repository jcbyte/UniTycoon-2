package com.vikingz.unitycoon.menus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameConfigManager;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.util.LeaderboardManager;
import com.vikingz.unitycoon.util.StatsCalculator;

import java.lang.constant.Constable;

/**
 * This class is the menu that pops up at the end of the game.
 *
 * This menu also contains a quit button that sends the user back to the
 * main menu as well as a continue button that lets the user continue the game.
 */
public class EndMenu extends Window {

    //Message to be displayed at end of the game
    private final String Message = "";

    // Label containing the leaderboard text
    private final Label leaderboardLabel;

    // Label containing your score
    private final Label scoreLabel;

    // Table containing the component to add our score to the leaderboard
    private final Table updateLeaderboardTable;

    // TextField containing the name of the user to add to the leaderboard
    private final TextField leaderboardTextField;

    //skin used for window
    private final Skin skin;

    /**
     * Creates a new EndMenu
     * @param skin The skin used to style the popup
     * @param Message The message that will be shown on the popup
     */
    public EndMenu(Skin skin, String Message) {

        super("", skin);

        this.setSize(800, 400);
        this.setModal(true);
        this.setMovable(false);
        this.setResizable(false);

        this.skin = skin;
        this.setBackground(GameGlobals.backGroundDrawable);

        Label message = new Label(Message, skin);
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
                LeaderboardManager.LeaderboardRecord record = new LeaderboardManager.LeaderboardRecord(leaderboardTextField.getText(), GameGlobals.SATISFACTION);
                LeaderboardManager.updateLeaderboard(GameConfig.getInstance().leaderboard, record);
                GameConfigManager.saveGameConfig();

                // Update visual leaderboard and hide the panel to only allow one to be added
                refreshLeaderboardText();
                updateLeaderboardTable.setVisible(false);
            }
        });

        this.add(yourScoreTable).row();
    }

    private void refreshLeaderboardText()
    {
        leaderboardLabel.setText("Leaderboard:\n" + LeaderboardManager.LeaderboardToString(GameConfig.getInstance().leaderboard));
    }

    public void refresh(boolean scoreOnLeaderboard)
    {
        refreshLeaderboardText();
        scoreLabel.setText("Your Score: " + StatsCalculator.getFormattedSatisfaction(GameGlobals.SATISFACTION));
        leaderboardTextField.setText("");
        updateLeaderboardTable.setVisible(scoreOnLeaderboard);
    }

    /**
     * Crates the buttons Left and Right,
     * sets the actions and text of each button
     * @param leftRun contains function to be run on click for left button
     * @param leftText contains text for the left button
     * @param rightRun contains function to be run on click for right button
     * @param rightText contains text for the right button
     */
    public void setupButtons(Runnable leftRun, String leftText, Runnable rightRun, String rightText){
        TextButton leftBtn = new TextButton(leftText, skin);
        TextButton rightBtn = new TextButton(rightText, skin);
        this.add(leftBtn).pad(10);
        this.add(rightBtn).pad(10);

        // Created for yes - no game events
        // The Popup needs to call back to parent object in someway

        leftBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                leftRun.run();
                //PopupMenu.this.remove();
            }
        });

        rightBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rightRun.run();
                //PopupMenu.this.remove();
            }
        });
    }
}
