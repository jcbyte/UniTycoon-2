package com.vikingz.unitycoon.util;

/**
 * This class is used to calculate different statistics such as satisfaction and balance.
 */
public class StatsCalculator {
  public static final int MAX_SATISFACTION = 10_000_000;

  /**
   * Calculates satisfaction gained per second.
   *
   * @param amountOfStudents       Amount of students in the game
   * @param satisfactionMultiplier Satisfaction multiplier
   * @return Int Amount of satisfaction gained
   */
  public static int calculateSatisfaction(int amountOfStudents, float satisfactionMultiplier) {
    return Math.round(amountOfStudents * satisfactionMultiplier);
  }

  /**
   * Calculates the profit made per second.
   *
   * @param coinsPerSecond The amount of coins a building makes per second
   * @return Float The amount of coins made per second
   */
  public static float calculateProfitMade(float coinsPerSecond) {
    return coinsPerSecond;
  }

  /**
   * Return the formatted satisfaction (convert to percentage).
   */
  public static String getFormattedSatisfaction(int satisfaction) {
    int clampedSatisfaction = Math.min(Math.max(satisfaction, 0), MAX_SATISFACTION);
    return String.format("%.2f", ((float) clampedSatisfaction / MAX_SATISFACTION) * 100) + "%";
  }
}
