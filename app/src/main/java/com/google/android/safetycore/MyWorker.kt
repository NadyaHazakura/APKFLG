package com.google.android.safetycore

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            // Lightweight background task: sync, cleanup small caches, update local DB
            // Avoid large allocations and bitmaps here
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
