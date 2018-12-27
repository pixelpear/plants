package com.alenabekrina.plants.notifications

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v4.content.ContextCompat.getSystemService
import org.threeten.bp.OffsetDateTime
import java.util.*
import javax.inject.Inject


class NotificationsUtils @Inject constructor(private val context: Application) {
    val ANDROID_CHANNEL_ID = "com.alenabekrina.plants"
    val ANDROID_CHANNEL_NAME = "PLANTS CHANNEL"

    fun scheduleNotification(date: OffsetDateTime) {
        createChannel()
        val alarmManager = getSystemService(context, AlarmManager::class.java)
        val intent = Intent(context, AlarmReceiver::class.java)
        val alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        // set for 3 seconds later
        alarmManager!!.set(AlarmManager.RTC, date.toEpochSecond() * 1000, alarmIntent)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.enableLights(true)
            channel.lightColor = Color.parseColor("#5B3C88")
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
