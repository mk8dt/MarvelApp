# Marvel App

This project is an example of Modularization in Android.

I want to try this kind of architecture to prove if is useful, and it is interesting because in big enterprise that has different project you can reuse part of code
and don´t waste time writing it, also whe you are coding the app you win compilation time if you don´t change all modules because just compile module tht you change.

I use MVVM pattern with Live data as observer pattern.

Also i have experimented with Gradle Kotlin DSL to write gradle files with kotlin and store all dependencies in other module to easier management.
You can explore more about this here https://docs.gradle.org/current/userguide/kotlin_dsl.html

# KOIN

As dependency injection i have used Koin because it´s easier than dagger to use and its written in kotlin and not generate extra code.
Just need to write this code in you class extend Application() and its ready to use.

implementation "org.koin:koin-androidx-scope:${Versions.koin}"
implementation"org.koin:koin-androidx-viewmodel:${Versions.koin}"

 startKoin {
            androidContext(this@MarvelApp)
            androidLogger()
            modules(DependenciesProvider().getModules())
        }

More information visit https://insert-koin.io/

# COIL

To manage image resource from network i have used Coil is a new library to replace Glide or Picasso and i have selected for the same reason that Koin , its written in kotlin
use co-routines and it´s fast and easy to use too. And you can manage image , gifs and videos. the only requirement is use JAVA 1.8.

implementation"io.coil-kt:coil:${Versions.coil}"
implementation "io.coil-kt:coil-gif:${Versions.coil}"

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

Read more about that https://coil-kt.github.io/coil/getting_started/

# EITHER

When you call network to get data you manage error or success on a easy way using either pattern and get both state and act in consequence to your app doesn´t crash.

sealed class Either<out E, out V> {
    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Value<out V>(val value: V) : Either<Nothing, V>()
}

fun <V> value(value: V): Either<Nothing, V> = Either.Value(value)

fun <E> error(error: E): Either<E, Nothing> = Either.Error(error)

inline fun <E, V, A> Either<E, V>.fold(e: (E) -> A, v: (V) -> A): A = when (this) {
    is Either.Error -> e(this.error)
    is Either.Value -> v(this.value)
}

You can read more about that and use other method here https://medium.com/@LubosMudrak/you-either-love-it-or-you-havent-used-it-yet-a55f9b866dbe

#CO-ROUTINES

As android developer i know what the hard is to manage different threads when you are calling network to get data and you don´t want to block main thread and still use the app
while you are waiting the response, so with kotlin we have co-routines that allow write sequential code in asynchronous way and you will never need the hell of callbacks.

implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

And for retrofit adapter add this

implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"

RetrofitApiClient = Retrofit.Builder()
    .baseUrl(RetrofitApiClient.BASE_URL)
    .client(getOkHttpClient())
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()
    .create(RetrofitApiClient::class.java)

uiScope.launch {

    view?.showProgressBar()

    val resultSuperHero = uiScope.async { withContext(ioContext) { getSuperheroByIdentifierUseCase.execute(identifier) } }

     resultSuperHero.await().fold(
                    { view?.showError(it) },
                    { _superheroMutableData.postValue(it) }
                )
 }


Hope this project will be useful for someone wants to learn about this architecture or any of libraries that i have used.