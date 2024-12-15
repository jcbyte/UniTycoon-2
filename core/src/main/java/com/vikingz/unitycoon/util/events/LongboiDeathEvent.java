package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class LongboiDeathEvent extends Event {
    // todo if you own a longboi statue this can be reduced
    public LongboiDeathEvent(GameScreen gameScreen) {
        super(
            "Your university's mascot, Longboi, has passed away.",
            new Event.Option(() -> {
                GameGlobals.SATISFACTION -= 250000;

                gameScreen.setPaused(false);
            }, "-Satisfaction")
        );
    }
}
