package com.mk8.screen.usecase

import com.mk8.core.domain.provider.SuperHeroProvider
import com.mk8.data.Either
import com.mk8.data.appdata.SuperHeroData
import com.mk8.marvelapp.usecase.common.UseCaseWithParams

class GetSuperheroByIdentifierUseCase(
    private val superHeroProvider: SuperHeroProvider
) : UseCaseWithParams<Either<String, SuperHeroData>, Int> {

    override suspend fun execute(params: Int): Either<String, SuperHeroData> = superHeroProvider.getSuperheroByIdentifier(params)
}