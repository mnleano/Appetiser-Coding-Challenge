package com.neds.appetisercodingchallenge

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object CommonBindingUtils {

    @BindingAdapter(value = ["icon", "kind"])
    @JvmStatic
    fun setIcon(view: ImageView, url: String, kind: String) {
        val placeholder = if (kind == "song") R.drawable.placeholder_song
        else R.drawable.placeholder_video
        Picasso.get().load(url).placeholder(placeholder).into(view)
    }
}