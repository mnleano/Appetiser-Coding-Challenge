package com.neds.appetisercodingchallenge.model

class ResultModel(
    val id: Int,
    val icon: String?,
    val kind: String?,
    val title: String?,
    val artist: String?,
    val currency: String?,
    val price: Double?,
    val genre: String?,
    val timestamp: String?,
    val description: String,
    val genres: List<String>
)