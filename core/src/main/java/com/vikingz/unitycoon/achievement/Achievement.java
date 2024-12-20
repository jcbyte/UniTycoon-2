package com.vikingz.unitycoon.achievement;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.Callable;

public class Achievement
{
    public String name;
    private boolean got;
    private Callable<Boolean> has;

    public Texture logo;

    public Runnable reward;
    public String rewardText;

    public Achievement(String name, Callable<Boolean> has, Texture logo, Runnable reward, String rewardText)
    {
        this.name = name;
        got = false;
        this.has = has;
        this.logo = logo;
        this.reward = reward;
        this.rewardText = rewardText;
    }

    /**
     * CHeck if the achievement has been archived
     */
    public boolean hasAchieved()
    {
        return got;
    }

    /**
     * Calculate if the achievement has been reached
     * @return true only the first time it is reached
     */
    public boolean calculate() {
        if (got)
            return false;

        try {
            got = has.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return got;
    }
}
