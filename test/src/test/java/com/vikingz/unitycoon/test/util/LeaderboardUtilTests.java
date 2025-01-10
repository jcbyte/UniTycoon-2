package com.vikingz.unitycoon.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import com.vikingz.unitycoon.util.LeaderboardUtil;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link LeaderboardUtil}.
 */
public class LeaderboardUtilTests extends AbstractHeadlessGdxTest {
  @Test
  public void testLeaderboardRecordEqual() {
    LeaderboardUtil.LeaderboardRecord l =
        new LeaderboardUtil.LeaderboardRecord("hello", 42);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("hello", 42), l);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("hello", 42).hashCode(),
        l.hashCode());
    assertNotEquals(new LeaderboardUtil.LeaderboardRecord("hiy", 42), l);
    assertNotEquals(new LeaderboardUtil.LeaderboardRecord("hiy", 42).hashCode(),
        l.hashCode());
    assertNotEquals(new LeaderboardUtil.LeaderboardRecord("hello", 12), l);
    assertNotEquals(new LeaderboardUtil.LeaderboardRecord("hello", 12).hashCode(),
        l.hashCode());
    assertNotEquals(null, l);

    LeaderboardUtil.LeaderboardRecord l1 = new LeaderboardUtil.LeaderboardRecord();
    assertEquals(new LeaderboardUtil.LeaderboardRecord(), l1);
    assertEquals(new LeaderboardUtil.LeaderboardRecord().hashCode(), l1.hashCode());
  }

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

  @Test
  public void testOnLeaderboard() {
    LeaderboardUtil.LeaderboardRecord[] l = {
        new LeaderboardUtil.LeaderboardRecord("1", 400),
        new LeaderboardUtil.LeaderboardRecord("2", 200),
        new LeaderboardUtil.LeaderboardRecord("3", 100),
    };
    assertTrue(LeaderboardUtil.onLeaderboard(l, 500));
    assertTrue(LeaderboardUtil.onLeaderboard(l, 300));
    assertTrue(LeaderboardUtil.onLeaderboard(l, 200));
    assertFalse(LeaderboardUtil.onLeaderboard(l, 100));
  }

  @Test
  public void testUpdateLeaderboard() {
    LeaderboardUtil.LeaderboardRecord[] l = {
        new LeaderboardUtil.LeaderboardRecord("1", 400),
        new LeaderboardUtil.LeaderboardRecord("2", 200),
        new LeaderboardUtil.LeaderboardRecord("3", 100),
        new LeaderboardUtil.LeaderboardRecord(),
    };

    assertTrue(LeaderboardUtil.updateLeaderboard(l,
        new LeaderboardUtil.LeaderboardRecord("1b", 300)));
    assertEquals(4, l.length);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("1", 400), l[0]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("1b", 300), l[1]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("2", 200), l[2]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("3", 100), l[3]);

    assertFalse(LeaderboardUtil.updateLeaderboard(l,
        new LeaderboardUtil.LeaderboardRecord("4", 100)));
    assertEquals(4, l.length);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("1", 400), l[0]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("1b", 300), l[1]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("2", 200), l[2]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("3", 100), l[3]);

    assertFalse(LeaderboardUtil.updateLeaderboard(l,
        new LeaderboardUtil.LeaderboardRecord("5", 20)));
    assertEquals(4, l.length);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("1", 400), l[0]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("1b", 300), l[1]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("2", 200), l[2]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("3", 100), l[3]);

    assertTrue(LeaderboardUtil.updateLeaderboard(l,
        new LeaderboardUtil.LeaderboardRecord("0", 400)));
    assertEquals(4, l.length);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("0", 400), l[0]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("1", 400), l[1]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("1b", 300), l[2]);
    assertEquals(new LeaderboardUtil.LeaderboardRecord("2", 200), l[3]);
  }

  @Test
  public void testLeaderboardToString() {
    LeaderboardUtil.LeaderboardRecord[] l = {
        new LeaderboardUtil.LeaderboardRecord("1", 1_000_000),
        new LeaderboardUtil.LeaderboardRecord("2", 200_000),
        new LeaderboardUtil.LeaderboardRecord("3", 50_000),
        new LeaderboardUtil.LeaderboardRecord(),
        new LeaderboardUtil.LeaderboardRecord(),
    };
    assertEquals("1: 10.00%\n2: 2.00%\n3: 0.50%\n", LeaderboardUtil.leaderboardToString(l));

    LeaderboardUtil.LeaderboardRecord[] l2 = {
        new LeaderboardUtil.LeaderboardRecord(),
        new LeaderboardUtil.LeaderboardRecord(),
    };
    assertEquals("No Records\n", LeaderboardUtil.leaderboardToString(l2));

  }
}
