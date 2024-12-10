package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class ExamWeekEvent extends Event {
    public ExamWeekEvent(GameScreen gameScreen) {
        super(
            "It's exam week, students scramble to study, leading to overcrowded libraries and stressed faculty.",
            new Event.Option(() -> {
                GameGlobals.SATISFACTION -= 500;

                gameScreen.setPaused(false);
            }, "-500 Satisfaction")
        );
    }
}
