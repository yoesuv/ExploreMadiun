import java.util.Properties

val apiKeyPropertiesFile = project.rootProject.file("apiKey.properties")
val apiKeyProperties = Properties()
apiKeyProperties.load(apiKeyPropertiesFile.inputStream())

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.perf)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.navigation.safeargs)
}

android {

    signingConfigs {
        create("config") {
            keyAlias = project.properties["KEY_ALIAS"].toString()
            keyPassword = project.properties["KEY_PASSWORD"].toString()
            storeFile = file("../-yusuf.keystore")
            storePassword = project.properties["STORE_PASSWORD"].toString()
        }
    }

    namespace = "com.yoesuv.infomadiun"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        val keyMaps = apiKeyProperties["MAPS_API_KEY"].toString()
        val keyDirections = apiKeyProperties["DIRECTION_API_KEY"].toString()

        applicationId = "com.yoesuv.infomadiun"
        minSdk = project.properties["minApiLevel"].toString().toInt()
        targetSdk = project.properties["targetApiLevel"].toString().toInt()
        versionCode = project.properties["versionCode"].toString().toInt()
        versionName = project.properties["versionName"].toString()
        vectorDrawables {
            useSupportLibrary = true
        }
        tasks.withType<org.gradle.api.tasks.bundling.AbstractArchiveTask>().configureEach {
            archiveBaseName.set("$applicationId-v$versionCode($versionName)")
        }
        resValue("string", "MAPS_API_KEY", keyMaps)
        resValue("string", "DIRECTION_API_KEY", keyDirections)
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("config")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("config")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets {
        getByName("main") {
            res.directories += listOf(
                "src/main/res",
                "src/main/res-menu",
                "src/main/res-menu/gallery",
                "src/main/res-menu/listplace",
                "src/main/res-menu/maps",
                "src/main/res-menu/other"
            )
        }
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
        resValues = true
    }
    flavorDimensions.add("default")

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.espresso.contrib)

    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.viewpager2)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.perf)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)

    implementation(libs.glide)
    ksp(libs.glide.compiler)
    implementation(libs.google.direction.library)
    implementation(libs.ssp.android)
    implementation(libs.sdp.android)
}
