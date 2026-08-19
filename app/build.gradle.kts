plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.google.android.safetycore"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.google.android.safetycore"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0.0"
    }
}
