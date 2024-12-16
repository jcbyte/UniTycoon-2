package com.vikingz.unitycoon.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class CurriculumChangeEvent extends Event {
    public CurriculumChangeEvent(GameScreen gameScreen) {
        super(() ->
            new Event(
                "A faculty member suggests a major change to the university's\ncurriculum to make it more cutting-edge.",
                new Event.Option(() -> {
                    gameScreen.setPaused(false);
                }, "Leave it"),
                new Event.Option(() -> {
                    GameGlobals.BALANCE -= 300;
                    GameGlobals.STUDENTS += 250;

                    gameScreen.setPaused(false);
                }, "Change it\n-300 Money\n+250 Students", GameGlobals.BALANCE < 300)
            )
        );
    }
}
