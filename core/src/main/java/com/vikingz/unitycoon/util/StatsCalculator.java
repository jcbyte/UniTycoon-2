package com.vikingz.unitycoon.util;

/**
 * This class is used to calculate different statistics such as
 * satisfaction and balance.
 */
public class StatsCalculator {

    /**
     * Calculates satisfaction gained per second
     * @param amountOfStudents Ammout of students in the game
     * @param satisfactionMultiplier Satisfaction multiplier
     * @return Int Amount of satisfaction gained
     */
    public static int calculateSatisfaction(int amountOfStudents, float satisfactionMultiplier){
        return Math.round(amountOfStudents * satisfactionMultiplier);
    }


    /**
     * Calculates the profit made per second
     * @param coinsPerSecond The ammout of coins a building makes per second 
     * @return Float The ammout of coins made per second
     */
    public static float calculateProfitMade(float coinsPerSecond){
        return coinsPerSecond;
    }

}
