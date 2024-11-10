package com.vikingz.unitycoon.util;

/**
 * This class represents a simple point in 2D space
 *
 * Mainly used as a return type for collision and translation
 * calculations throughout the project
 */
public class Point {

    /**
     * Makes it easier to create function that returns both x and y
     * such as the snapToGrid function in BuildingsRenderer.
     */
    float x, y;

    /**
     * Creates a new Point
     * @param x New x
     * @param y New y
     */
    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Creates an empty point where the values
     * are both set to 0.
     */
    public Point(){
        this.x = 0;
        this.y = 0;
    }



    // Getters and setters

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }




}
