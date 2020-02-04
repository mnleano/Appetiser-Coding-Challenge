package com.neds.appetisercodingchallenge.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.neds.appetisercodingchallenge.R
import com.neds.appetisercodingchallenge.adapter.ResultAdapter
import com.neds.appetisercodingchallenge.databinding.FragmentHomeBinding
import com.neds.appetisercodingchallenge.model.ResultModel
import com.neds.appetisercodingchallenge.webService.ITunesApi
import com.neds.appetisercodingchallenge.webService.model.Result
import com.neds.appetisercodingchallenge.webService.model.SearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: ResultAdapter
    private var results = mutableListOf<ResultModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        initData()
        return binding.root
    }

    @SuppressLint("CheckResult")
    private fun initData() {

        adapter = ResultAdapter(results)
        binding.recyclerView.adapter = adapter

        ITunesApi.search()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableObserver<SearchResponse>() {
                override fun onComplete() {
                    Timber.d("onComplete")
                }

                override fun onNext(t: SearchResponse) {
                    results.addAll(t.results.filter { r ->
                        r.trackName != ""
                    }.map { r ->
                        ResultModel(
                            r.artworkUrl100,
                            r.kind,
                            r.trackName,
                            r.artistName,
                            r.currency,
                            r.trackPrice,
                            r.primaryGenreName,
                            r.releaseDate
                        )
                    })
                    adapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    Timber.e(e, "onError=")
                }
            })
    }


}
