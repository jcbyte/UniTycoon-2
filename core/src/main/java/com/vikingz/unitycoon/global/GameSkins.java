package com.vikingz.unitycoon.global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/*
This class is to simplify the Skin loading process,
and allow us to make changes without needing to edit every ui element.

 */
public class GameSkins {
    
    // Skins
    private final Skin defaultSkin;
    private final Skin quantumSkin;

    /**
     * Creats a new GameSkins instance, reads skins from asset folder
     */
    public GameSkins(){
        defaultSkin = new Skin(Gdx.files.internal("glassy-ui/skin/glassy-ui.json")); //Default Theme
        quantumSkin = new Skin(Gdx.files.internal("glassy-ui/skin/glassy-ui.json")); //Default Theme
        //quantumSkin = new Skin(Gdx.files.internal("quantum-ui/skin/quantum-horizon-ui.json")); //Quantum Theme
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
