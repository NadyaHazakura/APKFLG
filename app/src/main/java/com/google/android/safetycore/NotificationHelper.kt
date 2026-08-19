package com.google.android.safetycore

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationHelper {
    const val CHANNEL_ID_DEFAULT = "default_channel"

    fun ensureDefaultChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = context.getSystemService(NotificationManager::class.java)
            nm?.createNotificationChannel(
                android.app.NotificationChannel(CHANNEL_ID_DEFAULT, "Default", NotificationManager.IMPORTANCE_DEFAULT)
            )
        }
    }

    fun buildNotification(context: Context, title: String, text: String) =
        NotificationCompat.Builder(context, CHANNEL_ID_DEFAULT)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setOnlyAlertOnce(true)
            .build()
}
