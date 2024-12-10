package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class LongboiDeathEvent extends Event {
    public LongboiDeathEvent(GameScreen gameScreen) {
        super(
            "Your university's mascot, Longboi, has passed away.",
            new Event.Option(() -> {
                GameGlobals.SATISFACTION -= 1000;

                gameScreen.setPaused(false);
            }, "-1000 Satisfaction")
        );
    }
}
