package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class GrantEvent extends Event {
    public GrantEvent(GameScreen gameScreen)
    {
        super(
            "You receive a grant from the government.",
            new Event.Option(() -> {
                GameGlobals.BALANCE += 1000;

                gameScreen.setPaused(false);
            }, "Take the cash\n+1000 Money"),
            new Event.Option(() -> {
                GameGlobals.STUDENTS += 300;

                gameScreen.setPaused(false);
            }, "Reduce fees\n+300 Students")
        );
    }
}
