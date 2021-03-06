package com.mk8.marvelapp.screen.base

import androidx.lifecycle.ViewModel
import com.mk8.screen.base.BaseView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

open class BaseViewModel<V : BaseView> : ViewModel() {

    private lateinit var job: Job
    protected lateinit var uiScope: CoroutineScope
    protected lateinit var ioContext: CoroutineContext
    protected var view: V? = null

    fun init(viewModelView: V) {
        view = viewModelView
        onCreate()
    }

    protected open fun onCreate() {
        job = Job()
        uiScope = CoroutineScope(Dispatchers.Main + job)
        ioContext = Dispatchers.IO + job
    }

    fun onDestroy() {
        uiScope.cancel()
        ioContext.cancel()
    }
}