plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias { libs.plugins.compose.compiler } apply false
}

buildscript {
    dependencies {
        classpath(libs.org.jetbrains.kotlin.plugin.compose.gradle.plugin)
    }
}