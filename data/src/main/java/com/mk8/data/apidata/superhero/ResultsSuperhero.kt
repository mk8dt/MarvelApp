package com.mk8.data.apidata.superhero

import com.mk8.data.apidata.Thumbnail

data class ResultsSuperhero(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)