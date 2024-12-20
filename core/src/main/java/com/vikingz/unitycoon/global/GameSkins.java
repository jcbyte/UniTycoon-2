package com.vikingz.unitycoon.global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * This class is to simplify the Skin loading process, and allow us to make changes without
 * needing to edit every UI element.
 */
public class GameSkins {

  // Skins loaded from assets
  private final Skin defaultSkin;

  /**
   * Constructor creates and loads GameSkins from assets files.
   */
  public GameSkins() {
    defaultSkin = new Skin(Gdx.files.internal("glassy-ui/skin/glassy-ui.json"));
  }

  public Skin getDefaultSkin() {
    return defaultSkin;
  }
}
