package com.google.android.safetycore

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class MyForegroundService : Service() {
    companion object {
        const val CHANNEL_ID = "foreground_channel"
        const val NOTIF_ID = 1001
    }

    override fun onCreate() {
        super.onCreate()
        createChannel()
        // Do NOT call startForeground here unless the service truly needs to run continuously.
        // Starting a persistent foreground service keeps the device awake and can increase battery drain.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Delegate work to WorkManager (constrained, backoff, periodic) instead of keeping a foreground service
        val workRequest = OneTimeWorkRequest.from(MyWorker::class.java)
        WorkManager.getInstance(this).enqueue(workRequest)

        // Stop the foreground service immediately to avoid holding a wake lock / notification
        stopForeground(true)
        stopSelf()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(NotificationManager::class.java)
            nm?.createNotificationChannel(
                NotificationChannel(CHANNEL_ID, "Foreground", NotificationManager.IMPORTANCE_LOW)
            )
        }
    }
}
