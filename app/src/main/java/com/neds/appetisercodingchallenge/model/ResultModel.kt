package com.neds.appetisercodingchallenge.model

import androidx.databinding.ObservableBoolean
import java.io.Serializable

class ResultModel(
    val trackId: Long,
    val icon: String?,
    val kind: String?,
    val title: String?,
    val artist: String?,
    val currency: String?,
    val price: Double?,
    val genre: String?,
    val releaseDate: String?,
    val description: String?
) : Serializable{
    val wishList = ObservableBoolean(false)
}