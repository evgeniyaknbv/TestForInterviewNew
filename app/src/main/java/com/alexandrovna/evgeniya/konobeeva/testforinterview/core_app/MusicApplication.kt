package com.alexandrovna.evgeniya.konobeeva.testforinterview.core_app

import android.app.Application
import android.app.NotificationChannel
import android.content.Context.NOTIFICATION_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import com.alexandrovna.evgeniya.konobeeva.testforinterview.R


class MusicApplication: Application() {

    val TAG = "MusicApplication"

    companion object {
        val NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID"
    }
    override fun onCreate() {
        super.onCreate()
        createNotificationManager()
    }

    private fun createNotificationManager(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannel = notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID)
            if(notificationChannel == null){
                Log.d(TAG, "createNotificationManager: notificationChannel == null");
                val name = "music_channel_name"
                val descriptionText = "music_channel_description"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance)
                mChannel.description = descriptionText
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mChannel)
            }
            Log.d(TAG, "createNotificationManager: notificationChannel NOT null");
        }

    }
}