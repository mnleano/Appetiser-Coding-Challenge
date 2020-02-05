package com.neds.appetisercodingchallenge.model

import androidx.databinding.ObservableBoolean
import com.neds.appetisercodingchallenge.data.Cart
import com.neds.appetisercodingchallenge.data.Recent
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
        fun map(c: Cart): ResultModel{
            return ResultModel(
                c.trackId,
                c.icon,
                c.kind,
                c.title,
                c.artist,
                c.currency,
                c.price,
                c.genre,
                c.releaseDate,
                c.description
            )
        }

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

        fun map(r: Recent): ResultModel{
            return ResultModel(
                r.trackId,
                r.icon,
                r.kind,
                r.title,
                r.artist,
                r.currency,
                r.price,
                r.genre,
                r.releaseDate,
                r.description
            )
        }
    }
}