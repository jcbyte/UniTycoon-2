package com.vikingz.unitycoon.util;

/**
 * This class contains functions that we use to format time.
 * 
 * Contains an inner class {@code Time} simply just to serve as
 * a return type once the formatting is done
 */
public class TimeUtil {
    
    /**
     * This class contains three attributes secs, mins and hrs.
     * 
     * Only serves as a return type
     */
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

    /**
     * Format time from seconds to minutes and seconds
     * @param secs Time in seconds
     * @return Time in minutes and seconds
     */
    public static Time secondsToMinSecs(int secs){
        

        int m = secs/ 60;
        int s = secs % 60;

        if(m > 100000){
            return null;
        }

        return new Time(s, m, 0);
        
    }

}

