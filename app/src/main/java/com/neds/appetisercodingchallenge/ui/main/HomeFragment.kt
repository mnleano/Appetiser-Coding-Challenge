package com.neds.appetisercodingchallenge.ui.main


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.neds.appetisercodingchallenge.R
import com.neds.appetisercodingchallenge.adapter.ResultAdapter
import com.neds.appetisercodingchallenge.data.ObjectBoxManager
import com.neds.appetisercodingchallenge.databinding.FragmentListBinding
import com.neds.appetisercodingchallenge.model.ResultModel
import com.neds.appetisercodingchallenge.webService.ITunesApi
import com.neds.appetisercodingchallenge.webService.model.SearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

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

    @SuppressLint("CheckResult")
    private fun initData() {

        adapter = ResultAdapter(results,
            object : ResultAdapter.Listener {
                override fun onClick(r: ResultModel) {
                    Timber.d("initData: onClick")
                    ObjectBoxManager.putRecent(r)
                    startActivity(SingleViewActivity.makeIntent(activity!!, r))
                }
            })

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
                        r.trackId != null &&
                                r.trackName != "" &&
                                (r.shortDescription != null || r.longDescription != null)
                    }.map { r ->
                        ResultModel(
                            r.trackId?.toLong() ?: 0,
                            r.artworkUrl100,
                            r.kind,
                            r.trackName,
                            r.artistName,
                            r.currency,
                            r.trackPrice,
                            r.primaryGenreName,
                            r.releaseDate,
                            r.longDescription ?: r.shortDescription ?: ""
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
