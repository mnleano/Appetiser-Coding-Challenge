package com.neds.appetisercodingchallenge.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.neds.appetisercodingchallenge.R
import com.neds.appetisercodingchallenge.adapter.ResultAdapter
import com.neds.appetisercodingchallenge.data.ObjectBoxManager
import com.neds.appetisercodingchallenge.data.Recent
import com.neds.appetisercodingchallenge.databinding.FragmentListBinding
import com.neds.appetisercodingchallenge.model.ResultModel

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: ResultAdapter

    private var results = mutableListOf<ResultModel>()
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
        adapter = ResultAdapter(results)
        binding.recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        ObjectBoxManager.getRecents()?.let {
            results.clear()
            results.addAll(it.map { r -> ResultModel.map(r) })
            adapter.notifyDataSetChanged()

            binding.tvEmpty.visibility = if (it.size > 0) View.GONE else View.VISIBLE
        }
    }


}
