package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class BigOilEvent extends Event {
    public BigOilEvent(GameScreen gameScreen) {
        super(
            "Big oil wants to donate to the university.\nThis could be controversial.",
            new Event.Option(() -> {
                GameGlobals.SATISFACTION += 50;

                gameScreen.setPaused(false);
            }, "Dont Accept\n+50 Students"),
            new Event.Option(() -> {
                GameGlobals.BALANCE += 4000;
                GameGlobals.STUDENTS = Math.max(0, GameGlobals.STUDENTS - 500);

                gameScreen.setPaused(false);
            }, "Accept the money\n+4000 Money\n-500 Students")
        );
    }
}
