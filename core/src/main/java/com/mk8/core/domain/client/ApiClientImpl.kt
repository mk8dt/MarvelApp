package com.mk8.core.domain.client

import com.mk8.data.Either
import com.mk8.data.apidata.comic.ComicDetail
import com.mk8.data.apidata.events.EventDetail
import com.mk8.data.apidata.series.SerieDetail
import com.mk8.data.apidata.stories.StoriesDetail
import com.mk8.data.appdata.SuperHeroData
import com.mk8.data.appdata.Superhero
import com.mk8.data.error
import com.mk8.data.value

class ApiClientImpl(private val retrofitApiClient: RetrofitApiClient) : ApiClient {

    override suspend fun getSuperheroList(): Either<String, List<SuperHeroData>> {
        val datNetwork = retrofitApiClient.getSuperHeroList()
        return if (datNetwork.code != 200)
            error(datNetwork.status)
        else {
            val results = datNetwork.data.results.map {
                Superhero(it.id, it.name, it.description, it.thumbnail)
            }
            value(results)
        }
    }

    override suspend fun getSuperHeroByIdentifier(identifier: Int): Either<String, SuperHeroData> {

        val dataNetWork = retrofitApiClient.getSuperHeroByIdentifier(identifier)

        return if (dataNetWork.code != 200) {
            error(dataNetWork.status)
        } else {
            val result = dataNetWork.data.results.map {
                Superhero(it.id, it.name, it.description, it.thumbnail)
            }.first()

            value(result)
        }
    }

    override suspend fun getComicListByIdentifier(identifier: Int): Either<String, List<ComicDetail>> {
        val dataNetWork = retrofitApiClient.getComicListByCharacterId(identifier)
        return if (dataNetWork.code != 200) {
            error(dataNetWork.status)
        } else {
            value(dataNetWork.data.results)
        }
    }

    override suspend fun getSeriesListByIdentifier(identifier: Int): Either<String, List<SerieDetail>> {
        val dataNetWork = retrofitApiClient.getSeriesListByCharacterId(identifier)
        return if (dataNetWork.code != 200) {
            error(dataNetWork.status)
        } else {
            value(dataNetWork.data.results)
        }
    }

    override suspend fun getStoriesListByIdentifier(identifier: Int): Either<String, List<StoriesDetail>> {
        val dataNetwork = retrofitApiClient.getStoriesListByCharacterId(identifier)
        return if (dataNetwork.code != 200) {
            error(dataNetwork.status)
        } else {
            value(dataNetwork.data.results)
        }
    }

    override suspend fun getEventsListByIdentifier(identifier: Int): Either<String, List<EventDetail>> {
        val dataNetwork = retrofitApiClient.getEventsListByCharacterId(identifier)
        return if (dataNetwork.code != 200) {
            error(dataNetwork.status)
        } else {
            value(dataNetwork.data.results)
        }
    }
}