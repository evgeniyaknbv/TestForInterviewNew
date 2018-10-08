package com.alexandrovna.evgeniya.konobeeva.testforinterview.MediaBlock

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.IBinder.DeathRecipient
import android.os.IInterface
import android.os.Parcel
import android.util.Log
import java.io.FileDescriptor
import java.net.URI

class MusicService: Service() {

    companion object {
        val URI_TO_PLAY = "URI_TO_PLAY"
    }

    val TAG = "MusicService"

    private var isStarted = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ");

        if(!isStarted){
            registerBroadCastReceiver()
        }


        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ");
        unRegisterBroadCastReceiver()
        super.onDestroy()
    }

    private fun registerBroadCastReceiver(){

    }

    private fun unRegisterBroadCastReceiver(){

    }

    private fun getUriFromIntent(intent: Intent?): URI?{
        var resultURI: URI? = null
        if(intent?.extras?.containsKey(URI_TO_PLAY) == true){
            val stringUri = intent.extras?.getString(URI_TO_PLAY)
            resultURI = stringUri?.let {  URI.create(it)}
        }

        return resultURI
    }
}