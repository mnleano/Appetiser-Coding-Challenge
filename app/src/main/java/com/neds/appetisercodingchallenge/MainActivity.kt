package com.neds.appetisercodingchallenge

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neds.appetisercodingchallenge.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initToolbar(binding.toolbar.toolbar, false)
//        Timber.d("onCreate")
//        val result = ITunesApi.search()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribeWith(object : DisposableObserver<SearchResponse>() {
//                override fun onComplete() {
//                    Timber.d("onComplete")
//                }
//
//                override fun onNext(t: SearchResponse) {
//                    Timber.d("onNext=$t")
//                }
//
//                override fun onError(e: Throwable) {
//                    Timber.e(e, "onError=")
//                }
//
//            })
//        result.dispose()
    }
}
