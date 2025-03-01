package com.vikingz.unitycoon.util;

/**
 * Class representing a pair of items.
 */
public class Pair<T, U> {
  public T first;
  public U second;

  /**
   * Initialise the pair with data.
   */
  public Pair(T first, U second) {
    this.first = first;
    this.second = second;
  }
}
