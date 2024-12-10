package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class GraduationEvent extends Event {
    public GraduationEvent(GameScreen gameScreen) {
        super(() -> {
            int satisfactionIncrease = Math.round(GameGlobals.SATISFACTION / 10000f) * 10000;
            return new Event(
                "Graduation Day!\nThe hard work of your students, faculty, and staff has lead\nup to this moment. Proud graduates, dressed in caps and\ngowns, step into the world carrying the knowledge and\nexperiences gained under your leadership.",
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION += satisfactionIncrease;

                    gameScreen.setPaused(false);
                }, "+" + satisfactionIncrease + " Satisfaction")
            );
        });
    }
}
