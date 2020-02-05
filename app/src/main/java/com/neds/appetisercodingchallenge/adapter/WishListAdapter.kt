package com.neds.appetisercodingchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.neds.appetisercodingchallenge.R
import com.neds.appetisercodingchallenge.data.ObjectBoxManager
import com.neds.appetisercodingchallenge.data.WishList
import com.neds.appetisercodingchallenge.databinding.RowWishListBinding
import com.neds.appetisercodingchallenge.model.ResultModel
import com.neds.appetisercodingchallenge.ui.SingleViewActivity

class WishListAdapter(private val wishes: MutableList<WishList>) :
    RecyclerView.Adapter<WishListAdapter.ViewHolder>() {

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<RowWishListBinding>(
            inflater!!,
            R.layout.row_wish_list,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return wishes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wish = wishes[position]
        holder.binding.data = wish
        holder.binding.root.setOnClickListener {
            val context = holder.binding.root.context
            context.startActivity(SingleViewActivity.makeIntent(context, ResultModel.map(wish)))
        }

        holder.binding.ivDelete.setOnClickListener {
            ObjectBoxManager.removeWishList(wish.id)
            wishes.removeAt(position)
            notifyItemRemoved(position)
        }

        holder.binding.btnAdd.setOnClickListener {
            ObjectBoxManager.addToCart(ResultModel.map(wish))
        }
    }


    inner class ViewHolder(val binding: RowWishListBinding) : RecyclerView.ViewHolder(binding.root)
}