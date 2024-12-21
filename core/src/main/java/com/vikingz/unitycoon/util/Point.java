package com.vikingz.unitycoon.util;

/**
 * This class represents a simple point in 2D space.
 *
 * <p>Mainly used as a return type for collision and translation calculations throughout the
 * project.
 */
public class Point {
  private float posX;
  private float posY;

  /**
   * Creates a new Point.
   */
  public Point(float x, float y) {
    this.posX = x;
    this.posY = y;
  }

  public float getX() {
    return posX;
  }

  public void setX(float x) {
    this.posX = x;
  }

  public float getY() {
    return posY;
  }

  public void setY(float y) {
    this.posY = y;
  }
}
