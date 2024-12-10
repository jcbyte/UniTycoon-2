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

    public static class Event
    {
        public static class Option
        {
            public final String text;
            public final Runnable action;
            public final boolean disabled;

            public Option(Runnable action, String text)
            {
                this.action = action;
                this.text = text;
                disabled = false;
            }

            public Option(Runnable action, String text, boolean disabled)
            {
                this.action = action;
                this.text = text;
                this.disabled = disabled;
            }
        }

        public final String message;
        public final Option opt1, opt2;
        public final boolean choice;

        public Event(String message, Option opt1, Option opt2)
        {
            this.message = message;
            this.opt1 = opt1;
            this.opt2 = opt2;
            choice = true;
        }

        public Event(String message, Option opt)
        {
            this.message = message;
            this.opt1 = opt;
            this.opt2 = null;
            choice = false;
        }
    }

    public static class CalculatedEvent
    {
        private final Callable<Event> calculateEvent;

        CalculatedEvent(Callable<Event> event)
        {
            calculateEvent = event;
        }

        public Event getEvent()
        {
            try {
                return calculateEvent.call();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class ManagedEvent
    {
        private final Event event;
        private final CalculatedEvent calcEvent;
        private final boolean isEventCalculated;

        public final int timeCalled;
        public boolean called;

        public ManagedEvent(Event event, int timeCalled)
        {
            this.event = event;
            calcEvent = null;
            isEventCalculated = false;
            this.timeCalled = timeCalled;
            this.called = false;
        }

        public ManagedEvent(CalculatedEvent event, int timeCalled)
        {
            this.event = null;
            calcEvent = event;
            isEventCalculated = true;
            this.timeCalled = timeCalled;
            this.called = false;
        }

        public Event getEvent()
        {
            if (isEventCalculated) {
                assert calcEvent != null;
                return calcEvent.getEvent();
            }
            else
                return event;
        }
    }

    private ManagedEvent[] events;

    public EventsManager(GameScreen gameScreen)
    {
        this.gameScreen = gameScreen;
        this.uiRenderer = gameScreen.getUIRenderer();

        Event grantEvent = new Event("You receive a grant from the government.",
            new Event.Option(() -> {
                GameGlobals.BALANCE += 1000;

                gameScreen.setPaused(false);
            }, "Take the cash\n+1000 Money"),
            new Event.Option(() -> {
                GameGlobals.STUDENTS += 300;

                gameScreen.setPaused(false);
            }, "Reduce fees\n+300 Students")
        );

        CalculatedEvent alumniVisitEvent =
            new CalculatedEvent(() -> {
                boolean alumniImpressed = GameGlobals.SATISFACTION > 2000;
                return new Event("Surprise alumni Visit\n" + (alumniImpressed ? "The alumni is impressed with the university and donates" : "The alumni is not impressed and your reputation decreases") + ".",
                    new Event.Option(() -> {
                        if (alumniImpressed)
                            GameGlobals.BALANCE += 1000;
                        else
                            GameGlobals.STUDENTS = Math.max(0, GameGlobals.STUDENTS - 200);

                        gameScreen.setPaused(false);
                    }, alumniImpressed ? "+1000 Money" : "-200 Students")
                );
            });

        Event longboiDiesEvent =
            new Event("Your university's mascot, Longboi, has passed away.",
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION -= 1000;

                    gameScreen.setPaused(false);
                }, "-1000 Satisfaction")
            );

        Event guestLectureEvent =
            new Event("A famous scholar visits, boosting prestige and attracting more students.",
                new Event.Option(() -> {
                    GameGlobals.STUDENTS += 100;

                    gameScreen.setPaused(false);
                }, "+100 Students")
            );

        Event examWeekEvent =
            new Event("It's exam week, students scramble to study, leading to overcrowded libraries and stressed faculty.",
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION -= 500;

                    gameScreen.setPaused(false);
                }, "-500 Satisfaction")
            );

        Event floodingEvent =
            new Event("Major flooding has occurred, a building have been destroyed and morale is low.",
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
            );

        Event earthquakeEvent =
            new Event("An earthquake has occurred and 3 buildings have been destroyed.",
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
            );

        CalculatedEvent rallyEvent = new CalculatedEvent(() ->
            new Event("The students begin to organise a peaceful protest.",
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
        );

        CalculatedEvent partyEvent = new CalculatedEvent(() ->
            new Event("The students want the university to throw a party.",
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

        Event sportsWinEvent =
            new Event("The university's team wins a championship.",
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION += 1000;

                    gameScreen.setPaused(false);
                }, "+1000 Satisfaction")
            );

        Event gambleEvent = new Event("On your day off you go to the casino\nDo you put it all on red?",
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
        );

        CalculatedEvent curriculumChangeEvent = new CalculatedEvent(() ->
            new Event("A faculty member suggests a major change to the university's curriculum to make it more cutting-edge.",
                new Event.Option(() -> {
                    gameScreen.setPaused(false);
                }, "Leave it"),
                new Event.Option(() -> {
                    GameGlobals.BALANCE -= 200;
                    GameGlobals.STUDENTS += 200;

                    gameScreen.setPaused(false);
                }, "Change it\n-200 Money\n+200 Students", GameGlobals.BALANCE < 200)
            )
        );

        Event bigOilDonor = new Event("Big oil wants to donate to the university, however this could be controversial.",
            new Event.Option(() -> {
                GameGlobals.SATISFACTION += 100;

                gameScreen.setPaused(false);
            }, "Dont Accept\n+100 Students"),
            new Event.Option(() -> {
                GameGlobals.BALANCE += 2000;
                GameGlobals.STUDENTS -= 400;

                gameScreen.setPaused(false);
            }, "Accept the money\n+2000 Money\n-400 Students")
        );

        CalculatedEvent staffStrike = new CalculatedEvent(() ->
            new Event("Faculty members threaten to strike over pay.",
                new Event.Option(() -> {
                    GameGlobals.BALANCE -= 500;

                    gameScreen.setPaused(false);
                }, "Increase pay\n-500 Money", GameGlobals.BALANCE < 500),
                new Event.Option(() -> {
                    GameGlobals.SATISFACTION -= 2000;

                    gameScreen.setPaused(false);
                }, "Fire the staff\n-2000 Satisfaction")
            )
        );


        events = new ManagedEvent[] {
            new ManagedEvent(rallyEvent, 13),
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
