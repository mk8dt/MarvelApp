import org.gradle.model.internal.core.ModelNodes.withType

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
}

android {

    compileSdkVersion(AndroidSdk.compile)
    buildToolsVersion(AppVersion.buildToolsVersion)

    defaultConfig {

        applicationId = AppVersion.applicationId
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
    implementation(project(":screen"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(ViewLibraries.kotlinStdLib)
    implementation(ViewLibraries.appCompat)
    implementation(ViewLibraries.ktxCore)

    implementation(ViewLibraries.koin)
    implementation(ViewLibraries.koinViewModel)

    implementation(NetWorkLibraries.okHttpClient)
    implementation(NetWorkLibraries.retrofit)
    implementation(NetWorkLibraries.retrofitGson)
    implementation(NetWorkLibraries.coroutinesAdapter)
    implementation(NetWorkLibraries.logginInterceptor)

    testImplementation(TestLibraries.junit4)

    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}
