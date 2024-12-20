package com.vikingz.unitycoon.event.events;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class ScolarEvent extends Event {
    public ScolarEvent(GameScreen gameScreen) {
        super(
            "A famous scholar visits.\nPrestige increases, attracting more students.",
            new Event.Option(() -> {
                GameGlobals.STUDENTS += 150;

                gameScreen.setPaused(false);
            }, "+150 Students")
        );
    }
}
