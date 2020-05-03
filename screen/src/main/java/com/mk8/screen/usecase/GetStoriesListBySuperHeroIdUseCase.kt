package com.mk8.screen.usecase

import com.mk8.core.domain.provider.SuperHeroExtrasProvider
import com.mk8.data.Either
import com.mk8.data.apidata.stories.StoriesDetail
import com.mk8.marvelapp.usecase.common.UseCaseWithParams

class GetStoriesListBySuperHeroIdUseCase(
    private val superHeroExtrasProvider: SuperHeroExtrasProvider
) : UseCaseWithParams<Either<String, List<StoriesDetail>>, Int> {

    override suspend fun execute(params: Int): Either<String, List<StoriesDetail>> = superHeroExtrasProvider.getStoriesList(params)
}