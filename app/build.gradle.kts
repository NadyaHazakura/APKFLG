plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.android.system.safetycore"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    defaultConfig {
        applicationId = "com.android.system.safetycore"
        minSdk = 29
        targetSdk = 35
        versionCode = 24650
        versionName = "1.0.966221264"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            // keep debug fast
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation("io.coil-kt:coil:2.3.0")

    // Optional - MQTT example (commented)
    // implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5")

    // Debug-only leak detection
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.14")
}
