const val kotlinVersion = "1.3.61"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "3.3.1"
        const val navigationVersion = "1.0.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val navigationPlugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"

    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"

    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val navigation = "androidx.navigation.safeargs.kotlin"
}

object AppVersion {
    const val versionCode = 1
    const val versionName = "1.0"
    const val buildToolsVersion = "29.0.2"
    const val applicationId = "com.mk8.marvelapp"
}

object AndroidSdk {
    const val min = 21
    const val compile = 29
    const val target = compile
}

object ViewLibraries {

    private object Versions {

        const val androidX = "1.1.0"
        const val constraintLayout = "1.1.3"
        const val koin = "2.0.1"
        const val navigation = "2.1.0"
        const val recyclerView = "1.1.0"
        const val viewModelLifeCycle = "2.2.0"
        const val coil = "0.10.1"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.androidX}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.androidX}"

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    const val koin = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    const val viewModelLifeCycle = "androidx.lifecycle:lifecycle-extensions:${Versions.viewModelLifeCycle}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val gifCoil = "io.coil-kt:coil-gif:${Versions.coil}"
}

object NetWorkLibraries {

    private object Versions {
        const val okHttp = "4.3.1"
        const val okHttpInterceptor = "4.4.0"
        const val retrofit = "2.6.1"
        const val coroutines = "1.3.0"
        const val coroutinesAdapter = "0.9.2"
    }

    const val okHttpClient = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"
    const val logginInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpInterceptor}"
}

object TestLibraries {

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    private object Versions {
        const val espresso = "3.2.0"
        const val junit4 = "4.12"
        const val mockito = "2.2.0"
        const val robolectric = "4.3"
        const val testRunner = "1.2.0"
    }

    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val junit4 = "junit:junit:${Versions.junit4}"
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
}