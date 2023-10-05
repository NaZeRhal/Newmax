pluginManagement {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "Newmax"
include(":app")
include(":feature:onboarding")
include(":theme")
include(":data")
include(":domain")
include(":data_api")
include(":common")
include(":core")
