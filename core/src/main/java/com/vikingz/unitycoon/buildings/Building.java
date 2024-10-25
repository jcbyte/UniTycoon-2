package com.vikingz.unitycoon.buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public abstract class Building {
    private Texture textureBuilding;
    private float x;
    private float y;
    private float width;
    private float height;


    public Building(float x, float y, Texture textureBuilding){
        this.x = x;
        this.y = y;
        this.width = 64;
        this.height = 64;
        this.textureBuilding = textureBuilding;
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
