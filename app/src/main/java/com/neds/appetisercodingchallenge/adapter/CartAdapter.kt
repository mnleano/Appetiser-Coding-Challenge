package com.neds.appetisercodingchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.neds.appetisercodingchallenge.R
import com.neds.appetisercodingchallenge.data.Cart
import com.neds.appetisercodingchallenge.data.ObjectBoxManager
import com.neds.appetisercodingchallenge.databinding.RowCartBinding
import com.neds.appetisercodingchallenge.model.ResultModel
import com.neds.appetisercodingchallenge.ui.SingleViewActivity

class CartAdapter(private val items: MutableList<Cart>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<RowCartBinding>(
            inflater!!,
            R.layout.row_cart,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.data = item
        holder.binding.root.setOnClickListener {
            val context = holder.binding.root.context
            context.startActivity(SingleViewActivity.makeIntent(context, ResultModel.map(item)))
        }

        holder.binding.ivDelete.setOnClickListener {
            ObjectBoxManager.removeToCart(item.id)
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }


    inner class ViewHolder(val binding: RowCartBinding) : RecyclerView.ViewHolder(binding.root)
}