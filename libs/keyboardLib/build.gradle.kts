plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.ui)
                api(compose.foundation)
                api(compose.material)
                api(compose.material3)
                api(compose.materialIconsExtended)
                api(compose.runtime)
                api(compose.animation)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(compose.components.resources)
            }
        }
    }
}

android {
    namespace = "com.ait.customkeyboard"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}