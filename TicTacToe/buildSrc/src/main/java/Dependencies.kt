object BuildPlugins {
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_VERSION}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_VERSION}"
    const val HILT_AGP = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_AGP_VERSION}"
}

object Dependencies {

    //Libs
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT_VERSION}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_VERSION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_VERSION}"

    const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VERSION}"
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_VERSION}"
    const val ROOM = "androidx.room:room-runtime:${Versions.ROOM_VERSION}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM_VERSION}"
    const val ROOM_EXT = "androidx.room:room-ktx:${Versions.ROOM_VERSION}"

    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
    const val GSON = "com.google.code.gson:gson:${Versions.GSON_VERSION}"
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT_AGP_VERSION}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT_AGP_VERSION}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VERSION}"

    //Testing
    const val JUNIT = "junit:junit:${Versions.JUNIT_VERSION}"
    const val JUNIT_EXT = "androidx.test.ext:junit-ktx:${Versions.JUNIT_EXT_VERSION}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_VERSION}"
    const val NAVIGATION_TESTING = "androidx.navigation:navigation-testing:${Versions.NAVIGATION_VERSION}"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_VERSION}"
    const val HILT_TESTING = "com.google.dagger:hilt-android-testing:${Versions.HILT_AGP_VERSION}"
    const val ROOM_TESTING = "androidx.room:room-testing:${Versions.ROOM_VERSION}"
    const val MOCKITO = "org.mockito:mockito-core:${Versions.MOCKITO_VERSION}"

}