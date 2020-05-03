package com.mk8.marvelapp

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mk8.core.domain.client.RetrofitApiClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

fun provideRetrofitClient(): RetrofitApiClient = Retrofit.Builder()
    .baseUrl(RetrofitApiClient.BASE_URL)
    .client(getOkHttpClient())
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()
    .create(RetrofitApiClient::class.java)

fun getOkHttpClient() = OkHttpClient().newBuilder().addInterceptor { chain ->
    val currentTimestamp = System.currentTimeMillis()
    val newUrl = chain.request().url
        .newBuilder()
        .addQueryParameter("apikey", RetrofitApiClient.API_KEY)
        .addQueryParameter("ts", currentTimestamp.toString())
        .addQueryParameter(
            "hash",
            md5(currentTimestamp.toString() + RetrofitApiClient.PRIVATE_KEY + RetrofitApiClient.API_KEY)
        )
        .build()

    val newRequest = chain.request()
        .newBuilder()
        .url(newUrl)
        .build()
    chain.proceed(newRequest)
}
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .connectTimeout(60L, TimeUnit.SECONDS)
    .readTimeout(60L, TimeUnit.SECONDS)
    .writeTimeout(60L, TimeUnit.SECONDS)
    .build()

private fun md5(hash: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(hash.toByteArray())).toString(16).padStart(32, '0')
}
