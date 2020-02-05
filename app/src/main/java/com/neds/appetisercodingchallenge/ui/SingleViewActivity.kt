package com.neds.appetisercodingchallenge.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neds.appetisercodingchallenge.R
import com.neds.appetisercodingchallenge.data.ObjectBoxManager
import com.neds.appetisercodingchallenge.databinding.ActivitySingleViewBinding
import com.neds.appetisercodingchallenge.model.ResultModel

class SingleViewActivity : BaseActivity() {

    private lateinit var binding: ActivitySingleViewBinding
    private var result: ResultModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_view)
        initToolbar(binding.toolbar.toolbar)
        intent.extras?.getSerializable(EXTRA_DATA)?.let {
            result = it as ResultModel
            result?.let { r ->
                r.wishList.set(ObjectBoxManager.isWishListed(r.trackId))
                binding.data = r
            }
        }

        binding.ivWishList.setOnClickListener {
            result?.let { r ->
                ObjectBoxManager.putWishList(r)
                r.wishList.set(ObjectBoxManager.isWishListed(r.trackId))
            }
        }
    }

    companion object {
        private const val EXTRA_DATA = "extraData"

        fun makeIntent(context: Context, result: ResultModel): Intent {
            ObjectBoxManager.putRecent(result)
            return Intent(context, SingleViewActivity::class.java).putExtra(
                EXTRA_DATA, result)
        }
    }
}
