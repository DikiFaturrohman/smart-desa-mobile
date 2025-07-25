// Project-level build.gradle.kts
buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        // Add this line for Hilt
        classpath(libs.hilt.android.gradle.plugin)

    }
}

plugins {
    id("com.android.application") version "8.8.2" apply false
    id("com.android.library") version "8.8.2" apply false
    id("org.jetbrains.kotlin.android") version "2.1.20" apply false
    alias(libs.plugins.compose.compiler) apply false
    id("com.google.devtools.ksp") version "2.1.20-2.0.0" apply false
    id("com.google.gms.google-services") version "4.4.3" apply false

}