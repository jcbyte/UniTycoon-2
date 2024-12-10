package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class UniPartyEvent extends Event {
    public UniPartyEvent(GameScreen gameScreen) {
        super(() ->
            new Event(
                "The students want the university to throw a party.",
                new Event.Option(() -> {
                    gameScreen.setPaused(false);
                }, "Don't Throw the party"),
                new Event.Option(() -> {
                    GameGlobals.BALANCE -= 500;
                    GameGlobals.SATISFACTION += 1000;

                    gameScreen.setPaused(false);
                }, "Throw the party\n-500 Money\n+1000 Satisfaction", GameGlobals.BALANCE < 500)
            )
        );
    }
}
