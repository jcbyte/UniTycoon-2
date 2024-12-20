package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class GraduationEvent extends Event {
    public GraduationEvent(GameScreen gameScreen) {
        super(
            "Graduation Day!\nThe hard work of your students, faculty, and staff has lead\nup to this moment. Proud graduates, dressed in caps and\ngowns, step into the world carrying the knowledge and\nexperiences gained under your leadership.",
            new Event.Option(() -> {
                GameGlobals.SATISFACTION += 1000000;

                gameScreen.setPaused(false);
            }, "+Satisfaction")
        );
    }
}
