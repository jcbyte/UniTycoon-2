package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class GraduationEvent extends Event {
    public GraduationEvent(GameScreen gameScreen) {
        super(
            "Graduation Day!\nThe hard work of your students, faculty, and staff has lead up to this triumphant moment.\nProud graduates, dressed in caps and gowns, step into the world carrying the knowledge and experiences gained under your leadership.",
            new Event.Option(() -> {
                GameGlobals.SATISFACTION += 20000;

                gameScreen.setPaused(false);
            }, "+20000 Satisfaction")
        );
    }
}
