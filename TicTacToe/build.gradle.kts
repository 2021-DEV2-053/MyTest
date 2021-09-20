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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}