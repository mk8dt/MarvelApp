package com.mk8.core.domain.repository

import com.mk8.core.domain.client.ApiClient
import com.mk8.data.Either
import com.mk8.data.apidata.comic.ComicDetail
import com.mk8.data.apidata.events.EventDetail
import com.mk8.data.apidata.series.SerieDetail
import com.mk8.data.apidata.stories.StoriesDetail

class SuperHeroExtrasRepository(
    private val apiClient: ApiClient
) {

    suspend fun getEventList(identifier: Int): Either<String, List<EventDetail>> = apiClient.getEventsListByIdentifier(identifier)

    suspend fun getSeriesList(identifier: Int): Either<String, List<SerieDetail>> = apiClient.getSeriesListByIdentifier(identifier)

    suspend fun getComicList(identifier: Int): Either<String, List<ComicDetail>> = apiClient.getComicListByIdentifier(identifier)

    suspend fun getStoriesList(identifier: Int): Either<String, List<StoriesDetail>> = apiClient.getStoriesListByIdentifier(identifier)
}