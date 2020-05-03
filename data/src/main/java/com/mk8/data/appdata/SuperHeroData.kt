package com.mk8.data.appdata

import com.mk8.data.apidata.Thumbnail

interface SuperHeroData {
    val id: Int
    val name: String
    val description: String
    val thumbnail: Thumbnail
}