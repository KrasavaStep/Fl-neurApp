import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
//    android {
//        namespace = "com.pandastudio.flaneurapp"
//        compileSdk {
//            version = release(37)
//        }
//        minSdk = 24
//    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)

            // Submodules
            implementation(project(":core:model"))
            implementation(project(":core:network"))
            implementation(project(":core:database"))
            implementation(project(":core:location"))
            implementation(project(":core:designsystem"))
            implementation(project(":core:maps"))
            implementation(project(":feature:generator"))
            implementation(project(":feature:history"))
            implementation(project(":feature:tracking"))
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.compose.ui.tooling.preview)
        }

        iosMain.dependencies {
        }
    }
}

// 4. Конфигурация Android Application (вынесена отдельно от kotlin)
android {
    namespace = "com.pandastudio.flaneurapp"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.pandastudio.flaneurapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(libs.androidx.compose.ui.tooling)
}
