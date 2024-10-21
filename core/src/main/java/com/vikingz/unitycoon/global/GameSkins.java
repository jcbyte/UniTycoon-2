package com.vikingz.unitycoon.global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/*
This class is to simplify the Skin loading process,
and allow us to make changes without needing to edit every ui element.
Format:
Constructor - Loads Skins
Getters - Gets Skin using function
 */
public class GameSkins {
    private final Skin defaultSkin;
    private final Skin quantumSkin;

    public GameSkins(){
        defaultSkin = new Skin(Gdx.files.internal("ui/glassy-ui.json")); //Default Theme
        quantumSkin = new Skin(Gdx.files.internal("quantum-ui/skin/quantum-horizon-ui.json")); //Quantum Theme
    }


    //Getters


    //Default Getter
    public Skin getDefaultSkin(){
        return defaultSkin;
    }

    //Quantum Getter
    public Skin getQuantumSkin(){
        return quantumSkin;
    }





}
