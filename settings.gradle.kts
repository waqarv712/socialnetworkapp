pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        /*mavenCentral(){
            content {
                includeModule("org.jetbrain.kotlinx", "kotlinx-collections-immutable-jvm")
            }
        }*/
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Social Network App"
include(":app")
 