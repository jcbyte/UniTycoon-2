package com.vikingz.unitycoon.test;

import static org.mockito.Mockito.mock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.BeforeEach;

/**
 * Abstract base class for running headless tests in a LibGDX environment.
 * This class sets up a mocked OpenGL {@link GL20} context and initializes the headless backend
 * before each test.
 */
public abstract class AbstractHeadlessGdxTest {
  /**
   * Sets up the necessary environment for headless testing.
   * Mocks the {@link GL20} interface to prevent OpenGL-related errors during tests then
   * initialises the headless application launcher.
   *
   * <p>This method will run before each test method in subclasses.
   */
  @BeforeEach
  public void setup() {
    Gdx.gl = Gdx.gl20 = mock(GL20.class);
    HeadlessLauncher.main(new String[0]);
  }
}
