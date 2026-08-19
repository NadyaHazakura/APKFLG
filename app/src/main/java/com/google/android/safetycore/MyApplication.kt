package com.google.android.safetycore

import android.app.Application
import android.content.ComponentCallbacks2
import android.content.Context
import coil.ImageLoader

class MyApplication : Application(), ComponentCallbacks2 {
    override fun onCreate() {
        super.onCreate()

        // Optionally configure Coil's ImageLoader here with smaller cache sizes
        // Keep caches conservative to reduce memory pressure and background work
        // val imageLoader = ImageLoader.Builder(this)
        //     .memoryCachePolicy(CachePolicy.ENABLED)
        //     .build()
        // coil.Coil.setImageLoader(imageLoader)

        // Schedule conservative periodic background sync only if the user has enabled it.
        // Default is false to avoid background battery usage.
        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val enabled = prefs.getBoolean("background_sync_enabled", false)
        if (enabled) {
            Scheduler.schedulePeriodicSync(this)
        }
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
