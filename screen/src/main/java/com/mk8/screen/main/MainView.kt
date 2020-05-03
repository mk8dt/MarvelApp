package com.mk8.screen.main

import com.mk8.screen.base.BaseView

interface MainView : BaseView {

    fun showErrorMessage(message: String)
}