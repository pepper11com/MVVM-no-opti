plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.pokedexmvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pokedexmvvm"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    bundle {
        language {
            enableSplit = false
        }
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":presentation"))
    implementation(project(":data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.androidx.appcompat)

    // Kotlin immutable
    implementation(libs.kotlinx.collections.immutable)

    // Koin DI
    implementation(libs.koin.android)
    implementation(libs.koin.nav)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)

    // Compose - Navigation
    implementation(libs.destinations.core)
    ksp(libs.destinations.ksp)

    // Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)

    implementation(libs.androidx.core.splashscreen)
}
