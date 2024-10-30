package com.vikingz.unitycoon.util;

public class StatsCalculator {

    public static int calculateSatisfaction(int amountOfStudents, float satisfactionMultiplier){
        return Math.round(amountOfStudents * satisfactionMultiplier);
    }


    public static float calcuateProfitMade(float coinsPerSecond){
        return coinsPerSecond;
    }

}
