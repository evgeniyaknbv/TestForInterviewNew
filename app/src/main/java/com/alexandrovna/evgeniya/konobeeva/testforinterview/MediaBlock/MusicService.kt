package com.alexandrovna.evgeniya.konobeeva.testforinterview.MediaBlock

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.alexandrovna.evgeniya.konobeeva.testforinterview.core_app.MusicApplication
import android.app.NotificationManager
import android.content.Context


class MusicService: Service() {

    companion object {
        val URI_TO_PLAY = "URI_TO_PLAY"
        val COMMAND = "COMMAND"
        val NOTIFICATION_ID = 994030
    }

    val TAG = "MUSIC_PATH"
    private val mediaController = MediaController()
    private var isReceiverRegistrated = false
    lateinit var notificationBuilder: NotificationCompat.Builder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ");

        getCommandFromIntent(intent)?.let {
            executeCommand(it, getUriFromIntent(intent))
        } ?: stopSelf()

        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
//        empty
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ");
        if(!isReceiverRegistrated){
            registerBroadCastReceiver()
            isReceiverRegistrated = true
        }
        notificationBuilder = getNotification("start", "start")
        startForeground(NOTIFICATION_ID, notificationBuilder.build())

    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ");
        unRegisterBroadCastReceiver()
//        stopForeground(true)
        isReceiverRegistrated = false
        super.onDestroy()
    }



    private fun registerBroadCastReceiver(){
        Log.d(TAG, "registerBroadCastReceiver: ");
    }

    private fun unRegisterBroadCastReceiver(){
        Log.d(TAG, "unRegisterBroadCastReceiver: ");
    }

    private fun getUriFromIntent(intent: Intent?): String?{
        var resultURI: String? = null
        if(intent?.extras?.containsKey(URI_TO_PLAY) == true){
            val stringUri = intent.extras?.getString(URI_TO_PLAY)
            resultURI = stringUri
        }

        return resultURI
    }

    private fun getCommandFromIntent(intent: Intent?): Commands? {
        var resultCommand: Commands? = null
        if(intent?.extras?.containsKey(COMMAND) == true){
            val commandId = intent.extras?.getInt(COMMAND)
            commandId?.let { resultCommand = Commands.values()[commandId] }
        }
        return resultCommand
    }

    private fun executeCommand(command: Commands, uri: String?){
        Log.d(TAG, "executeCommand: ${command.name}");
        when(command){
            Commands.PLAY -> { uri?.let { mediaController.play(it)} }
            Commands.STOP -> { mediaController.stop()}
            Commands.PAUSE -> { mediaController.pause()}
            Commands.RESET -> { uri?.let { mediaController.reset(it)}}
        }
        createNotification(command)
    }

    private fun createNotification(command: Commands){
        Log.d(TAG, "createNotification: ");
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationBuilder.setContentTitle(command.name).setContentText("my music")
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    fun getNotification(title: String, body: String): NotificationCompat.Builder{
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationCompat.Builder(applicationContext, MusicApplication.NOTIFICATION_CHANNEL_ID)
        }else{
            NotificationCompat.Builder(applicationContext)
        }.setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true)

    }
}