package com.mk8.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mk8.data.appdata.SuperHeroData
import com.mk8.data.fold
import com.mk8.marvelapp.screen.base.BaseViewModel
import com.mk8.screen.usecase.GetSuperheroListUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getSuperheroListUseCase: GetSuperheroListUseCase
) : BaseViewModel<MainView>() {

    private val _superHeroMutableData = MutableLiveData<List<SuperHeroData>>()
    val superHeroLiveData: LiveData<List<SuperHeroData>>
        get() = _superHeroMutableData

    override fun onCreate() {
        super.onCreate()
        getSuperHeroList()
    }

    fun getSuperHeroList() {
        view?.showProgressBar()

        uiScope.launch {

            val result = uiScope.async { withContext(ioContext) { getSuperheroListUseCase.execute() } }

            result.await().fold(
                {
                    view?.hideProgressBar()
                    view?.showErrorMessage(it)
                },
                {
                    view?.hideProgressBar()
                    _superHeroMutableData.postValue(it)
                }
            )

        }
    }
}