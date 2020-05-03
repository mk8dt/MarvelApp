package com.mk8.data.apidata.comic

import com.mk8.data.apidata.Thumbnail

data class ComicDetail(
    val id: Int,
    val title: String,
    val variantDescription: String,
    val description: String,
    val thumbnail: Thumbnail,
    val prices: List<PriceDetail>
)