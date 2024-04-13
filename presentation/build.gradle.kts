plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.presentation"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}

dependencies {
    implementation(project(":domain"))

    // Koin DI
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)

    // Android
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v270)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.atomicfu)
}