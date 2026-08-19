package com.google.android.safetycore

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

object Scheduler {
    /**
     * Schedule a conservative periodic sync that only runs under good conditions.
     * Uses a 4-hour interval by default — increase if you can tolerate less frequent updates.
     */
    fun schedulePeriodicSync(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val work = PeriodicWorkRequestBuilder<MyWorker>(4, TimeUnit.HOURS)
            .setConstraints(constraints)
            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 30, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "safetycore_sync",
                ExistingPeriodicWorkPolicy.KEEP,
                work
            )
    }
}
