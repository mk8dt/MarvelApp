plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.navigation)
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)

        versionCode = AppVersion.versionCode
        versionName = AppVersion.versionName

        testInstrumentationRunner = TestLibraries.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":core"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(ViewLibraries.kotlinStdLib)
    implementation(ViewLibraries.appCompat)
    implementation(ViewLibraries.ktxCore)

    implementation(ViewLibraries.constraintLayout)
    implementation(ViewLibraries.recyclerView)

    implementation(ViewLibraries.koin)
    implementation(ViewLibraries.koinViewModel)

    implementation(ViewLibraries.viewModelLifeCycle)

    implementation(ViewLibraries.navigationUi)
    implementation(ViewLibraries.navigationFragment)

    implementation(NetWorkLibraries.coroutines)

    implementation(ViewLibraries.coil)
    implementation(ViewLibraries.gifCoil)

    testImplementation(TestLibraries.junit4)

    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}

repositories {
    mavenCentral()
}
