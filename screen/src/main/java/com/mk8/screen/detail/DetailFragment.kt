package com.mk8.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.mk8.data.apidata.comic.ComicDetail
import com.mk8.data.apidata.events.EventDetail
import com.mk8.data.apidata.series.SerieDetail
import com.mk8.data.apidata.stories.StoriesDetail
import com.mk8.data.appdata.SuperHeroData
import com.mk8.screen.R
import com.mk8.screen.adapter.ComicsAdapter
import com.mk8.screen.adapter.EventsAdapter
import com.mk8.screen.adapter.SeriesAdapter
import com.mk8.screen.adapter.StoriesAdapter
import com.mk8.screen.base.BaseFragment
import com.mk8.screen.extension.*
import com.mk8.screen.main.MainActivity
import kotlinx.android.synthetic.main.detail_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment(), DetailView {

    private val detailViewModel by viewModel<DetailViewModel>()

    private val comicAdapter = ComicsAdapter()
    private val seriesAdapter = SeriesAdapter()
    private val storiesAdapter = StoriesAdapter()
    private val eventsAdapter = EventsAdapter()

    private val superheroObserver = Observer<SuperHeroData> { drawSuperHero(it) }
    private val comicObserver = Observer<List<ComicDetail>> {
        comics_label.apply { visibility = if (it.isEmpty()) GONE else VISIBLE }
        comicAdapter.setList(it)
    }
    private val seriesObserver = Observer<List<SerieDetail>> {
        series_label.apply { visibility = if (it.isEmpty()) GONE else VISIBLE }
        seriesAdapter.setList(it)
    }
    private val storiesObserver = Observer<List<StoriesDetail>> {
        stories_label.apply { visibility = if (it.isEmpty()) GONE else VISIBLE }
        storiesAdapter.setList(it)
    }
    private val eventsObserver = Observer<List<EventDetail>> {
        events_label.apply { visibility = if (it.isEmpty()) GONE else VISIBLE }
        eventsAdapter.setList(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        container?.inflateLayout(R.layout.detail_layout)

    override fun initView() {
        init(detailViewModel, this)
        initAdapter()
        initObserver()

        val identifier = arguments?.let { DetailFragmentArgs.fromBundle(it).heroId } ?: 0
        detailViewModel.getSuperHeroByIdentifier(identifier)

        comics_label.apply { setOnClickListener { showHideList(this, comics_list) } }
        series_label.apply { setOnClickListener { showHideList(this, series_list) } }
        stories_label.apply { setOnClickListener { showHideList(this, stories_list) } }
        events_label.apply { setOnClickListener { showHideList(this, events_list) } }
    }

    private fun initObserver() {
        detailViewModel.apply {
            superheroLiveData.observe(viewLifecycleOwner, superheroObserver)
            comicsLiveData.observe(viewLifecycleOwner, comicObserver)
            eventsLiveData.observe(viewLifecycleOwner, eventsObserver)
            seriesLiveData.observe(viewLifecycleOwner, seriesObserver)
            storiesLiveData.observe(viewLifecycleOwner, storiesObserver)
        }
    }

    private fun initAdapter() {
        comics_list.initRecycler(comicAdapter)
        series_list.initRecycler(seriesAdapter)
        stories_list.initRecycler(storiesAdapter)
        events_list.initRecycler(eventsAdapter)
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar() {
        detail_progressbar.bringToFront()
        detail_progressbar.visible()
    }

    override fun hideProgressBar() {
        detail_progressbar.gone()
    }

    private fun showHideList(label: TextView, list: RecyclerView) {
        if (list.visibility == GONE) {
            label.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_up, 0)
            list.visible()
        } else {
            label.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down, 0)
            list.gone()
        }
    }

    private fun drawSuperHero(superHero: SuperHeroData) {
        val image = "${superHero.thumbnail.path}.${superHero.thumbnail.extension}"
        (activity as MainActivity).setToolbarTitle(superHero.name)
        description_detail.text = superHero.description
        if (superHero.thumbnail.extension == "gif") image_detail.loadGif(image) else image_detail.loadFromUrlRoundedCorners(image)
    }
}