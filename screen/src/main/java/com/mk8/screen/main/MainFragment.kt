package com.mk8.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.mk8.data.appdata.SuperHeroData
import com.mk8.screen.R
import com.mk8.screen.base.BaseFragment
import com.mk8.screen.base.BaseRecyclerView
import com.mk8.screen.base.BaseViewHolder
import com.mk8.screen.extension.*
import kotlinx.android.synthetic.main.main_layout.*
import kotlinx.android.synthetic.main.superhero_itemview.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(), MainView {

    private val mainViewModel by viewModel<MainViewModel>()
    private val superHeroAdapter = SuperHeroAdapter { routeToDetail(it) }
    private val superHeroObserver = Observer<List<SuperHeroData>> {
        error_label.gone()
        superHeroAdapter.setList(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        container?.inflateLayout(R.layout.main_layout)

    override fun initView() {
        init(mainViewModel, this)
        (activity as MainActivity).setToolbarTitle(context?.getString(R.string.app_name))

        super_hero_list.initRecycler(superHeroAdapter)

        error_label.setOnClickListener { mainViewModel.getSuperHeroList() }

        mainViewModel.superHeroLiveData.observe(viewLifecycleOwner, superHeroObserver)
    }

    override fun showErrorMessage(message: String) {
        error_label.apply {
            text = message
            visible()
        }
    }

    override fun showProgressBar() {
        main_progressbar.visible()
    }

    override fun hideProgressBar() {
        main_progressbar.gone()
    }

    private fun routeToDetail(identifier: Int) {
        val action = MainFragmentDirections.routeToDetail(identifier)
        navController?.navigate(action)
    }

    inner class SuperHeroAdapter(private val onClick: (Int) -> Unit) : BaseRecyclerView<SuperHeroData, SuperHeroAdapter.Holder>() {

        override fun getViewHolder(parent: ViewGroup): Holder = Holder(parent.inflateLayout(R.layout.superhero_itemview))

        inner class Holder(itemView: View) : BaseViewHolder<SuperHeroData>(itemView) {

            override fun bindData(item: SuperHeroData) {
                itemView.apply {
                    val image = "${item.thumbnail.path}.${item.thumbnail.extension}"
                    if (item.thumbnail.extension == "gif") image_itemview.loadGif(image) else image_itemview.loadFromUrlCircle(image)
                    name_itemview.text = item.name
                    description_hero_itemview.text = item.description
                    setOnClickListener { onClick.invoke(item.id) }
                }
            }
        }
    }
}