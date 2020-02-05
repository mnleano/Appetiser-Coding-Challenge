package com.neds.appetisercodingchallenge.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField

import com.neds.appetisercodingchallenge.R
import com.neds.appetisercodingchallenge.adapter.CartAdapter
import com.neds.appetisercodingchallenge.data.Cart
import com.neds.appetisercodingchallenge.data.ObjectBoxManager
import com.neds.appetisercodingchallenge.databinding.FragmentListBinding

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: CartAdapter
    private val items = mutableListOf<Cart>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        initData()
        return binding.root
    }

    private fun initData() {
        adapter = CartAdapter(items)
        binding.recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        ObjectBoxManager.getCart()?.let {
            items.clear()
            items.addAll(it)
            adapter.notifyDataSetChanged()

            binding.tvEmpty.visibility = if (it.size > 0) View.GONE else View.VISIBLE
        }
    }

}
