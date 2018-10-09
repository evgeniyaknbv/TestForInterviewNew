package com.alexandrovna.evgeniya.konobeeva.testforinterview.MediaBlock

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import android.util.Log

class MusicService: Service() {

    companion object {
        val URI_TO_PLAY = "URI_TO_PLAY"
        val COMMAND = "COMMAND"
        private val NOTIFICATION_ID = 994030
    }

    val TAG = "MusicService"
    val mediaController = MediaController()

    private var isReceiverRegistrated = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ");

//        этот код написать в onCreate
        if(!isReceiverRegistrated){
            registerBroadCastReceiver()
//            startForeground(NOTIFICATION_ID, )
            isReceiverRegistrated = true
        }

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
        Log.d(TAG, "onCreate: ");
//        empty
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ");
        unRegisterBroadCastReceiver()
        isReceiverRegistrated = false
        super.onDestroy()
    }

    private fun registerBroadCastReceiver(){
        Log.d(TAG, "registerBroadCastReceiver: ");
    }

    private fun unRegisterBroadCastReceiver(){
        Log.d(TAG, "unRegisterBroadCastReceiver: ");
    }

    private fun getUriFromIntent(intent: Intent?): Uri?{
        var resultURI: Uri? = null
        if(intent?.extras?.containsKey(URI_TO_PLAY) == true){
            val stringUri = intent.extras?.getString(URI_TO_PLAY)
            resultURI = stringUri?.let {  Uri.parse(it)}
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

    private fun executeCommand(command: Commands, uri: Uri?){
        Log.d(TAG, "executeCommand: ");
        when(command){
            Commands.PLAY -> {
                uri?.let { mediaController.play(this.applicationContext, it)}
                /*
                 меняем нотификацию
                 пишем команду, которая выполнилась последней
                  */
            }
            Commands.STOP -> { mediaController.stop()}
            Commands.PAUSE -> { mediaController.pause()}
            Commands.RESET -> { uri?.let { mediaController.play(this.applicationContext, it)}}
        }
    }

    private fun createNotification(command: Commands){

    }
}