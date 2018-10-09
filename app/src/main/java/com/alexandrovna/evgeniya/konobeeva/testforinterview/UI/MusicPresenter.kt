package com.alexandrovna.evgeniya.konobeeva.testforinterview.UI

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.alexandrovna.evgeniya.konobeeva.testforinterview.MediaBlock.Commands
import com.alexandrovna.evgeniya.konobeeva.testforinterview.MediaBlock.MusicService

class MusicPresenter {

    fun play(context: Context, uri: Uri) {
        createIntent(context, Commands.PLAY, uri)
    }

    fun stop(context: Context) {
        createIntent(context, Commands.PLAY)
    }

    fun pause(context: Context) {
        createIntent(context,Commands.PLAY)
    }

    fun reset(context: Context,uri: Uri) {
        createIntent(context, Commands.PLAY, uri)
    }

    private fun startService(context: Context, intentCommand: Intent) {
        context.startService(intentCommand)
    }

    private fun createIntent(context: Context, commands: Commands, uri: Uri? = null): Intent{
        return Intent().apply {
            putExtra(MusicService.COMMAND, commands.ordinal)
            uri?.let { putExtra(MusicService.URI_TO_PLAY, it.toString()) }
            startService(context, this)
        }
    }

}