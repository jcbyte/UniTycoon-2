package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class CurriculumChangeEvent extends Event {
    public CurriculumChangeEvent(GameScreen gameScreen) {
        super(() ->
            new Event(
                "A faculty member suggests a major change to the university's curriculum to make it more cutting-edge.",
                new Event.Option(() -> {
                    gameScreen.setPaused(false);
                }, "Leave it"),
                new Event.Option(() -> {
                    GameGlobals.BALANCE -= 200;
                    GameGlobals.STUDENTS += 200;

                    gameScreen.setPaused(false);
                }, "Change it\n-200 Money\n+200 Students", GameGlobals.BALANCE < 200)
            )
        );
    }
}
