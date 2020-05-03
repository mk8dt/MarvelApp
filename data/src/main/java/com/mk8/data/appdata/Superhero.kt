package com.mk8.data.appdata

import com.mk8.data.apidata.Thumbnail

data class Superhero(
    override val id: Int,
    override val name: String,
    override val description: String,
    override val thumbnail: Thumbnail
) : SuperHeroData