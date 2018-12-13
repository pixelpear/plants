package com.alenabekrina.plants.notifications

import javax.inject.Inject
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.support.v4.content.ContextCompat.getSystemService



class NotificationManager @Inject constructor(val context: Context) {
    private fun scheduleNotification() {
        val alarmManager = getSystemService(context, AlarmManager::class.java)
        val intent = Intent(context, AlarmReceiver::class)
        val alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        // set for 30 seconds later
        alarmManager!!.set(AlarmManager.RTC, Calendar.getInstance().getTimeInMillis() + 30000, alarmIntent)
    }
}
