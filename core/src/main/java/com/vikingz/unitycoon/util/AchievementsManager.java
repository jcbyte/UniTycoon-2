package com.vikingz.unitycoon.util;

import com.badlogic.gdx.graphics.Texture;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UIRenderer;
import com.vikingz.unitycoon.screens.GameScreen;

public class AchievementsManager {
    private UIRenderer uiRenderer;
    public Achievement[] achievements;

    /**
     * Used to set the uiRenderer as it is not required until updates.
     * Deferred Initialization as the UIRenderer requires an instance of this to initialise.
     */
    public void setUIRenderer(UIRenderer uiRenderer)
    {
        this.uiRenderer = uiRenderer;
    }

    public AchievementsManager() {
        achievements = new Achievement[] {
            new Achievement(
                "Dean of Dollars\nHave a balance over 15k",
                () -> GameGlobals.BALANCE > 15000,
                new Texture("achievements/dean-of-dollars.png"),
                () -> {
                    GameGlobals.SATISFACTION += 100000;
                },
                "+100000 Satisfaction"
            ),
            new Achievement(
                "A+ Atmosphere\nHave over 1 Million Satisfaction",
                () -> GameGlobals.SATISFACTION > 1000000,
                new Texture("achievements/dean-of-dollars.png"),
                () -> {
                    GameGlobals.BALANCE += 400;
                },
                "+400 Money"
            ),
            new Achievement(
                "Concrete Campus\nHave over 10 Buildings",
                () -> GameGlobals.ACADEMIC_BUILDINGS_COUNT + GameGlobals.ACCOMODATION_BUILDINGS_COUNT + GameGlobals.FOOD_BUILDINGS_COUNT + GameGlobals.RECREATIONAL_BUILDINGS_COUNT > 10,
                new Texture("achievements/dean-of-dollars.png"),
                () -> {
                    GameGlobals.BALANCE += 150;
                    GameGlobals.STUDENTS += 150;
                },
                "+150 Money\n+150 Students"
            ),
            new Achievement(
                "Crowded Campus\nHave over 1000 Students",
                () -> GameGlobals.STUDENTS > 50, // todo return to 1000
                new Texture("achievements/dean-of-dollars.png"),
                () -> {
                    GameGlobals.SATISFACTION += 50000;
                    GameGlobals.STUDENTS += 100;
                },
                "+50000 Satisfaction\n+100 Students"
            ),
            new Achievement(
                "Work Hard, Play Harder\nHave over 5 recreational buildings",
                () -> GameGlobals.RECREATIONAL_BUILDINGS_COUNT > 5,
                new Texture("achievements/dean-of-dollars.png"),
                () -> {
                    GameGlobals.SATISFACTION += 100000;
                },
                "+100000 Satisfaction"
            ),
        };
    }

    public void update()
    {
        for (Achievement achievement : achievements)
        {
            if (achievement.calculate())
            {
                uiRenderer.showAchievement(achievement);
            }
        }
    }
}
