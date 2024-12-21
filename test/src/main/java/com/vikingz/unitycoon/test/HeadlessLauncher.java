package com.vikingz.unitycoon.test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.vikingz.unitycoon.Main;

/**
 * Launches the headless application.
 */
public class HeadlessLauncher {
  public static void main(String[] args) {
    createApplication();
  }

  private static Application createApplication() {
    // Note: you can use a custom ApplicationListener implementation for the headless project instead of Gemo.
    return new HeadlessApplication(new Main(), getDefaultConfiguration());
  }

  private static HeadlessApplicationConfiguration getDefaultConfiguration() {
    HeadlessApplicationConfiguration configuration = new HeadlessApplicationConfiguration();
    // When this value is negative, render() is never called.
    configuration.updatesPerSecond = -1;

    return configuration;
  }
}
