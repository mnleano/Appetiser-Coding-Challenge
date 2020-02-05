package com.neds.appetisercodingchallenge.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.neds.appetisercodingchallenge.R
import com.neds.appetisercodingchallenge.adapter.ResultAdapter
import com.neds.appetisercodingchallenge.adapter.WishListAdapter
import com.neds.appetisercodingchallenge.data.ObjectBoxManager
import com.neds.appetisercodingchallenge.data.WishList
import com.neds.appetisercodingchallenge.databinding.FragmentListBinding
import com.neds.appetisercodingchallenge.model.ResultModel

/**
 * A simple [Fragment] subclass.
 */
class WishListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: WishListAdapter
    private var wishes = mutableListOf<WishList>()
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
        adapter = WishListAdapter(wishes)
        binding.recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        ObjectBoxManager.getWishList()?.let {
            wishes.clear()
            wishes.addAll(it)
            adapter.notifyDataSetChanged()

            binding.tvEmpty.visibility = if (it.size > 0) View.GONE else View.VISIBLE
        }

    }
}
