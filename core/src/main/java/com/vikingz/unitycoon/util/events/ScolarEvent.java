package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class ScolarEvent extends Event {
    public ScolarEvent(GameScreen gameScreen) {
        super(
            "A famous scholar visits, boosting prestige and attracting more students.",
            new Event.Option(() -> {
                GameGlobals.STUDENTS += 100;

                gameScreen.setPaused(false);
            }, "+100 Students")
        );
    }
}
