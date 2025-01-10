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
}
