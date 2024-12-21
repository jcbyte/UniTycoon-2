package com.vikingz.unitycoon.util;

/**
 * This class contains functions that we use to format time.
 *
 * <p>Contains an inner class {@code Time} simply just to serve as a return type once formatting
 * is done.
 */
public class TimeUtil {

  /**
   * This class contains secs and mins.
   *
   * <p>Only serves as a return type
   */
  public static class Time {

    private final int secs;
    private final int mins;

    /**
     * Create time object with specified data.
     */
    public Time(int secs, int mins) {
      this.secs = secs;
      this.mins = mins;
    }

    /**
     * Returns the time in a min:second format where each value will always be padded to 2 digits.
     */
    public String toString() {
      return String.format("%02d:%02d", mins, secs);
    }
  }

  /**
   * Format time from seconds to minutes and seconds.
   *
   * @param secs Time in seconds
   * @return Time in minutes and seconds
   */
  public static Time secondsToMinSecs(int secs) {
    int m = secs / 60;
    int s = secs % 60;

    return new Time(s, m);
  }
}
