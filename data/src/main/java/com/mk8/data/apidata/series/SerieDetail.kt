package com.mk8.data.apidata.series

import com.mk8.data.apidata.Thumbnail

data class SerieDetail(
    val title: String,
    val description: String,
    val startYear: Int,
    val endYear: Int,
    val thumbnail: Thumbnail
)