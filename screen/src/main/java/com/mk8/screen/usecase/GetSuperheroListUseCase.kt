package com.mk8.screen.usecase

import com.mk8.data.Either
import com.mk8.data.appdata.SuperHeroData
import com.mk8.core.domain.provider.SuperHeroProvider
import com.mk8.marvelapp.usecase.common.UseCase

class GetSuperheroListUseCase(
    private val superHeroProvider: SuperHeroProvider
) : UseCase<Either<String, List<SuperHeroData>>> {

    override suspend fun execute(): Either<String, List<SuperHeroData>> = superHeroProvider.getSuperHeroList()
}