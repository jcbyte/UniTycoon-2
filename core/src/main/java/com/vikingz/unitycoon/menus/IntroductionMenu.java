package com.vikingz.unitycoon.menus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.vikingz.unitycoon.global.GameGlobals;

/**
 * Class containing the introduction menu which is displayed at the beginning of the game.
 */
public class IntroductionMenu extends Window {

    /**
     * Creates a new Introduction menu
     */
    public IntroductionMenu(Skin skin, Runnable btnRunnable) {
        super("", skin);

        this.setSize(800, 800);
        this.setModal(true);
        this.setMovable(false);
        this.setResizable(false);
        this.setBackground(GameGlobals.backGroundDrawable);

        Label message = new Label(
            "Welcome\n" +
                "\n" +
                "Your goal is to build a thriving university filled with happy\nstudents. Aim to achieve 100% satisfaction within 5 minutes!\n" +
                "\n" +
                "Tips:\n" +
                "- Place buildings using the four building category buttons\n   at the bottom of the screen.\n" +
                "- You can sell buildings once placed by right clicking on\n   them\n" +
                "- Pause the game anytime by pressing ESC or clicking the\n   pause button in the bottom left corner.\n" +
                "- Achieve milestones (displayed on the right side of the\n   screen) to earn rewards that will help you progress.\n" +
                "\n" +
                "Good luck!\n",
            skin
        );
        message.setFontScale(1.3f);
        this.add(message).align(Align.center).pad(50).row();

        TextButton btn = new TextButton("Begin!", skin);
        btn.getLabel().setFontScale(0.5f);
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnRunnable.run();
                IntroductionMenu.this.remove();
            }
        });

        Table buttonTable = new Table();
        buttonTable.add(btn).expandX().fillX().pad(10);
        this.add(buttonTable);
    }
}
