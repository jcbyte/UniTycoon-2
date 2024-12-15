package com.vikingz.unitycoon.util.events;

import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.screens.GameScreen;

public class LongboiDeathEvent extends Event {
    public LongboiDeathEvent(GameScreen gameScreen) {
        super(() -> {
            int statueCount = (int) gameScreen.getGameRenderer().getBuildingRenderer().getPlaceBuildings().stream()
                .filter(building -> building.getBuildingInfo().getBuildingID().equals("LONGBS"))
                .count();

            return new Event(
                "Your university's mascot, Longboi, has passed away." + (statueCount > 0 ? "\nYou have " + statueCount + " Longboi Statues, so this decrease is reduced." : ""),
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION -= 250000 / (statueCount + 1);

                    gameScreen.setPaused(false);
                }, "-Satisfaction")
            );
        });
    }
}
