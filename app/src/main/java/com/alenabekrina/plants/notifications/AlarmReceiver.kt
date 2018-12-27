package com.alenabekrina.plants.notifications

import android.app.PendingIntent
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.alenabekrina.plants.App
import com.alenabekrina.plants.R
import com.alenabekrina.plants.view.PlantsListActivity
import com.alenabekrina.plants.viewmodel.PlantsViewModel
import javax.inject.Inject


class AlarmReceiver: BroadcastReceiver() {
    @Inject
    lateinit var notificationManager: NotificationsUtils

    val uniqueID = 3657554

    override fun onReceive(context: Context, intent: Intent) {
        val app = context.applicationContext as App
        app.component.injectAlarmReceiver(this)

        val n = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(context, notificationManager.ANDROID_CHANNEL_ID)
        } else {
            NotificationCompat.Builder(context)
        }

        val plantName: String = intent.extras["PlantName"] as String


        n.setSmallIcon(R.mipmap.ic_launcher)
        n.setTicker("ticker")
        n.setWhen(System.currentTimeMillis())
        n.setContentTitle("Watering")
        n.setContentText(plantName)
        val it = Intent(context, PlantsListActivity::class.java)
        val pi = PendingIntent.getActivity(context, 0, it, 0)
        n.setContentIntent(pi)
        n.setAutoCancel(true)
        NotificationManagerCompat.from(context).notify(uniqueID, n.build())
    }
}