package com.google.android.safetycore

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import android.util.Log

class MyWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            // Lightweight background task: sync, cleanup small caches, update local DB
            // Avoid large allocations and bitmaps here
            Result.success()
        } catch (e: IOException) {
            // Network / IO errors are often transient -> retry
            Log.w("MyWorker", "Transient error, will retry", e)
            Result.retry()
        } catch (e: Exception) {
            // Non-transient errors should fail to avoid repeated wakeups
            Log.e("MyWorker", "Non-transient error, failing", e)
            Result.failure()
        }
    }
}
