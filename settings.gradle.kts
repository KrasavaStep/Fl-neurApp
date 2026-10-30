pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "flaneur-app"

include(
    ":core:model",
    ":core:network",
    ":core:database",
    ":core:location",
    ":core:designsystem",
    ":core:maps",
    ":feature:generator",
    ":feature:tracking",
    ":feature:history",
    ":composeApp"
)
