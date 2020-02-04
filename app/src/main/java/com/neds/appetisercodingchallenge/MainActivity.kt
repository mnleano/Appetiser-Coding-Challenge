package com.neds.appetisercodingchallenge

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.neds.appetisercodingchallenge.databinding.ActivityMainBinding
import com.neds.appetisercodingchallenge.fragments.CartFragment
import com.neds.appetisercodingchallenge.fragments.HistoryFragment
import com.neds.appetisercodingchallenge.fragments.HomeFragment
import com.neds.appetisercodingchallenge.fragments.WishListFragment
import com.neds.appetisercodingchallenge.webService.ITunesApi
import com.neds.appetisercodingchallenge.webService.model.SearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var oldFragment: Fragment? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initToolbar(binding.toolbar.toolbar, false)

        binding.navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> setFragment(HomeFragment())
                R.id.navigation_cart -> setFragment(CartFragment())
                R.id.navigation_wish_list -> setFragment(WishListFragment())
                R.id.navigation_history -> setFragment(HistoryFragment())
                else -> false
            }
        }

        binding.navigation.selectedItemId = R.id.navigation_home
    }

    private fun setFragment(fragment: Fragment): Boolean {
        if (oldFragment != fragment) {
            oldFragment = fragment
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                .commitAllowingStateLoss()
            return true
        }
        return false
    }
}
