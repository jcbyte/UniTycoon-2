package com.vikingz.unitycoon.render;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.util.StatsCalculator;
import com.vikingz.unitycoon.util.TimeUtil;

/**
 * This class is used for drawing game stats to the screen.
 * <p>
 * This class contains all the labels that are on the
 * top right of the screen that display the users balance,
 * satisfaction etc.
 */
public class StatsRenderer {

    //Used to render UI
    private final Stage stage;
    private final BitmapFont font;

    // Labels
    String balStr;
    Label balanceLabel;

    String studentsStr;
    Label studentsLabel;

    String satisStr;
    Label satisfactionLabel;

    String timerStr;
    Label timerLabel;

    /**
     * Creates a new stats renderer
     * @param skin Skin that determines the style of the text
     */
    public StatsRenderer(Skin skin) {
        stage = new Stage();
        font = new BitmapFont();
        font.getData().setScale(1.5f);

        // Label strings
        balStr = "Balance: ";
        studentsStr = "Students: ";
        satisStr = "Satisfaction: ";
        timerStr = "Timer: ";

        // Creating labels
        balanceLabel = new Label(balStr, skin);
        balanceLabel.setColor(Color.BLACK);
        balanceLabel.setFontScale(2f);

        studentsLabel = new Label(studentsStr, skin);
        studentsLabel.setColor(Color.BLACK);
        studentsLabel.setFontScale(2f);

        satisfactionLabel = new Label(satisStr, skin);
        satisfactionLabel.setColor(Color.BLACK);
        satisfactionLabel.setFontScale(2f);

        timerLabel = new Label(timerStr, skin);
        timerLabel.setColor(Color.BLACK);
        timerLabel.setFontScale(2f);

        // Create a background color texture
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.95f, 0.95f, 1f, 0.9f);
        pixmap.fill();
        Drawable background = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        // Create layout tables
        Table outerTable = new Table();
        outerTable.setFillParent(true);
        outerTable.top();

        Table table = new Table();
        table.setBackground(background);
        table.defaults().pad(5, 10, 5, 10).width(380);

        // Adds the labels to the table
        table.add(balanceLabel);
        table.add(studentsLabel);
        table.add(satisfactionLabel);
        table.add(timerLabel);

        outerTable.add(table).expandX().fillX();
        stage.addActor(outerTable);
    }

    /**
     * Draws the labels to the screen
     * @param delta Time since last frame
     */
    public void render(float delta) {
        // Update the label contents each frame
        balanceLabel.setText(balStr + GameGlobals.BALANCE);
        studentsLabel.setText(studentsStr + GameGlobals.STUDENTS);
        satisfactionLabel.setText(satisStr + StatsCalculator.getFormattedSatisfaction(GameGlobals.SATISFACTION));
        timerLabel.setText(timerStr + TimeUtil.secondsToMinSecs(GameGlobals.ELAPSED_TIME));

        stage.act(delta);
        stage.draw();
    }

    /**
     * disposes stats being drawn for garbage collection
     */
    public void dispose(){
        stage.dispose();
        font.dispose();
    }
}
