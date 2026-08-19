plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

// Load keystore properties if present (keystore.properties at repo root)
val keystorePropsFile = rootProject.file("keystore.properties")
val keystoreProps = java.util.Properties()
if (keystorePropsFile.exists()) {
    keystoreProps.load(java.io.FileInputStream(keystorePropsFile))
}

android {
    namespace = "com.google.android.safetycore"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    defaultConfig {
        applicationId = "com.google.android.safetycore"
        minSdk = 29
        targetSdk = 35
        versionCode = 24650
        versionName = "1.0.966221264"
    }

    signingConfigs {
        create("release") {
            if (keystorePropsFile.exists()) {
                storeFile = file(keystoreProps["storeFile"] as String)
                storePassword = keystoreProps["storePassword"] as String
                keyAlias = keystoreProps["keyAlias"] as String
                keyPassword = keystoreProps["keyPassword"] as String
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation("io.coil-kt:coil:2.3.0")
}
