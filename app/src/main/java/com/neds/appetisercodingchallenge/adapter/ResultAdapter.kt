package com.neds.appetisercodingchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.neds.appetisercodingchallenge.R
import com.neds.appetisercodingchallenge.databinding.RowResultBinding
import com.neds.appetisercodingchallenge.model.ResultModel

class ResultAdapter(private val results: MutableList<ResultModel>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<RowResultBinding>(
            inflater!!,
            R.layout.row_result,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.binding.data = result
    }

    inner class ViewHolder(val binding: RowResultBinding) : RecyclerView.ViewHolder(binding.root)

}