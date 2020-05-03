package com.mk8.core.domain.provider

import com.mk8.core.domain.repository.SuperHeroExtrasRepository
import com.mk8.data.Either
import com.mk8.data.apidata.comic.ComicDetail
import com.mk8.data.apidata.events.EventDetail
import com.mk8.data.apidata.series.SerieDetail
import com.mk8.data.apidata.stories.StoriesDetail

class SuperHeroExtrasProvider(
    private val superHeroExtrasRepository: SuperHeroExtrasRepository
) {

    suspend fun getEventsList(identifier: Int): Either<String, List<EventDetail>> = superHeroExtrasRepository.getEventList(identifier)

    suspend fun getSeriesList(identifier: Int): Either<String, List<SerieDetail>> = superHeroExtrasRepository.getSeriesList(identifier)

    suspend fun getComicList(identifier: Int): Either<String, List<ComicDetail>> = superHeroExtrasRepository.getComicList(identifier)

    suspend fun getStoriesList(identifier: Int): Either<String, List<StoriesDetail>> = superHeroExtrasRepository.getStoriesList(identifier)
}