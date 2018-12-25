package com.alenabekrina.plants.notifications

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v4.content.ContextCompat.getSystemService
import java.util.*
import javax.inject.Inject


class PlantsNotificationManager @Inject constructor(val context: Application) {
    val ANDROID_CHANNEL_ID = "com.chikeandroid.tutsplustalerts.ANDROID"
    val ANDROID_CHANNEL_NAME = "ANDROID CHANNEL"

    fun scheduleNotification() {
        createChannel()

        val alarmManager = getSystemService(context, AlarmManager::class.java)
        val intent = Intent(context, AlarmReceiver::class.java)
        val alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        // set for 3 seconds later
        alarmManager!!.set(AlarmManager.RTC, Calendar.getInstance().timeInMillis + 3000, alarmIntent)
    }

    fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            //mChannel.description = description
            mChannel.enableLights(true)
            mChannel.lightColor = Color.parseColor("#5B3C88")
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            val manager = (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            manager.createNotificationChannel(mChannel)
        }
    }
}
