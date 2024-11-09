package com.vikingz.unitycoon.util;

/**
 * This class is used to calculate different statistics such as
 * satisfaction and balance.
 */
public class StatsCalculator {

    public static int calculateSatisfaction(int amountOfStudents, float satisfactionMultiplier){
        return Math.round(amountOfStudents * satisfactionMultiplier);
    }


    public static float calculateProfitMade(float coinsPerSecond){
        return coinsPerSecond;
    }

}
