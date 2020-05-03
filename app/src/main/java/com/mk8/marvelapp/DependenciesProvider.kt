package com.mk8.marvelapp

import com.mk8.core.domain.cache.CacheSuperHero
import com.mk8.core.domain.cache.CacheSuperHeroList
import com.mk8.core.domain.client.ApiClient
import com.mk8.core.domain.client.ApiClientImpl
import com.mk8.core.domain.provider.SuperHeroExtrasProvider
import com.mk8.core.domain.provider.SuperHeroProvider
import com.mk8.core.domain.repository.SuperHeroExtrasRepository
import com.mk8.core.domain.repository.SuperHeroRepository
import com.mk8.screen.detail.DetailViewModel
import com.mk8.screen.main.MainViewModel
import com.mk8.screen.usecase.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class DependenciesProvider {

    fun getModules(): List<Module> = listOf(appModule, domainModule, useCaseModule, viewModelModule)

    private val appModule = module {
        single { provideRetrofitClient() }

        single<ApiClient> { ApiClientImpl(retrofitApiClient = get()) }
    }

    private val domainModule = module {
        single { SuperHeroProvider(superHeroRepository = get(), cacheSuperheroList = get(), cacheSuperHero = get()) }
        single { SuperHeroExtrasProvider(superHeroExtrasRepository = get()) }

        single { SuperHeroRepository(apiClient = get()) }
        single { SuperHeroExtrasRepository(apiClient = get()) }

        single { CacheSuperHeroList() }
        single { CacheSuperHero() }
    }

    private val useCaseModule = module {
        factory { GetSuperheroListUseCase(superHeroProvider = get()) }
        factory { GetSuperheroByIdentifierUseCase(superHeroProvider = get()) }
        factory { GetComicListBySuperHeroIdUseCase(superHeroExtrasProvider = get()) }
        factory { GetSeriesListBySuperHeroIdUseCase(superHeroExtrasProvider = get()) }
        factory { GetStoriesListBySuperHeroIdUseCase(superHeroExtrasProvider = get()) }
        factory { GetEventsListBySuperHeroIdUseCase(superHeroExtrasProvider = get()) }
    }

    private val viewModelModule = module {
        viewModel { MainViewModel(getSuperheroListUseCase = get()) }
        viewModel {
            DetailViewModel(
                getComicListBySuperHeroIdUseCase = get(),
                getEventsListBySuperHeroIdUseCase = get(),
                getSeriesListBySuperHeroIdUseCase = get(),
                getStoriesListBySuperHeroIdUseCase = get(),
                getSuperheroByIdentifierUseCase = get()
            )
        }
    }
}
