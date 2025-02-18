plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // ROOM
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "de.syntax_institut.syntaxfitness"
    compileSdk = 35

    defaultConfig {
        applicationId = "de.syntax_institut.syntaxfitness"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // ROOM
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.cronet.embedded)
    implementation(libs.generativeai)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.translate)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.play.services.maps)
    implementation(libs.maps)
    implementation(libs.protolite.well.known.types)
    implementation(libs.firebase.firestore)
    ksp(libs.androidx.room.compiler)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.datastore.preferences)


    // Api
    implementation(libs.moshi)
    implementation(libs.retrofit)
    implementation (libs.converterMoshi)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    implementation(libs.logging.interceptor)
    //Location
    implementation(libs.play.services.location)

    implementation(libs.androidx.work.runtime.ktx)
    // Import the BoM for the Firebase platform
    implementation (platform(libs.firebase.bom))

    // Declare the dependencies for the desired Firebase products without specifying versions
    // For example, declare the dependencies for Firebase Authentication and Cloud Firestore
    implementation( libs.firebase.auth)
    implementation(libs.firebase.auth.ktx)
}