package com.mk8.core.domain.repository

import com.mk8.core.domain.client.ApiClient
import com.mk8.data.Either
import com.mk8.data.appdata.SuperHeroData

class SuperHeroRepository(private val apiClient: ApiClient) {

    suspend fun getSuperHeroList(): Either<String, List<SuperHeroData>> = apiClient.getSuperheroList()

    suspend fun getSuperHeroByIdentifier(identifier: Int): Either<String, SuperHeroData> = apiClient.getSuperHeroByIdentifier(identifier)
}