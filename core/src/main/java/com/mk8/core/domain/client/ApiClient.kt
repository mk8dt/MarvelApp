package com.mk8.core.domain.client

import com.mk8.data.Either
import com.mk8.data.apidata.comic.ComicDetail
import com.mk8.data.apidata.events.EventDetail
import com.mk8.data.apidata.series.SerieDetail
import com.mk8.data.apidata.stories.StoriesDetail
import com.mk8.data.appdata.SuperHeroData

interface ApiClient {

    suspend fun getSuperheroList(): Either<String, List<SuperHeroData>>

    suspend fun getSuperHeroByIdentifier(identifier: Int): Either<String, SuperHeroData>

    suspend fun getComicListByIdentifier(identifier: Int): Either<String, List<ComicDetail>>

    suspend fun getSeriesListByIdentifier(identifier: Int): Either<String, List<SerieDetail>>

    suspend fun getStoriesListByIdentifier(identifier: Int): Either<String, List<StoriesDetail>>

    suspend fun getEventsListByIdentifier(identifier: Int): Either<String, List<EventDetail>>
}