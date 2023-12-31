@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlinx.serialization)
  alias(libs.plugins.sqldelight.plugin)
}

android {
  namespace = "com.maxrzhe.newmax.data"
  compileSdk = (findProperty("android.compileSdk") as String).toInt()

  defaultConfig {
    minSdk = (findProperty("android.minSdk") as String).toInt()

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

  implementation(project(":data_api"))
  implementation(project(":common"))
  implementation(project(":core"))
  implementation(libs.androidx.core.ktx)

  implementation(libs.ktor.client.core)
  implementation(libs.ktor.client.android)
  implementation(libs.ktor.serialization.kotlinx.json)
  implementation(libs.ktor.client.content.negotiation)
  implementation(libs.ktor.client.logging.jvm)

  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.serialization)

  implementation(libs.sqldelight.android.driver)
  implementation(libs.sqldelight.coroutines.extensions)

  implementation(libs.koin.android)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}

sqldelight {
  databases {
    create("NewsDatabase") {
      packageName.set("com.maxrzhe.newmax.data.database")
    }
  }
}