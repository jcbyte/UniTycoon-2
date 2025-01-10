package com.vikingz.unitycoon.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to ignore methods from being produced in the Jacoco coverage report.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// Note: This must have `Generated` in its name for Jacoco to ignore it.
public @interface DebugMethodExcludeGeneratedJacoco {}
