package com.vikingz.unitycoon.util;

import com.vikingz.unitycoon.global.GameGlobals;

import java.util.concurrent.Callable;

public class AchievementsManager {
    public class Achievement
    {
        public static String name;
        private boolean got;
        private static Callable<Boolean> has;

        public static Runnable reward;
        public static String rewardText;

        public Achievement(String name, Callable<Boolean> has, Runnable reward, String rewardText)
        {
            this.name = name;
            got = false;
            this.has = has;
            this.reward = reward;
            this.rewardText = rewardText;
        }

        /**
         * CHeck if the achievement has been archived
         */
        public boolean hasAchieved()
        {
            return got;
        }

        /**
         * Calculate if the achievement has been reached
         * @return true only the first time it is reached
         */
        public boolean calculate() {
            if (got)
                return false;

            try {
                got = has.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return got;
        }
    }

    public Achievement[] achievements;

    // todo implement these

    public AchievementsManager() {
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
}
