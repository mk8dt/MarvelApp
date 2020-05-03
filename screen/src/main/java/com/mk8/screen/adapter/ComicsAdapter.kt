package com.mk8.screen.adapter

import android.view.View
import android.view.ViewGroup
import com.mk8.data.apidata.comic.ComicDetail
import com.mk8.screen.R
import com.mk8.screen.base.BaseRecyclerView
import com.mk8.screen.base.BaseViewHolder
import com.mk8.screen.extension.gone
import com.mk8.screen.extension.inflateLayout
import com.mk8.screen.extension.loadFromUrlCircle
import kotlinx.android.synthetic.main.comic_itemview.view.*

class ComicsAdapter : BaseRecyclerView<ComicDetail, ComicsAdapter.Holder>() {

    override fun getViewHolder(parent: ViewGroup): Holder = Holder(parent.inflateLayout(R.layout.comic_itemview))

    inner class Holder(itemView: View) : BaseViewHolder<ComicDetail>(itemView) {

        override fun bindData(item: ComicDetail) {

            itemView.apply {
                val image = "${item.thumbnail.path}.${item.thumbnail.extension}"
                comic_image_itemview.loadFromUrlCircle(image)
                comic_title_itemview.text = item.title

                if (item.variantDescription.isEmpty()) comic_variant_description_itemview.gone()
                else comic_variant_description_itemview.text = item.variantDescription

                comic_description_itemview.text = item.description

                item.prices.forEach {
                    if (it.type == "printPrice") print_price_text.text = it.price.toString()
                    if (it.type == " digitalPurchasePrice") digital_price_text.text = it.price.toString()
                }
            }
        }
    }
}