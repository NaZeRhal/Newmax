@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "com.maxrzhe.newmax"
  compileSdk = (findProperty("android.compileSdk") as String).toInt()

  defaultConfig {
    applicationId = "com.maxrzhe.newmax"
    minSdk = (findProperty("android.minSdk") as String).toInt()
    targetSdk = (findProperty("android.targetSdk") as String).toInt()
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = libs.versions.jvmTarget.get()
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(project(":theme"))
  implementation(project(":feature:onboarding"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)

  implementation(platform(libs.compose.bom))
  implementation(libs.bundles.compose)
  implementation(libs.compose.navigation)

  implementation(libs.koin.android)
  implementation(libs.koin.compose)

  implementation(libs.splashscreen)
  implementation(libs.androidx.activity)


  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
//  androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
//  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//  debugImplementation("androidx.compose.ui:ui-tooling")
//  debugImplementation("androidx.compose.ui:ui-test-manifest")
}