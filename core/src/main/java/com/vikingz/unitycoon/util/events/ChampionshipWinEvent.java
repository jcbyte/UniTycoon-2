package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class ChampionshipWinEvent extends Event {
    public ChampionshipWinEvent(GameScreen gameScreen) {
        super(
            "The university team wins a championship.",
            new Event.Option(() -> {
                GameGlobals.SATISFACTION += 100000;

                gameScreen.setPaused(false);
            }, "+Satisfaction")
        );
    }
}
