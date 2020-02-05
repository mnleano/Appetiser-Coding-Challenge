package com.neds.appetisercodingchallenge.data

import android.content.Context
import com.neds.appetisercodingchallenge.BuildConfig
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import timber.log.Timber


object ObjectBox {
    lateinit var boxStore: BoxStore
        private set

    fun build(context: Context, name: String): BoxStore {

        try {
            // This is the minimal setup required on Android
            boxStore = MyObjectBox.builder().name(name).androidContext(context).maxReaders(480)
                .queryAttempts(4).build()
            if (BuildConfig.DEBUG) {
                val started = AndroidObjectBrowser(boxStore).start(context)
                Timber.i("ObjectBrowser started: $started")
            }
        } catch (e: Exception) {
            // If you made change of the object box data model, read this document
            // See https://docs.objectbox.io/advanced/data-model-updates
            Timber.e(
                "Can't build object box, If you made change of the object box data model, read this document https://docs.objectbox.io/advanced/data-model-updates",
                e
            )
        }

        return boxStore
//         Example how you could use a custom dir in "external storage"
//         (Android 6+ note: give the app storage permission in app info settings)
//        val directory = File(Environment.getExternalStorageDirectory(), "objectbox-notes");
//        boxStore = MyObjectBox.builder().androidContext(context.applicationContext)
//            .directory(directory)
//            .build()
    }

    fun deleteAll(context: Context, name: String) {
        try {
            boxStore.close()
            boxStore.deleteAllFiles()
            build(context, name)
        } catch (e: Exception) {
            Timber.e("Can't delete all", e)
        }
    }
}