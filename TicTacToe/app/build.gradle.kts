plugins {
    id("com.android.application")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    buildToolsVersion = Config.BUILD_TOOLS
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME

        testInstrumentationRunner = "mytest.tictactoe.CustomTestRunner"
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
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation (Dependencies.APPCOMPAT)
    implementation (Dependencies.CONSTRAINT_LAYOUT)
    implementation (Dependencies.LEGACY_SUPPORT)
    implementation (Dependencies.FRAGMENT)
    implementation (Dependencies.NAVIGATION_FRAGMENT)
    implementation (Dependencies.NAVIGATION_UI)
    implementation (Dependencies.LIFECYCLE_VIEWMODEL_KTX)
    implementation (Dependencies.LIFECYCLE_RUNTIME_KTX)
    implementation (Dependencies.ROOM)
    implementation (Dependencies.ROOM_EXT)
    annotationProcessor (Dependencies.ROOM_COMPILER)
    kapt (Dependencies.ROOM_COMPILER)

    implementation (Dependencies.MATERIAL)
    implementation (Dependencies.GSON)
    implementation (Dependencies.COROUTINES_CORE)
    implementation (Dependencies.HILT_ANDROID)
    kapt (Dependencies.HILT_COMPILER)

    //Testing
    testImplementation (Dependencies.JUNIT)
    testImplementation (Dependencies.JUNIT_EXT)
    testImplementation (Dependencies.ROOM_TESTING)
    testImplementation (Dependencies.ARCH_TESTING)
    testImplementation (Dependencies.MOCKITO)
    testImplementation (Dependencies.COROUTINES_TEST)
    debugImplementation (Dependencies.FRAGMENT_TESTING)
    androidTestImplementation (Dependencies.JUNIT_EXT)
    androidTestImplementation (Dependencies.HILT_TESTING)
    androidTestImplementation (Dependencies.ESPRESSO)
    androidTestImplementation (Dependencies.ARCH_TESTING)
    androidTestImplementation (Dependencies.NAVIGATION_TESTING)
    kaptAndroidTest(Dependencies.HILT_COMPILER)
}

