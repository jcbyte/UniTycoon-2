package com.vikingz.unitycoon.util;

public class StatsCalculator {

    public static int calculateSatisfaction(int ammountOfStudents, float satisfactionMultiplier){

        return Math.round(ammountOfStudents * satisfactionMultiplier);

    }


}
