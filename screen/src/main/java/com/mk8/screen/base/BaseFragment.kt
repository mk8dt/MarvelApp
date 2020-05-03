package com.mk8.screen.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.mk8.marvelapp.screen.base.BaseViewModel
import com.mk8.screen.R

abstract class BaseFragment : Fragment() {

    private var currentViewModel: BaseViewModel<*>? = null
    protected val navController: NavController? by lazy { activity?.findNavController(R.id.nav_host) }

    fun <V : BaseView> init(viewModel: BaseViewModel<V>, view: V) {
        currentViewModel = ViewModelProvider(this)[viewModel::class.java]
        (currentViewModel as BaseViewModel<V>).init(view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun initView()

    override fun onDestroy() {
        super.onDestroy()
        currentViewModel?.onDestroy()
    }
}