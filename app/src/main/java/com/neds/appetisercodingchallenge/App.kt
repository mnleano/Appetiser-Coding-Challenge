package com.neds.appetisercodingchallenge

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.neds.appetisercodingchallenge.data.ObjectBox
import io.objectbox.BoxStore
import timber.log.Timber

class App : MultiDexApplication() {

    lateinit var boxStore: BoxStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        boxStore = ObjectBox.build(this, "ACC_DB")

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}