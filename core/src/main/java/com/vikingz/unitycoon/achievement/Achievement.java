package com.vikingz.unitycoon.achievement;

import com.badlogic.gdx.graphics.Texture;
import java.util.concurrent.Callable;

/**
 * Class representing an achievement object and information on how to calculate and give rewards.
 */
public class Achievement {
  public final String name;
  private boolean got;
  private final Callable<Boolean> has;

  public final Texture logo;

  public final Runnable reward;
  public final String rewardText;

  /**
   * Generate a new achievement object with given data.
   */
  public Achievement(String name,
                     Callable<Boolean> has,
                     Texture logo,
                     Runnable reward,
                     String rewardText
  ) {
    this.name = name;
    got = false;
    this.has = has;
    this.logo = logo;
    this.reward = reward;
    this.rewardText = rewardText;
  }

  /**
   * Check if the achievement has been achieved.
   */
  public boolean hasAchieved() {
    return got;
  }

  /**
   * Calculate if the achievement has been reached.
   *
   * @return true only the first time it is reached
   */
  public boolean calculate() {
    if (got) {
      return false;
    }

    try {
      got = has.call();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return got;
  }
}
