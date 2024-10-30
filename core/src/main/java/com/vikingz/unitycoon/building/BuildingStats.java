package com.vikingz.unitycoon.building;

public class BuildingStats {
 

    public enum BuildingType {
        NONE,
        ACADEMIC,
        ACCOMODATION,
        RECREATIONAL,
        FOOD,
    
    }
    
    
    public enum BuildingID {
    
        // Academic
        PZA,
        RCH,
        
        // Accomodation
        KATO,
        LISTER,
    
        // Recreational 
        YSV, // York sports village

        // Food
        MCD, // Mc Donalds?
    
    }


    // Academic
    public static int PZA_COST = 100;
    public static float PZA_SATISFACTION_MULT = 1.1f;

    public static int RCH_COST = 100;
    public static float RCH_SATISFACTION_MULT = 1.1f;


    // Accomodation
    public static int KATO_COST = 100;
    public static float KATO_SATISFACTION_MULT = 1.1f;
    public static int KATO_STUDENT_CAPACITY = 100;

    public static int LISTER_COST = 100;
    public static float LISTER_SATISFACTION_MULT = 1.1f;
    public static int LISTER_STUDENT_CAPACITY = 150;

    
    // Recreational
    public static int YSV_COST = 100;
    public static float YSV_SATISFACTION_MULT = 1.5f;
    public static float YSV_COINS_PER_SECOND = 10;
    


    // Food
    public static int MCD_COST = 100;
    public static float MCD_SATISFACTION_MULT = 1.1f;
    public static float MCD_COINS_PER_SECOND = 10;




    public static BuildingInfo getInfo(BuildingID id){


        switch (id) {
            // Academic
            case PZA:
                return new BuildingInfo(id, BuildingType.ACADEMIC, PZA_COST, PZA_SATISFACTION_MULT);

            case RCH:
                return new BuildingInfo(id, BuildingType.ACADEMIC, RCH_COST, RCH_SATISFACTION_MULT);

            // Accomodation
            case KATO:
                return new BuildingInfo(id, BuildingType.ACCOMODATION, KATO_COST, PZA_SATISFACTION_MULT, KATO_STUDENT_CAPACITY);

            case LISTER:
                return new BuildingInfo(id, BuildingType.ACCOMODATION, LISTER_COST, PZA_SATISFACTION_MULT, LISTER_STUDENT_CAPACITY);

            // Recreational
            case YSV:
                return new BuildingInfo(id, BuildingType.RECREATIONAL, YSV_COST, YSV_SATISFACTION_MULT, YSV_COINS_PER_SECOND);

            // Food
            case MCD:
                return new BuildingInfo(id, BuildingType.FOOD, MCD_COST, MCD_SATISFACTION_MULT, MCD_COINS_PER_SECOND);

        }

        return null;

    }


}
