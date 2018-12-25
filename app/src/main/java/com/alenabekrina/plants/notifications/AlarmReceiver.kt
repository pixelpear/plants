package com.alenabekrina.plants.notifications

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.alenabekrina.plants.App
import com.alenabekrina.plants.R
import com.alenabekrina.plants.view.PlantsListActivity
import javax.inject.Inject


class AlarmReceiver: BroadcastReceiver() {
    @Inject
    lateinit var notificationManager: PlantsNotificationManager

    val uniqueID = 3657554

    override fun onReceive(context: Context, intent: Intent) {
        val app = context.applicationContext as App
        app.component.injectAlarmReceiver(this)

        val n = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(context, notificationManager.ANDROID_CHANNEL_ID)
        } else {
            NotificationCompat.Builder(context)
        }

        n.setSmallIcon(R.mipmap.ic_launcher)
        n.setTicker("ticker")
        n.setWhen(System.currentTimeMillis())
        n.setContentTitle("title")
        n.setContentText("text")
        val it = Intent(context, PlantsListActivity::class.java)
        val pi = PendingIntent.getBroadcast(context, 0, it, 0)
        n.setContentIntent(pi)
        NotificationManagerCompat.from(context).notify(uniqueID, n.build())
    }
}