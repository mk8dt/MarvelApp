package com.mk8.screen.adapter

import android.view.View
import android.view.ViewGroup
import com.mk8.data.apidata.stories.StoriesDetail
import com.mk8.screen.R
import com.mk8.screen.base.BaseRecyclerView
import com.mk8.screen.base.BaseViewHolder
import com.mk8.screen.extension.inflateLayout
import kotlinx.android.synthetic.main.stories_itemview.view.*

class StoriesAdapter : BaseRecyclerView<StoriesDetail, StoriesAdapter.Holder>() {

    override fun getViewHolder(parent: ViewGroup): Holder = Holder(parent.inflateLayout(R.layout.stories_itemview))

    inner class Holder(itemView: View) : BaseViewHolder<StoriesDetail>(itemView) {

        override fun bindData(item: StoriesDetail) {
            itemView.apply {
                stories_title.text = item.title
                stories_type.text = item.type
            }
        }
    }
}