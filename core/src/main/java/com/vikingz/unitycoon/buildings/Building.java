package com.vikingz.unitycoon.buildings;

import com.badlogic.gdx.graphics.Texture;

public abstract class Building {

    private Texture textureBuilding;
    private float x;
    private float y;


    public Building(float x, float y, Texture texture){
        this.x = x;
        this.y = y;
        this.textureBuilding = texture;
    }


    public void building(String ImageAsset){
        this.textureBuilding = new Texture(ImageAsset);
    }

    public Texture getTextureBuilding() {
        return textureBuilding;
    }

    public void setTextureBuilding(Texture textureBuilding) {
        this.textureBuilding = textureBuilding;
    }
}
