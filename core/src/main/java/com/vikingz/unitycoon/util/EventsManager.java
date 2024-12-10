package com.vikingz.unitycoon.util;

import com.badlogic.gdx.math.MathUtils;
import com.vikingz.unitycoon.building.Building;
import com.vikingz.unitycoon.global.GameConfig;
import com.vikingz.unitycoon.global.GameGlobals;
import com.vikingz.unitycoon.render.BuildingRenderer;
import com.vikingz.unitycoon.render.GameRenderer;
import com.vikingz.unitycoon.render.UIRenderer;
import com.vikingz.unitycoon.screens.GameScreen;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class EventsManager {
    UIRenderer uiRenderer;
    GameScreen gameScreen;

    public static class ManagedEvent
    {
        private final Event event;

        public final int timeCalled;
        public boolean called;

        public ManagedEvent(Event event, int timeCalled)
        {
            this.event = event;
            this.timeCalled = timeCalled;
            this.called = false;
        }

        public Event getEvent()
        {
            return event.getEvent();
        }
    }

    private ManagedEvent[] events;

    public EventsManager(GameScreen gameScreen)
    {
        this.gameScreen = gameScreen;
        this.uiRenderer = gameScreen.getUIRenderer();

        Event[] goodEvents = new Event[] {
            new Event(
                "You receive a grant from the government.",
                new Event.Option(() -> {
                    GameGlobals.BALANCE += 1000;

                    gameScreen.setPaused(false);
                }, "Take the cash\n+1000 Money"),
                new Event.Option(() -> {
                    GameGlobals.STUDENTS += 300;

                    gameScreen.setPaused(false);
                }, "Reduce fees\n+300 Students")
            ),
            new Event(
                "A famous scholar visits, boosting prestige and attracting more students.",
                new Event.Option(() -> {
                    GameGlobals.STUDENTS += 100;

                    gameScreen.setPaused(false);
                }, "+100 Students")
            ),
            new Event(
                "The university's team wins a championship.",
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION += 1000;

                    gameScreen.setPaused(false);
                }, "+1000 Satisfaction")
            ),
        };

        Event[] badEvents = new Event[] {
            new Event(
                "Your university's mascot, Longboi, has passed away.",
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION -= 1000;

                    gameScreen.setPaused(false);
                }, "-1000 Satisfaction")
            ),
            new Event(
                "It's exam week, students scramble to study, leading to overcrowded libraries and stressed faculty.",
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION -= 500;

                    gameScreen.setPaused(false);
                }, "-500 Satisfaction")
            ),
            new Event(
                "Major flooding has occurred, a building have been destroyed and morale is low.",
                new Event.Option(() -> {
                    BuildingRenderer buildingRenderer = gameScreen.getGameRenderer().getBuildingRenderer();
                    List<Building> placedBuildings = buildingRenderer.getPlaceBuildings();

                    if (!placedBuildings.isEmpty()) {
                        int randomIndex = MathUtils.random(placedBuildings.size() - 1);
                        buildingRenderer.removeBuilding(placedBuildings.get(randomIndex));
                    }

                    GameGlobals.STUDENTS = Math.max(0, GameGlobals.STUDENTS - 100);

                    gameScreen.setPaused(false);
                }, "-1 Building\n-100 Students")
            ),
            new Event(
                "An earthquake has occurred and 3 buildings have been destroyed.",
                new Event.Option(() -> {
                    BuildingRenderer buildingRenderer = gameScreen.getGameRenderer().getBuildingRenderer();
                    List<Building> placedBuildings = buildingRenderer.getPlaceBuildings();

                    for (int i = 0; i < 3; i++)
                    {
                        if (placedBuildings.isEmpty())
                            break;

                        int randomIndex = MathUtils.random(placedBuildings.size() - 1);
                        buildingRenderer.removeBuilding(placedBuildings.get(randomIndex));
                    }

                    gameScreen.setPaused(false);
                }, "-3 Buildings")
            ),
        };

        Event[] neutralEvents = new Event[] {
            new Event(() -> {
                boolean alumniImpressed = GameGlobals.SATISFACTION > 2000;
                return new Event(
                    "Surprise alumni Visit\n" + (alumniImpressed ? "The alumni is impressed with the university and donates" : "The alumni is not impressed and your reputation decreases") + ".",
                    new Event.Option(() -> {
                        if (alumniImpressed)
                            GameGlobals.BALANCE += 1000;
                        else
                            GameGlobals.STUDENTS = Math.max(0, GameGlobals.STUDENTS - 200);

                        gameScreen.setPaused(false);
                    }, alumniImpressed ? "+1000 Money" : "-200 Students")
                );
            }),
            new Event(() ->
                new Event(
                    "The students begin to organise a peaceful protest.",
                    new Event.Option(() -> {
                        GameGlobals.SATISFACTION -= 1000;

                        gameScreen.setPaused(false);
                    }, "Shut them down\n-1000 Satisfaction"),
                    new Event.Option(() -> {
                        GameGlobals.BALANCE -= 500;
                        GameGlobals.SATISFACTION += 1000;

                        gameScreen.setPaused(false);
                    }, "Fund the rally\n-500 Money\n+1000 Satisfaction", GameGlobals.BALANCE < 500)
                )
            ),
            new Event(() ->
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
            ),
            new Event(
                "On your day off you go to the casino\nDo you put it all on red?",
                new Event.Option(() -> {
                    gameScreen.setPaused(false);
                }, "Dont gamble."),
                new Event.Option(() -> {
                    if (MathUtils.randomBoolean())
                        GameGlobals.BALANCE *= 2;
                    else
                        GameGlobals.BALANCE = 0;

                    gameScreen.setPaused(false);
                }, "All on red\n0 Money or 2x Money")
            ),
            new Event(() ->
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
            ),
            new Event(
                "Big oil wants to donate to the university, however this could be controversial.",
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION += 100;

                    gameScreen.setPaused(false);
                }, "Dont Accept\n+100 Students"),
                new Event.Option(() -> {
                    GameGlobals.BALANCE += 2000;
                    GameGlobals.STUDENTS -= 400;

                    gameScreen.setPaused(false);
                }, "Accept the money\n+2000 Money\n-400 Students")
            ),
            new Event(() ->
                new Event(
                    "Faculty members threaten to strike over pay.",
                    new Event.Option(() -> {
                        GameGlobals.BALANCE -= 500;

                        gameScreen.setPaused(false);
                    }, "Increase pay\n-500 Money", GameGlobals.BALANCE < 500),
                    new Event.Option(() -> {
                        GameGlobals.SATISFACTION -= 2000;

                        gameScreen.setPaused(false);
                    }, "Fire the staff\n-2000 Satisfaction")
                )
            ),
        };

        // todo move events to own classes
        // todo create static event
        // todo balance the events

        events = new ManagedEvent[] {

        };
    }

    public void render()
    {
        for (ManagedEvent event : events)
        {
            if (GameGlobals.ELAPSED_TIME <= event.timeCalled)
            {
                if (!event.called) {
                    gameScreen.setPaused(true);
                    uiRenderer.showEvent(event.getEvent());
                    event.called = true;
                }
            }
        }
    }

}
