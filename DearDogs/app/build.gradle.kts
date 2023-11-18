plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
    id ("kotlinx-serialization")
}

android {
    namespace = "com.prikhozhayamaria.pmis.deardogs.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.prikhozhayamaria.pmis.deardogs.myapplication"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "0.2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }

}


dependencies {
   /* implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    annotationProcessor("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.3.2")
    implementation ("androidx.room:room-runtime:2.5.1")
    implementation ("androidx.room:room-ktx:2.5.1")
    implementation ("androidx.navigation:navigation-runtime-ktx:2.6.0")
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation ("com.google.dagger:hilt-android:2.44.2")*/
    // implementation(libs.compose.destinations)
    // Navigation
    // implementation ("androidx.navigation:navigation-fragment-ktx:2.3.0-beta01")
    //  implementation ("androidx.navigation:navigation-ui-ktx:2.3.0-beta01")
    // Dynamic Feature Module Support
    //  implementation ("androidx.navigation:navigation-dynamic-features-fragment:2.3.0-beta01")
    // Testing Navigation
    // androidTestImplementation ("androidx.navigation:navigation-testing:2.3.0-beta01")

    //TryByHabrGoogle
    //implementation("androidx.navigation:navigation-compose:2.4.0-beta02")
    //TryBySomeYouTube
    val nav_version = "2.7.3"

    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation ("com.google.android.material:material:1.3.0-alpha02")
   /* val appcompat_version = "1.6.1"

    implementation("androidx.appcompat:appcompat:$appcompat_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    kapt ("com.google.dagger:hilt-android-compiler:2.28-alpha")
    kapt ("androidx.room:room-compiler:2.5.2")*/
    //kapt ("androidx.room:room-compiler:2.5.1")






    //Test
    implementation ("androidx.core:core-ktx:1.10.1")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation(libs.material3)
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.google.dagger:hilt-android:2.44.2")
    kapt ("com.google.dagger:hilt-compiler:2.44.2")

    //Room
    implementation ("androidx.room:room-runtime:2.6.0")
    kapt ("androidx.room:room-compiler:2.6.0")
    implementation ("androidx.room:room-ktx:2.6.0")

    val composeBom = platform("androidx.compose:compose-bom:2023.04.01")
    implementation (composeBom)
    androidTestImplementation (composeBom)

    //COMPOSE
    // Material Design 2
    implementation ("androidx.compose.material:material")

    // Android Studio Preview support
    implementation ("androidx.compose.ui:ui-tooling-preview")
    debugImplementation ("androidx.compose.ui:ui-tooling")

    // UI Tests
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")

    // Optional - Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    //collectAsStateWithLifecycle()
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    //COMPOSE NAVIGATION
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("androidx.navigation:navigation-compose:2.6.0")
    implementation ("androidx.navigation:navigation-runtime-ktx:2.6.0")
    implementation ("com.android.volley:volley:1.2.1")

    val ktor_version = "2.3.6"
    val serialization_version = "1.6.0"

    implementation ("io.ktor:ktor-client-okhttp:$ktor_version")
    implementation("io.ktor:ktor-client-mock:$ktor_version")
    implementation ("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-cbor:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-protobuf:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-client-apache:$ktor_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation ("io.ktor:ktor-client-android:$ktor_version")
    implementation ("io.ktor:ktor-client-json:$ktor_version")
    implementation ("io.ktor:ktor-client-serialization:$ktor_version")
    implementation ("io.ktor:ktor-client-logging:$ktor_version")

    implementation("io.coil-kt:coil-compose:2.0.0-rc01")
    //implementation("io.ktor:ktor-client-json:${ktor_version}")
    //implementation("io.ktor:ktor-client-serialization:${ktor_version}")
    //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${serialization_version}")
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.0")
//    implementation("org.jetbrains.kotlin:kotlin-serialization:1.3.0")
}
hilt {
    enableAggregatingTask = true
}