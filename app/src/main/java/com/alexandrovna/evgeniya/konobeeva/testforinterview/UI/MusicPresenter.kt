package com.alexandrovna.evgeniya.konobeeva.testforinterview.UI

import android.content.Context
import android.content.Intent
import android.util.Log
import com.alexandrovna.evgeniya.konobeeva.testforinterview.MediaBlock.Commands
import com.alexandrovna.evgeniya.konobeeva.testforinterview.MediaBlock.MusicService

object MusicPresenter {
val TAG = "MUSIC_PATH"


    fun play(context: Context, uri: String) {
        Log.d(TAG, "play: ");
        startService(context, Commands.PLAY, uri)
    }

    fun stop(context: Context) {
        Log.d(TAG, "stop: ");
        startService(context, Commands.STOP)
    }

    fun pause(context: Context) {
        Log.d(TAG, "pause: ");
        startService(context,Commands.PAUSE)
    }

    fun reset(context: Context,uri: String) {
        Log.d(TAG, "reset: ");
        startService(context, Commands.RESET, uri)
    }

    private fun startService(context: Context, commands: Commands, uri: String? = null) {
        Log.d(TAG, "startService: ");
        (context as MainActivity).applicationContext.startService(createIntent(context, commands, uri))
    }

    private fun createIntent(context: Context, commands: Commands, uri: String? = null): Intent{
        return Intent(context, MusicService::class.java).apply {
            putExtra(MusicService.COMMAND, commands.ordinal)
            uri?.let { this.putExtra(MusicService.URI_TO_PLAY, it.toString())}}
    }

}