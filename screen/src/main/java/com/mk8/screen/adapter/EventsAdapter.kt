package com.mk8.screen.adapter

import android.view.View
import android.view.ViewGroup
import com.mk8.data.apidata.events.EventDetail
import com.mk8.screen.R
import com.mk8.screen.base.BaseRecyclerView
import com.mk8.screen.base.BaseViewHolder
import com.mk8.screen.extension.inflateLayout
import com.mk8.screen.extension.loadFromUrlCircle
import kotlinx.android.synthetic.main.events_itemview.view.*

class EventsAdapter : BaseRecyclerView<EventDetail, EventsAdapter.Holder>() {

    override fun getViewHolder(parent: ViewGroup): Holder = Holder(parent.inflateLayout(R.layout.events_itemview))

    inner class Holder(itemView: View) : BaseViewHolder<EventDetail>(itemView) {

        override fun bindData(item: EventDetail) {
            val image = "${item.thumbnail.path}.${item.thumbnail.extension}"
            itemView.apply {
                event_image_itemview.loadFromUrlCircle(image)
                event_title_itemview.text = item.title
                event_description_itemview.text = item.description
            }
        }
    }
}