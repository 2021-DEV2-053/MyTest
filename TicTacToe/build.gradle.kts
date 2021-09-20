// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildPlugins.ANDROID_GRADLE)
        classpath(BuildPlugins.KOTLIN)
        classpath(BuildPlugins.HILT_AGP)
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}