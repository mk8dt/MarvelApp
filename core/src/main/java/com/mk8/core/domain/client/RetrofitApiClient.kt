package com.mk8.core.domain.client

import com.mk8.data.apidata.comic.JsonResponseComic
import com.mk8.data.apidata.events.JsonResponseEvents
import com.mk8.data.apidata.series.JsonResponseSeries
import com.mk8.data.apidata.stories.JsonResponseStories
import com.mk8.data.apidata.superhero.JsonResponseSuperHero
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApiClient {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com:443/"
        const val API_KEY = "c03b87d6b9912d502a0e96094dad8907"
        const val PRIVATE_KEY = "52b61990c0702f73a030da5d0ccbe66e97f1242b"
    }

    @GET("v1/public/characters")
    suspend fun getSuperHeroList(): JsonResponseSuperHero

    @GET("v1/public/characters/{characterId}")
    suspend fun getSuperHeroByIdentifier(@Path("characterId") characterId: Int): JsonResponseSuperHero

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getComicListByCharacterId(@Path("characterId") characterId: Int): JsonResponseComic

    @GET("v1/public/characters/{characterId}/series")
    suspend fun getSeriesListByCharacterId(@Path("characterId") characterId: Int): JsonResponseSeries

    @GET("v1/public/characters/{characterId}/stories")
    suspend fun getStoriesListByCharacterId(@Path("characterId") characterId: Int): JsonResponseStories

    @GET("v1/public/characters/{characterId}/events")
    suspend fun getEventsListByCharacterId(@Path("characterId") characterId: Int): JsonResponseEvents
}