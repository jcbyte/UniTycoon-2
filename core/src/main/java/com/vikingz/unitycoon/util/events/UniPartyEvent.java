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
                }, "Don't do anything"),
                new Event.Option(() -> {
                    GameGlobals.BALANCE -= 250;
                    GameGlobals.SATISFACTION += 100000;

                    gameScreen.setPaused(false);
                }, "Throw the party\n-250 Money\n+100000 Satisfaction", GameGlobals.BALANCE < 250)
            )
        );
    }
}
