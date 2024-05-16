import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.library)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm {
        jvm {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "17"
                }
            }
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenmodel)
            implementation(libs.composeImageLoader)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.koin.core)
            implementation(libs.kermit)
            implementation(libs.multiplatformSettings)
            // https://mvnrepository.com/artifact/com.rabbitmq/amqp-client
            implementation(libs.amqp.client)
            implementation(libs.slf4j.simple)
            implementation(libs.slf4j.api)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
            implementation(libs.kotlinx.coroutines.test)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activityCompose)
            implementation(libs.androidx.security.crypto)
        }
    }
}

buildConfig {
    // BuildConfig configuration here.
    // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
    forClass("PreferenceName") {
        packageName("app.dayacore")

        buildConfigField("String", "CONFIG", "\"app-config\"")
    }
}

android {
    namespace = "app.dayacore"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}