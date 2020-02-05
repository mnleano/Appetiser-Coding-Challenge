package com.neds.appetisercodingchallenge.data

import com.neds.appetisercodingchallenge.model.ResultModel
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query
import timber.log.Timber

object ObjectBoxManager {

    private val recentBox: Box<Recent>
        get() {
            return ObjectBox.boxStore.boxFor()
        }

    private val wishListBox: Box<WishList>
        get() {
            return ObjectBox.boxStore.boxFor()
        }

    fun putRecent(r: ResultModel) {
        recentBox.query { equal(Recent_.trackId, r.trackId) }.findFirst()
            ?.let { recentBox.remove(it.id) }

        val recent = Recent(
            0, r.trackId, r.icon,
            r.kind, r.title, r.artist,
            r.currency, r.price, r.genre,
            r.releaseDate, r.description
        )

        recentBox.put(recent)
    }

    fun putWishList(r: ResultModel) {
        if (isWishlisted(r.trackId))
            wishListBox.query { equal(WishList_.trackId, r.trackId) }.findFirst()
                ?.let { wishListBox.remove(it.id) }
        else {
            val wishList = WishList(
                0, r.trackId, r.icon,
                r.kind, r.title, r.artist,
                r.currency, r.price, r.genre,
                r.releaseDate, r.description
            )

            wishListBox.put(wishList)
        }
    }

    fun getRecent(): MutableList<Recent> {
        Timber.d("getRecent: total=${recentBox.count()}")
        return recentBox.all
    }

    fun isWishlisted(id: Long): Boolean {
        return wishListBox.query { equal(WishList_.trackId, id) }.findFirst()?.let { true } ?: false
    }


}