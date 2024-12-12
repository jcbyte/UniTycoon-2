package com.vikingz.unitycoon.util;

import com.badlogic.gdx.graphics.Texture;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.UIRenderer;
import com.vikingz.unitycoon.screens.GameScreen;

public class AchievementsManager {
    private GameScreen gameScreen;
    public Achievement[] achievements;

    public AchievementsManager(GameScreen gameScreen) {
        this.gameScreen = gameScreen;

        achievements = new Achievement[] {
            new Achievement(
                "Dean of Dollars\nHave a balance over 15k",
                () -> GameGlobals.BALANCE > 15000,
                new Texture("achievements/dean-of-dollars.png"),
                () -> {
                    GameGlobals.SATISFACTION += 100000;
                    gameScreen.setPaused(false);
                },
                "+100000 Satisfaction"
            ),
            new Achievement(
                "A+ Atmosphere\nHave over 1 Million Satisfaction",
                () -> GameGlobals.SATISFACTION > 1000000,
                new Texture("achievements/aplus-atmosphere.png"),
                () -> {
                    GameGlobals.BALANCE += 400;
                    gameScreen.setPaused(false);
                },
                "+400 Money"
            ),
            new Achievement(
                "Concrete Campus\nHave over 10 Buildings",
                () -> GameGlobals.ACADEMIC_BUILDINGS_COUNT + GameGlobals.ACCOMODATION_BUILDINGS_COUNT + GameGlobals.FOOD_BUILDINGS_COUNT + GameGlobals.RECREATIONAL_BUILDINGS_COUNT > 10,
                new Texture("achievements/concrete-campus.png"),
                () -> {
                    GameGlobals.BALANCE += 150;
                    GameGlobals.STUDENTS += 150;
                    gameScreen.setPaused(false);
                },
                "+150 Money\n+150 Students"
            ),
            new Achievement(
                "Crowded Campus\nHave over 1000 Students",
                () -> GameGlobals.STUDENTS > 1000,
                new Texture("achievements/crowded-campus.png"),
                () -> {
                    GameGlobals.SATISFACTION += 50000;
                    GameGlobals.STUDENTS += 100;
                    gameScreen.setPaused(false);
                },
                "+50000 Satisfaction\n+100 Students"
            ),
            new Achievement(
                "Work Hard, Play Harder\nHave over 5 recreational buildings",
                () -> GameGlobals.RECREATIONAL_BUILDINGS_COUNT > 5,
                new Texture("achievements/work-play.png"),
                () -> {
                    GameGlobals.SATISFACTION += 100000;
                    gameScreen.setPaused(false);
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
                gameScreen.setPaused(true);
                gameScreen.getUIRenderer().showAchievement(achievement);
            }
        }
    }
}
