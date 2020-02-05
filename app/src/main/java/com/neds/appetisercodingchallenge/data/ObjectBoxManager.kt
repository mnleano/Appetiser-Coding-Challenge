package com.neds.appetisercodingchallenge.data

import com.neds.appetisercodingchallenge.model.ResultModel
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query

object ObjectBoxManager {

    private val recentBox: Box<Recent>
        get() {
            return ObjectBox.boxStore.boxFor()
        }

    private val cartBox: Box<Cart>
        get() {
            return ObjectBox.boxStore.boxFor()
        }

    private val wishListBox: Box<WishList>
        get() {
            return ObjectBox.boxStore.boxFor()
        }

    fun addToCart(r: ResultModel) {
        cartBox.put(
            Cart(
                0, r.trackId, r.icon,
                r.kind, r.title, r.artist,
                r.currency, r.price, r.genre,
                r.releaseDate, r.description
            )
        )
    }

    fun removeToCart(id: Long) {
        cartBox.query { equal(Cart_.trackId, id) }.findFirst()?.let {
            cartBox.remove(it.id)
        }
    }

    fun putWishList(r: ResultModel) {
        if (isWishListed(r.trackId))
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

    fun getWishList(): MutableList<WishList>? {
        return wishListBox.all
    }

    fun removeWishList(id: Long) {
        wishListBox.remove(id)
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

    fun isWishListed(id: Long): Boolean {
        return wishListBox.query { equal(WishList_.trackId, id) }.findFirst()?.let { true } ?: false
    }


}