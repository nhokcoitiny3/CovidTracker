package com.tiny.covidtracker.ui.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso

class Utils {
    companion object {
        private var instance: Utils? = null

        @JvmStatic
        fun g(): Utils {
            if (instance == null) instance = Utils()
            return instance!!
        }
    }
    var picasso: Picasso? = null
    fun providePicasso(context: Context): Picasso {
        if (picasso == null) picasso =
            Picasso.Builder(context)
                .downloader(OkHttp3Downloader(context.cacheDir, 250000000))
                .memoryCache(LruCache(419430400))
                .listener { picasso: Picasso?, uri: Uri?, exception: Exception? ->
                    Log.wtf(
                        "PICASSO", exception ?: java.lang.Exception("NoExcep")
                    )
                }
                .build()
        return picasso!!
    }
}