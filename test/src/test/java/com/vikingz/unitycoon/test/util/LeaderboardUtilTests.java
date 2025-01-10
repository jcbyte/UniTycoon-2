package com.vikingz.unitycoon.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.LeaderboardUtil;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link LeaderboardUtil}.
 */
public class LeaderboardUtilTests extends AbstractHeadlessGdxTest {
  @Test
  public void testGenerateBlankLeaderboard() {
    LeaderboardUtil.LeaderboardRecord[] l = LeaderboardUtil.generateBlankLeaderboard(3);
    assertEquals(3, l.length);
    LeaderboardUtil.LeaderboardRecord[] l1 = LeaderboardUtil.generateBlankLeaderboard(0);
    assertEquals(0, l1.length);
  }

  @Test
  public void testSortLeaderboard() {
    LeaderboardUtil.LeaderboardRecord[] l = {
        new LeaderboardUtil.LeaderboardRecord("3", 100),
        new LeaderboardUtil.LeaderboardRecord(),
        new LeaderboardUtil.LeaderboardRecord("1", 400),
        new LeaderboardUtil.LeaderboardRecord("2", 200),
    };
    LeaderboardUtil.sortLeaderboard(l);
    assertEquals(4, l.length);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("1", 400), l[0]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("2", 200), l[1]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("3", 100), l[2]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord(), l[3]);
  }
}
