package com.vikingz.unitycoon.util;

import com.vikingz.unitycoon.global.GameConfig;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class LeaderboardManager {
    /**
     * Class to represent a leaderboard record
     */
    public static class LeaderboardRecord {
        public String name;
        public int score;

        public LeaderboardRecord() {
            this.name = null;
            this.score = -1;
        }

        public LeaderboardRecord(String name, int score) {
            this.name = name;
            this.score = score;
        }
    };

    public static LeaderboardRecord[] generateBlankLeaderboard(int no)
    {
        return Stream.generate(LeaderboardRecord::new).limit(5).toArray(LeaderboardRecord[]::new);
    }

    public static void sortLeaderboard(LeaderboardRecord[] records)
    {
        Arrays.sort(records, Comparator.comparing(record -> record.score));
    }

    /**
     * Check if this score could be in the leaderboard
     */
    public static boolean onLeaderboard(LeaderboardRecord[] leaderboard, int score)
    {
        return leaderboard[leaderboard.length - 1].score < score;
    }

    /**
     * Update the leaderboard with the new record if it is in the top 5 records
     * @return true if the leaderboard was updated
     */
    public static boolean updateLeaderboard(LeaderboardRecord[] leaderboard, LeaderboardRecord record) {
        // If score is worse than worst score then no update
        if (!onLeaderboard(leaderboard, record.score))
        {
            return false;
        }

        // Go though each score from worst to best
        for (int i = leaderboard.length - 1; i > 0; i--) {
            // If the score is worse than the score above it then place it here
            if (leaderboard[i - 1].score > record.score) {
                leaderboard[i] = record;
                return true;
            }
            // If the score is better than the score above it then move the higher up score down
            else
            {
                leaderboard[i] = leaderboard[i - 1];
            }
        }

        // If the for loop ends then this score must be the best so place it at the top
        leaderboard[0] = record;
        return true;
    }

    public static String LeaderboardToString(LeaderboardRecord[] leaderboard)
    {
        StringBuilder text = new StringBuilder();
        for (LeaderboardManager.LeaderboardRecord lRec : leaderboard)
        {
            if (lRec.name != null)
            {
                text.append(lRec.name).append(": ").append(lRec.score).append("\n");
            }
        }
        return text.isEmpty() ? "No Records\n" : text.toString();
    }
}
