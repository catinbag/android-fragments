// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
}

// Old AndroidX libraries bring kotlin-stdlib-jdk7 and jdk8 1.6.x. Since Kotlin 1.8 their
// classes are inside kotlin-stdlib: without this pin the build fails with duplicate classes.
subprojects {
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.10")
            force("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10")
        }
    }
}
