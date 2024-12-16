package com.vikingz.unitycoon.events;

import com.badlogic.gdx.math.MathUtils;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class CasinoEvent extends Event {
    public CasinoEvent(GameScreen gameScreen) {
        super(
            "You are at the casino.\nDo you put it all on red?",
            new Event.Option(() -> {
                gameScreen.setPaused(false);
            }, "Dont gamble"),
            new Event.Option(() -> {
                if (MathUtils.randomBoolean())
                    GameGlobals.BALANCE *= 2;
                else
                    GameGlobals.BALANCE = 0;

                gameScreen.setPaused(false);
            }, "All on red\n2x Money (50%)\nLose all money (50%)")
        );
    }
}
