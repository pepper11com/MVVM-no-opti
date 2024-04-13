plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)

    id("kotlinx-serialization")
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    buildTypes {
        debug {
            isMinifyEnabled = false
        }

        release {
            isMinifyEnabled = true
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt")
//            )
        }
    }

    defaultConfig {
        minSdk = 28
        targetSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))

    // Koin DI
    implementation(libs.koin.core)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)

    // Networking
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging.jvm)

    // Serialization
    implementation(libs.kotlinx.serialization.json)
}