package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class AlumniVisitEvent extends Event {
    public AlumniVisitEvent(GameScreen gameScreen) {
        super(() -> {
            boolean alumniImpressed = GameGlobals.SATISFACTION > 2000;
            return new Event(
                "Surprise alumni Visit\n" + (alumniImpressed ? "The alumni is impressed with the university and donates" : "The alumni is not impressed and your reputation decreases") + ".",
                new Event.Option(() -> {
                    if (alumniImpressed)
                        GameGlobals.BALANCE += 1000;
                    else
                        GameGlobals.STUDENTS = Math.max(0, GameGlobals.STUDENTS - 200);

                    gameScreen.setPaused(false);
                }, alumniImpressed ? "+1000 Money" : "-200 Students")
            );
        });
    }
}
