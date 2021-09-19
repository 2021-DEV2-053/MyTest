plugins {
    id("com.android.application")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-kapt")
    id("realm-android")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation (Dependencies.APPCOMPAT)
    implementation (Dependencies.CONSTRAINT_LAYOUT)
    implementation (Dependencies.NAVIGATION_FRAGMENT)
    implementation (Dependencies.NAVIGATION_UI)

    implementation (Dependencies.LIFECYCLE_VIEWMODEL_KTX)
    implementation (Dependencies.LIFECYCLE_RUNTIME_KTX)

    implementation (Dependencies.MATERIAL)
    implementation (Dependencies.GSON)
    implementation (Dependencies.HILT_ANDROID)
    kapt (Dependencies.HILT_COMPILER)

    implementation (Dependencies.COROUTINES_ANDROID)

    //Testing
    testImplementation (Dependencies.JUNIT)
    androidTestImplementation (Dependencies.JUNIT_EXT)
    androidTestImplementation (Dependencies.ESPRESSO)
    androidTestImplementation (Dependencies.NAVIGATION_TESTING)
}

