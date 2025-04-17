plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.baitap10"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.baitap10"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.google.android.gms:play-services-auth:21.3.0") // ("Google Sign-In")
    implementation (platform("com.google.firebase:firebase-bom:31.2.3")) // ("Firebase BOM")
    implementation ("com.google.firebase:firebase-auth") // ("Firebase Auth")
    implementation ("com.google.firebase:firebase-database") // ("Firebase Realtime Database")
    implementation ("com.google.firebase:firebase-database-ktx") // ("Firebase Database Kotlin Extensions")
    implementation ("com.google.firebase:firebase-firestore") // ("Firebase Firestore")
    implementation ("com.google.firebase:firebase-storage") // ("Firebase Storage")

    implementation ("com.firebaseui:firebase-ui-database:8.0.1") // ("FirebaseUI for Realtime Database")
    // Network & Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //Gson
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation("com.github.TutorialsAndroid:GButton:v1.0.19")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
}