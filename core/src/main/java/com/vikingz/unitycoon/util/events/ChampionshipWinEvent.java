package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class ChampionshipWinEvent extends Event {
    public ChampionshipWinEvent(GameScreen gameScreen) {
        super(
            "The university's team wins a championship.",
            new Event.Option(() -> {
                GameGlobals.SATISFACTION += 1000;

                gameScreen.setPaused(false);
            }, "+1000 Satisfaction")
        );
    }
}
