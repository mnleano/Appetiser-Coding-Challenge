package com.neds.appetisercodingchallenge.ui

import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    fun initToolbar(toolbar: Toolbar, showAsUp: Boolean = true) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(showAsUp)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if(supportFragmentManager.backStackEntryCount > 0)
                    supportFragmentManager.popBackStack()
                else onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}