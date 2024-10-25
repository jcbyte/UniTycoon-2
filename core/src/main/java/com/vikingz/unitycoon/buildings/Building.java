package com.vikingz.unitycoon.buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public abstract class Building {
    private Texture textureBuilding;
    private float x;
    private float y;
    private int width;
    private int height;


    public void building(int width, int height){
        this.width = width;
        this.height = height;
    }

    public Texture getTextureBuilding() {
        return textureBuilding;
    }

    public void setTextureBuilding(Texture textureBuilding) {
        this.textureBuilding = textureBuilding;
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
