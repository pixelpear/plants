package com.alenabekrina.plants.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.alenabekrina.plants.R
import android.support.v4.app.NotificationManagerCompat



class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)

        val notification = NotificationCompat.Builder(context)
            .setContentTitle("Random title")
            .setContentText("Random text")
            .setSmallIcon(R.drawable.ic_water_blue)
            .setContentIntent(PendingIntent.getActivity(context, 0, Intent(context, MyActivity.class), 0))
            .build();


        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(context, NotificationChannel.DEFAULT_CHANNEL_ID)
        } else {
            Notification.Builder(context)
        }
        builder.setContentTitle("My Title")
        builder.setContentText("This is the Body")
        builder.setSmallIcon(R.drawable.ic_water_blue)

        val notificationCompat = builder.build()
        val managerCompat = NotificationManagerCompat.from(this)
        managerCompat.notify(NOTIFICATION_ID, notificationCompat)

        notificationManager.notify(0, notification);
    }
}