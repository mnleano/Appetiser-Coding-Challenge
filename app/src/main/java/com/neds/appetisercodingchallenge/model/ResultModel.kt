package com.neds.appetisercodingchallenge.model

import androidx.databinding.ObservableBoolean
import com.neds.appetisercodingchallenge.data.WishList
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
) : Serializable {
    val wishList = ObservableBoolean(false)

    companion object {
        fun map(w: WishList): ResultModel {
            return ResultModel(
                w.trackId,
                w.icon,
                w.kind,
                w.title,
                w.artist,
                w.currency,
                w.price,
                w.genre,
                w.releaseDate,
                w.description
            )
        }
    }
}