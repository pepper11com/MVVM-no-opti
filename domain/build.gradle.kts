plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
        targetSdk = 34
    }

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


    kotlinOptions {
        jvmTarget = "1.8"
    }
}


java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin.sourceSets["main"].kotlin {
    srcDir("build/generated/ksp/main/kotlin")
}

dependencies {

    // Koin DI
    implementation(libs.koin.core)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
}