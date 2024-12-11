package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class ExamWeekEvent extends Event {
    public ExamWeekEvent(GameScreen gameScreen) {
        super(
            "It's exam week, students are stressed and scramble\n to study leading to overcrowded libraries.",
            new Event.Option(() -> {
                GameGlobals.SATISFACTION -= 100000;

                gameScreen.setPaused(false);
            }, "-100000 Satisfaction")
        );
    }
}
