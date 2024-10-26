package com.vikingz.unitycoon.util;

public class Point {
    
    /**
     * Makes it easier to create fucntion that returns both x and y
     * such as the snapToGrid function in BuldingsRenderer.
     */
    float x, y;

    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

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
