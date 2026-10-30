package com.flaneur.core.location

import android.app.Service
import android.content.Intent
import android.os.IBinder

class WalkingForegroundService : Service() {

    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(this)
        val notification = NotificationHelper.buildNotification(this, "Отслеживание геопозиции...")
        startForeground(NotificationHelper.NOTIFICATION_ID, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
