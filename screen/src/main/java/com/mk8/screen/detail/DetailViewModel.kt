package com.mk8.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mk8.data.apidata.comic.ComicDetail
import com.mk8.data.apidata.events.EventDetail
import com.mk8.data.apidata.series.SerieDetail
import com.mk8.data.apidata.stories.StoriesDetail
import com.mk8.data.appdata.SuperHeroData
import com.mk8.data.fold
import com.mk8.marvelapp.screen.base.BaseViewModel
import com.mk8.screen.usecase.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val getSuperheroByIdentifierUseCase: GetSuperheroByIdentifierUseCase,
    private val getComicListBySuperHeroIdUseCase: GetComicListBySuperHeroIdUseCase,
    private val getEventsListBySuperHeroIdUseCase: GetEventsListBySuperHeroIdUseCase,
    private val getSeriesListBySuperHeroIdUseCase: GetSeriesListBySuperHeroIdUseCase,
    private val getStoriesListBySuperHeroIdUseCase: GetStoriesListBySuperHeroIdUseCase
) : BaseViewModel<DetailView>() {

    private val _superheroMutableData = MutableLiveData<SuperHeroData>()
    val superheroLiveData: LiveData<SuperHeroData>
        get() = _superheroMutableData

    private val _comicsMutableData = MutableLiveData<List<ComicDetail>>()
    val comicsLiveData: LiveData<List<ComicDetail>>
        get() = _comicsMutableData

    private val _eventsMutableData = MutableLiveData<List<EventDetail>>()
    val eventsLiveData: LiveData<List<EventDetail>>
        get() = _eventsMutableData

    private val _storiesMutableData = MutableLiveData<List<StoriesDetail>>()
    val storiesLiveData: LiveData<List<StoriesDetail>>
        get() = _storiesMutableData

    private val _seriesMutableData = MutableLiveData<List<SerieDetail>>()
    val seriesLiveData: LiveData<List<SerieDetail>>
        get() = _seriesMutableData

    fun getSuperHeroByIdentifier(identifier: Int) {

        uiScope.launch {
            view?.showProgressBar()

            val resultSuperHero = uiScope.async { withContext(ioContext) { getSuperheroByIdentifierUseCase.execute(identifier) } }

            resultSuperHero.await().fold(
                { view?.showError(it) },
                { _superheroMutableData.postValue(it) }
            )

            val resultComicsList = uiScope.async { withContext(ioContext) { getComicListBySuperHeroIdUseCase.execute(identifier) } }

            resultComicsList.await().fold(
                { view?.showError(it) },
                { _comicsMutableData.postValue(it) }
            )

            val resultSeriesList = uiScope.async { withContext(ioContext) { getSeriesListBySuperHeroIdUseCase.execute(identifier) } }

            resultSeriesList.await().fold(
                { view?.showError(it) },
                { _seriesMutableData.postValue(it) }
            )

            val resultStoriesList = uiScope.async { withContext(ioContext) { getStoriesListBySuperHeroIdUseCase.execute(identifier) } }

            resultStoriesList.await().fold(
                { view?.showError(it) },
                { _storiesMutableData.postValue(it) }
            )

            val resultEventsList = uiScope.async { withContext(ioContext) { getEventsListBySuperHeroIdUseCase.execute(identifier) } }

            resultEventsList.await().fold(
                { view?.showError(it) },
                { _eventsMutableData.postValue(it) }
            )
            view?.hideProgressBar()
        }
    }
}