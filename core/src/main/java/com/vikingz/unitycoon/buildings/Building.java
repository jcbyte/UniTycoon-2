package com.vikingz.unitycoon.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Building {
    private TextureRegion texture;
    private float x;
    private float y;
    private float width;
    private float height;


    public Building(TextureRegion texture, float x, float y){
        this.x = x;
        this.y = y;
        this.width = 64;
        this.height = 64;
        this.texture = texture;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion textureBuilding) {
        this.texture = textureBuilding;
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
