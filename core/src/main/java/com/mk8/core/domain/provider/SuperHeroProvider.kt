package com.mk8.core.domain.provider

import com.mk8.core.domain.cache.CacheSuperHero
import com.mk8.core.domain.cache.CacheSuperHeroList
import com.mk8.core.domain.repository.SuperHeroRepository
import com.mk8.data.Either
import com.mk8.data.appdata.SuperHeroData
import com.mk8.data.fold
import com.mk8.data.value

class SuperHeroProvider(
    private val superHeroRepository: SuperHeroRepository,
    private val cacheSuperheroList: CacheSuperHeroList,
    private val cacheSuperHero: CacheSuperHero
) {
    suspend fun getSuperHeroList(): Either<String, List<SuperHeroData>> {
        val cacheData = cacheSuperheroList.load()
        return if (cacheData != null) value(cacheData)
        else {
            val dataRepository = superHeroRepository.getSuperHeroList()
            dataRepository.fold({}, { cacheSuperheroList.save(it) })
            dataRepository
        }
    }

    suspend fun getSuperheroByIdentifier(identifier: Int): Either<String, SuperHeroData> {
        val cacheData = cacheSuperHero.load()
        return if (cacheData != null && cacheData.id == identifier) value(cacheData)
        else {
            val dataRepo = superHeroRepository.getSuperHeroByIdentifier(identifier)
            dataRepo.fold({}, { cacheSuperHero.save(it) })
            dataRepo
        }
    }
}