package com.mk8.screen.usecase

import com.mk8.core.domain.provider.SuperHeroExtrasProvider
import com.mk8.data.Either
import com.mk8.data.apidata.comic.ComicDetail
import com.mk8.marvelapp.usecase.common.UseCaseWithParams

class GetComicListBySuperHeroIdUseCase(
    private val superHeroExtrasProvider: SuperHeroExtrasProvider
) : UseCaseWithParams<Either<String,List<ComicDetail>>,Int>{

    override suspend fun execute(params: Int): Either<String, List<ComicDetail>> = superHeroExtrasProvider.getComicList(params)
}