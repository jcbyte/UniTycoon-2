package com.vikingz.unitycoon.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class StrikeEvent extends Event {
    public StrikeEvent(GameScreen gameScreen) {
        super(() ->
            new Event(
                "Faculty members threaten to strike over pay.",
                new Event.Option(() -> {
                    GameGlobals.BALANCE -= 500;

                    gameScreen.setPaused(false);
                }, "Increase pay\n-500 Money", GameGlobals.BALANCE < 500),
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION -= 200000;

                    gameScreen.setPaused(false);
                }, "Fire the staff\n-Satisfaction")
            )
        );
    }
}
