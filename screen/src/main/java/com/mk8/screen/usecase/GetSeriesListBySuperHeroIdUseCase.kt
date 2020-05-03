package com.mk8.screen.usecase

import com.mk8.core.domain.provider.SuperHeroExtrasProvider
import com.mk8.data.Either
import com.mk8.data.apidata.series.SerieDetail
import com.mk8.marvelapp.usecase.common.UseCaseWithParams

class GetSeriesListBySuperHeroIdUseCase(
    private val superHeroExtrasProvider: SuperHeroExtrasProvider
) : UseCaseWithParams<Either<String, List<SerieDetail>>, Int> {

    override suspend fun execute(params: Int): Either<String, List<SerieDetail>> = superHeroExtrasProvider.getSeriesList(params)
}