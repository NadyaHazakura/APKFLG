package com.google.android.safetycore

import android.app.Application
import android.content.ComponentCallbacks2
import coil.ImageLoader

class MyApplication : Application(), ComponentCallbacks2 {
    override fun onCreate() {
        super.onCreate()
        // Optionally configure Coil's ImageLoader here with smaller cache sizes
        // val imageLoader = ImageLoader.Builder(this)
        //     .memoryCachePolicy(CachePolicy.ENABLED)
        //     .build()
        // coil.Coil.setImageLoader(imageLoader)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when (level) {
            ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN -> {
                // App UI is hidden; clear UI caches
            }
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL,
            ComponentCallbacks2.TRIM_MEMORY_BACKGROUND -> {
                // Release caches and reduce memory pressure
                try {
                    val imageLoader = coil.Coil.imageLoader(this)
                    imageLoader.memoryCache?.clear()
                } catch (_: Throwable) { /* ignore */ }
            }
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        try {
            coil.Coil.imageLoader(this).memoryCache?.clear()
        } catch (_: Throwable) { /* ignore */ }
    }
}
