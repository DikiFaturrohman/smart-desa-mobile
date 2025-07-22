// app/build.gradle.kts
// Perbaikan cara loading local.properties

import org.gradle.kotlin.dsl.ksp
import java.io.FileInputStream
import java.util.Properties

val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        load(FileInputStream(localPropertiesFile))
    }
}

fun getProperty(key: String, defaultValue: String): String {
    return localProperties.getProperty(key, defaultValue)
}

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    alias(libs.plugins.compose.compiler)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.smart_desa"
    compileSdk = 35

    ksp {
        arg("ksp.kotlin.1.9.compatibility", "true")
    }

    defaultConfig {
        applicationId = "com.example.smart_desa"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders["redirectSchemeName"] = "com.example.smart_desa"
        manifestPlaceholders["redirectHostName"] = "callback"
        manifestPlaceholders["appAuthRedirectScheme"] = "com.example.smart_desa"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Core Android & Kotlin
    implementation(libs.androidx.core.ktx.v1120)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx.v270)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Compose
    implementation(libs.androidx.activity.compose.v182)
    implementation(platform(libs.androidx.compose.bom.v20231001))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.lifecycle.service)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    debugImplementation(libs.ui.tooling)

    // Compose Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.16.0"))


    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")


    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries

    // Animated Bottom Navigation Bar
    implementation("com.exyte:animated-navigation-bar:1.0.0")
//    implementation(libs.spotify.player) // Add this
    // Accompanist (Compose utilities)
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.webview)

    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler) // Added Hilt compiler dependency

    // Room for local database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Retrofit & OkHttp for API requests
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Coroutines for asynchronous programming
    implementation(libs.kotlinx.coroutines.android)

    // Coil for image loading with Compose
    implementation(libs.coil.compose)

    // Spotify SDK
    implementation(libs.auth)

    // YouTube Player API for Compose
    implementation(libs.core)
    implementation(libs.chromecast.sender)

    // DataStore Preferences
    implementation(libs.androidx.datastore.preferences)

    // Add to dependencies section in app/build.gradle.kts
    implementation(libs.glide)
    implementation(libs.androidx.media)  // For MediaStyle notifications
    implementation(libs.appauth)
    implementation(libs.androidx.browser)

    // For JSON processing
    implementation(libs.converter.gson)

    // Skydoves Sandwich for API response handling
    implementation(libs.skydoves.sandwich)
    implementation(libs.skydoves.whatif)
    implementation(libs.skydoves.sandwich.retrofit)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    androidTestImplementation(platform(libs.androidx.compose.bom.v20250500))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.33.2-alpha")

    implementation("androidx.core:core-splashscreen:1.0.1")


}