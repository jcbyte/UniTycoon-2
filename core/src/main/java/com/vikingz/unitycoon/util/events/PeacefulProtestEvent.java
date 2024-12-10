package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class PeacefulProtestEvent extends Event {
    public PeacefulProtestEvent(GameScreen gameScreen) {
        super(() ->
            new Event(
                "The students begin to organise a peaceful protest.",
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION -= 50000;

                    gameScreen.setPaused(false);
                }, "Shut them down\n-50000 Satisfaction"),
                new Event.Option(() -> {
                    GameGlobals.BALANCE -= 100;
                    GameGlobals.SATISFACTION += 50000;

                    gameScreen.setPaused(false);
                }, "Fund the rally\n-100 Money\n+50000 Satisfaction", GameGlobals.BALANCE < 100)
            )
        );
    }
}
