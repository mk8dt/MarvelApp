package com.mk8.screen.usecase

import com.mk8.core.domain.provider.SuperHeroExtrasProvider
import com.mk8.data.Either
import com.mk8.data.apidata.events.EventDetail
import com.mk8.marvelapp.usecase.common.UseCaseWithParams

class GetEventsListBySuperHeroIdUseCase(
    private val superHeroExtrasProvider : SuperHeroExtrasProvider
) : UseCaseWithParams<Either<String,List<EventDetail>>,Int> {

    override suspend fun execute(params: Int): Either<String, List<EventDetail>> = superHeroExtrasProvider.getEventsList(params)
}