package com.neds.appetisercodingchallenge.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.neds.appetisercodingchallenge.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

object CommonBindingUtils {

    @BindingAdapter(value = ["icon", "kind"])
    @JvmStatic
    fun setIcon(view: ImageView, url: String?, kind: String?) {
        val placeholder = if (kind == "song") R.drawable.placeholder_song
        else R.drawable.placeholder_video
        Picasso.get().load(url).placeholder(placeholder).into(view)
    }

    @BindingAdapter(value = ["currency", "price"])
    @JvmStatic
    fun setPrice(view: TextView, currency: String, price: Double) {
        val text = "$currency $price"
        view.text = text
    }

    @BindingAdapter("timestamp")
    @JvmStatic
    fun setTimestamp(view: TextView, timestamp: String) {
        var sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date = sdf.parse(timestamp)
        sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        view.text = view.context.getString(R.string.release_date, sdf.format(date))

    }
}