package com.vikingz.unitycoon.render;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
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
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final Stage stage;

    // Labels
    String balStr;
    Label balance;

    String studentsStr;
    Label students;

    String satisStr;
    Label satisfaction;

    String timerStr;
    Label timer;

    //Stores all labels
    List<Label> labels;

    /**
     * Creates a new stats renderer
     * @param skin Skin that determines the style of the text
     */
    public StatsRenderer(Skin skin) {

        batch = new SpriteBatch();
        stage = new Stage();
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        labels = new ArrayList<>();

        // Label strings
        balStr = "Balance";
        studentsStr = "Students";
        satisStr = "satisfaction";
        timerStr = "Timer: ";

        // Creating labels
        balance = new Label(balStr, skin);
        students = new Label(studentsStr, skin);
        satisfaction = new Label(satisStr, skin);
        timer = new Label(timerStr, skin);

        // Adding labels to a list
        labels.add(balance);
        labels.add(students);
        labels.add(satisfaction);
        labels.add(timer);

        for(Label lbl: labels){
            lbl.setColor(Color.BLACK);
            lbl.setFontScale(1.5f);
        }

        int padding = 3;

        // Create layout table
        Table table = new Table();
        table.setFillParent(true);

        table.top();
        table.left();

        // Adds the labels to the table
        table.add(balance).pad(padding).align(Align.left);
        table.row();
        table.add(students).pad(padding).align(Align.left);
        table.row();
        table.add(satisfaction).pad(padding).align(Align.left);
        table.row();
        table.add(timer).pad(padding).align(Align.left);

        stage.addActor(table);

    }


    /**
     * Draws the labels to the screen
     * @param delta Time since last frame
     */
    public void render(float delta) {

        batch.begin();

        // Update the label contents each frame
        balStr = "Balance: " + GameGlobals.BALANCE;
        studentsStr = "Students: " + GameGlobals.STUDENTS;
        satisStr = "Satisfaction: " + StatsCalculator.getFormattedSatisfaction(GameGlobals.SATISFACTION);

        TimeUtil.Time timerAmount = TimeUtil.secondsToMinSecs(GameGlobals.ELAPSED_TIME);
        timerStr = timerAmount == null? (timerStr = "Timer: Infinity") : (timerStr = "Timer: " + timerAmount);

        // Sets the new string to the corresponding label
        balance.setText(balStr);
        students.setText(studentsStr);
        satisfaction.setText(satisStr);
        timer.setText(timerStr);

        stage.act(delta);
        stage.draw();
        batch.end();
    }

    /**
     * Sets current width and height to the new values when the window is resized
     * @param width New width
     * @param height New height
     */
    public void resize(float width, float height){
    }

    /**
     * disposes stats being drawn for garbage collection
     */
    public void dispose(){
        stage.dispose();
        batch.dispose();
        font.dispose();
    }
}
