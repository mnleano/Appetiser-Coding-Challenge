package com.neds.appetisercodingchallenge.model

class ResultModel(
    val trackId: Int,
    val icon: String?,
    val kind: String?,
    val title: String?,
    val artist: String?,
    val currency: String?,
    val price: Double?,
    val genre: String?,
    val releaseDate: String?,
    val description: String?,
    val genres: List<String>?
)