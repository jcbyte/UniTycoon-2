package com.vikingz.unitycoon.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Class to for leaderboard functions.
 */
public class LeaderboardUtil {
  /**
   * Class to represent a leaderboard record.
   */
  public static class LeaderboardRecord {
    private final String name;
    private final int score;

    /**
     * Create blank leaderboard record, this will not be shown.
     */
    public LeaderboardRecord() {
      this.name = null;
      this.score = -1;
    }

    /**
     * Create leaderboard record with specified data.
     */
    public LeaderboardRecord(String name, int score) {
      this.name = name;
      this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null || getClass() != obj.getClass()) {
        return false;
      }

      LeaderboardRecord that = (LeaderboardRecord) obj;
      return score == that.score
          && name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
      int result = name != null ? name.hashCode() : 0;
      result = 31 * result + score;
      return result;
    }
  }

  /**
   * Generate an empty leaderboard, for initialisation.
   *
   * @param no amount of records in the leaderboard
   */
  public static LeaderboardRecord[] generateBlankLeaderboard(int no) {
    return Stream.generate(LeaderboardRecord::new).limit(no).toArray(LeaderboardRecord[]::new);
  }

  /**
   * Sort the leaderboard into score descending order.
   *
   * @param records the leaderboard to sort in place.
   */
  public static void sortLeaderboard(LeaderboardRecord[] records) {
    Arrays.sort(records,
        Comparator.comparing((LeaderboardRecord record) -> record.score).reversed());
  }

  /**
   * Check if this score could be in the leaderboard.
   */
  public static boolean onLeaderboard(LeaderboardRecord[] leaderboard, int score) {
    return leaderboard[leaderboard.length - 1].score < score;
  }

  /**
   * Update the leaderboard with the new record if it is in the top 5 records.
   *
   * @return true if the leaderboard was updated
   */
  public static boolean updateLeaderboard(
      LeaderboardRecord[] leaderboard,
      LeaderboardRecord record
  ) {
    // If score is worse than worst score then no update
    if (!onLeaderboard(leaderboard, record.score)) {
      return false;
    }

    // Go though each score from worst to best
    for (int i = leaderboard.length - 1; i > 0; i--) {
      // If the score is worse than the score above it then place it here
      if (leaderboard[i - 1].score > record.score) {
        leaderboard[i] = record;
        return true;
      } else {
        // If the score is better than the score above it then move the higher up score down
        leaderboard[i] = leaderboard[i - 1];
      }
    }

    // If the for loop ends then this score must be the best so place it at the top
    leaderboard[0] = record;
    return true;
  }

  /**
   * Print the leaderboard nicely formatted.
   */
  public static String leaderboardToString(LeaderboardRecord[] leaderboard) {
    StringBuilder text = new StringBuilder();
    for (LeaderboardUtil.LeaderboardRecord record : leaderboard) {
      if (record.name != null) {
        text.append(record.name).append(": ")
            .append(StatsCalculator.getFormattedSatisfaction(record.score)).append("\n");
      }
    }
    return text.isEmpty() ? "No Records\n" : text.toString();
  }
}
