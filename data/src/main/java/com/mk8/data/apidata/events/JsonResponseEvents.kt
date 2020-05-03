package com.mk8.data.apidata.events

data class JsonResponseEvents(
    val code: Int,
    val status: String,
    val data: DataResponseEvents
)