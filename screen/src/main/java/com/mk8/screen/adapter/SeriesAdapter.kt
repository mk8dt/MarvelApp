package com.mk8.screen.adapter

import android.view.View
import android.view.ViewGroup
import com.mk8.data.apidata.series.SerieDetail
import com.mk8.screen.R
import com.mk8.screen.base.BaseRecyclerView
import com.mk8.screen.base.BaseViewHolder
import com.mk8.screen.extension.inflateLayout
import com.mk8.screen.extension.loadFromUrlCircle
import kotlinx.android.synthetic.main.series_itemview.view.*

class SeriesAdapter : BaseRecyclerView<SerieDetail, SeriesAdapter.Holder>() {

    override fun getViewHolder(parent: ViewGroup): Holder = Holder(parent.inflateLayout(R.layout.series_itemview))

    inner class Holder(itemView: View) : BaseViewHolder<SerieDetail>(itemView) {
        override fun bindData(item: SerieDetail) {
            itemView.apply {
                val image = "${item.thumbnail.path}.${item.thumbnail.extension}"
                series_image_itemview.loadFromUrlCircle(image)
                series_title_itemview.text = item.title
                series_description_itemview.text = item.description
                start_year_itemview.text = item.startYear.toString()
                end_year_itemview.text = item.endYear.toString()
            }
        }
    }
}