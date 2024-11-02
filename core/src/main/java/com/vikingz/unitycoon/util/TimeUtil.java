package com.vikingz.unitycoon.util;

public class TimeUtil {
    
    public static class Time{

        public int secs;
        public int mins;
        public int hrs;
    
        public Time(int secs, int mins, int hrs){
            this.secs = secs;
            this.mins = mins;
            this.hrs = hrs;
        }
    
        public Time(){
            this.secs = 0;
            this.mins = 0;
            this.hrs = 0;
        }

        public String toString(){
            return String.format("%02d", mins) + ":" + String.format("%02d", secs);
        }
    
    }


    public static Time secondsToMinSecs(int secs){
        

        int m = secs/ 60;
        int s = secs % 60;

        return new Time(s, m, 0);
        
    }

}

