package com.vikingz.unitycoon.building;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vikingz.unitycoon.util.Point;
import com.vikingz.unitycoon.util.StatsCalculator;

/**
 * Abstract class that represents all the buildings in the game.
 */
public abstract class Building {

  // Building drawing properties
  private TextureRegion texture;
  private float posX;
  private float posY;
  private float width;
  private float height;

  // Building functional properties
  private final BuildingStats.BuildingType buildingType;
  private final float satisfactionMultiplier;
  private final BuildingInfo buildingInfo;

  /**
   * Creates a new Building.
   *
   * @param texture      Texture
   * @param x            X
   * @param y            Y
   * @param buildingInfo Building Info
   */
  public Building(TextureRegion texture, float x, float y, BuildingInfo buildingInfo) {
    this.posX = x;
    this.posY = y;
    this.width = 128;
    this.height = 128;
    this.texture = texture;
    this.buildingType = buildingInfo.getBuildingType();
    this.satisfactionMultiplier = buildingInfo.getSatisfactionMultiplier();
    this.buildingInfo = buildingInfo;
  }

  /**
   * Creates a new Building.
   *
   * @param texture      Texture
   * @param p            Point p
   * @param buildingInfo Building Info
   */
  public Building(TextureRegion texture, Point p, BuildingInfo buildingInfo) {
    this.posX = p.getX();
    this.posY = p.getY();
    this.width = 128;
    this.height = 128;
    this.texture = texture;
    this.buildingType = buildingInfo.getBuildingType();
    this.satisfactionMultiplier = buildingInfo.getSatisfactionMultiplier();
    this.buildingInfo = buildingInfo;
  }

  /**
   * Calculates satisfaction logic.
   *
   * @param numberOfStudents Number of students
   * @return Amount of satisfaction
   */
  public int calculateSatisfaction(int numberOfStudents) {
    return StatsCalculator.calculateSatisfaction(numberOfStudents, this.satisfactionMultiplier);
  }

  /**
   * Gets the building information.
   */
  public BuildingInfo getBuildingInfo() {
    return buildingInfo;
  }

  /**
   * Get the building width in pixels.
   */
  public float getWidth() {
    return width;
  }

  /**
   * Set the pixel width of the building.
   */
  public void setWidth(float width) {
    this.width = width;
  }

  /**
   * Get the building height in pixels.
   */
  public float getHeight() {
    return height;
  }

  /**
   * Set the pixel height of the building.
   */
  public void setHeight(float height) {
    this.height = height;
  }


  /**
   * Returns the current texture of the building used for seeing a preview of a building and
   * rendering.
   *
   * @return TextureRegion Image of the building
   */
  public TextureRegion getTexture() {
    return texture;
  }

  /**
   * Sets the Texture of the building.
   *
   * @param textureBuilding Image of the building
   */
  public void setTexture(TextureRegion textureBuilding) {
    this.texture = textureBuilding;
  }

  /**
   * Gets the current middle point of the building X coordinate.
   */
  public float getX() {
    return posX;
  }

  /**
   * Sets the x coordinate of the building.
   */
  public void setX(float x) {
    this.posX = x;
  }

  /**
   * Gets the current middle point of the building Y coordinate.
   */
  public float getY() {
    return posY;
  }

  /**
   * Sets the y coordinate of the building.
   */
  public void setY(float y) {
    this.posY = y;
  }

  /**
   * Gets the buildings buildingType ENUM.
   *
   * @return BuildingStats.BuildingType ENUM value
   */
  public BuildingStats.BuildingType getBuildingType() {
    return buildingType;
  }


  /**
   * Display the class as a string.
   */
  public String toString() {
    String str = "";

    str += "x: " + this.posX;
    str += " y: " + this.posY;
    str += " width: " + this.width;
    str += " height: " + this.height;
    str += " type: " + this.buildingType;

    return str;
  }
}
