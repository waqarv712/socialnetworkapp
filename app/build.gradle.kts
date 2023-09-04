import com.android.tools.build.bundletool.model.KeystoreProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.waqarvicky.socialnetworkapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.waqarvicky.socialnetworkapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            /*storeFile = file("../keystores/waqarvicky.jks")
            storePassword = "waqarvicky"
            keyAlias = "waqarvicky"
            keyPassword = "waqarvicky"*/

            storeFile = file(project.property("KEYSTORE_FILE").toString())
            storePassword = project.property("KEYSTORE_PASSWORD").toString()
            keyAlias = project.property("SIGNING_KEY_ALIAS").toString()
            keyPassword = project.property("SIGNING_KEY_PASSWORD").toString()
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.runtime:runtime-livedata")

    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    implementation("androidx.navigation:navigation-compose:2.7.1")
    implementation("io.insert-koin:koin-android:3.1.4")
    implementation("io.insert-koin:koin-androidx-compose:3.1.4")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.24.1-alpha")

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")

}

//tasks.withType<JavaCompile> {
//    options.compilerArgs.addAll(
//        listOfNotNull(
//            "--enable-preview",
//            "--add-modules", "jdk.incubator.concurrent"
//        )
//    )
//}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()

//    jvmArgs("--enable-preview", "--add-modules", "jdk.incubator.concurrent")

    testLogging {
        events("passed", "skipped", "failed", "standardOut", "standardError")
    }
}