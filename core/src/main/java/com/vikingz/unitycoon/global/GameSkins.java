package com.vikingz.unitycoon.global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/*
This class is to simplify the Skin loading process,
and allow us to make changes without needing to edit every ui element.

 */
public class GameSkins {

    // Skins loaded from assets
    private final Skin defaultSkin;
    private final Skin quantumSkin;

    /**
     * Constructor creates and loads GameSkins from assets files
     */
    public GameSkins(){
        defaultSkin = new Skin(Gdx.files.internal("glassy-ui/skin/glassy-ui.json")); //Default Theme Glassy ui
        quantumSkin = new Skin(Gdx.files.internal("quantum-ui/skin/quantum-horizon-ui.json")); //Theme Quantum Horizon ui
    }


    //Getters


    //Default Skin Getter
    public Skin getDefaultSkin(){
        return defaultSkin;
    }

    //Quantum Skin Getter
    public Skin getQuantumSkin(){
        return quantumSkin;
    }





}
