package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class BigOilEvent extends Event {
    public BigOilEvent(GameScreen gameScreen) {
        super(
            "Big oil wants to donate to the university, however this could be controversial.",
            new Event.Option(() -> {
                GameGlobals.SATISFACTION += 100;

                gameScreen.setPaused(false);
            }, "Dont Accept\n+100 Students"),
            new Event.Option(() -> {
                GameGlobals.BALANCE += 2000;
                GameGlobals.STUDENTS -= 400;

                gameScreen.setPaused(false);
            }, "Accept the money\n+2000 Money\n-400 Students")
        );
    }
}
