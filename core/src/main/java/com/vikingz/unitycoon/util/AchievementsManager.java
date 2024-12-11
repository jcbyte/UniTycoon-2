package com.vikingz.unitycoon.util;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UIRenderer;
import com.vikingz.unitycoon.screens.GameScreen;

public class AchievementsManager {
    private UIRenderer uiRenderer;
    public Achievement[] achievements;

    public AchievementsManager(GameScreen gameScreen) {
        uiRenderer = gameScreen.getUIRenderer();

        achievements = new Achievement[] {
            new Achievement(
                "Dean of Dollars\nHave a balance over 15k",
                () -> GameGlobals.BALANCE > 15000,
                () -> {
                    GameGlobals.SATISFACTION += 100000;
                },
                "+100000 Satisfaction"
            ),
            new Achievement(
                "A+ Atmosphere\nHave over 1 Million Satisfaction",
                () -> GameGlobals.SATISFACTION > 1000000,
                () -> {
                    GameGlobals.BALANCE += 400;
                },
                "+400 Money"
            ),
            new Achievement(
                "Concrete Campus\nHave over 10 Buildings",
                () -> GameGlobals.ACADEMIC_BUILDINGS_COUNT + GameGlobals.ACCOMODATION_BUILDINGS_COUNT + GameGlobals.FOOD_BUILDINGS_COUNT + GameGlobals.RECREATIONAL_BUILDINGS_COUNT > 10,
                () -> {
                    GameGlobals.BALANCE += 150;
                    GameGlobals.STUDENTS += 150;
                },
                "+150 Money\n+150 Students"
            ),
            new Achievement(
                "Crowded Campus\nHave over 1000 Students",
                () -> GameGlobals.STUDENTS > 1000,
                () -> {
                    GameGlobals.SATISFACTION += 50000;
                    GameGlobals.STUDENTS += 100;
                },
                "+50000 Satisfaction\n+100 Students"
            ),
            new Achievement(
                "Work Hard, Play Harder\nHave over 5 recreational buildings",
                () -> GameGlobals.RECREATIONAL_BUILDINGS_COUNT > 5,
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
