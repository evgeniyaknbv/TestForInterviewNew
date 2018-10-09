package com.alexandrovna.evgeniya.konobeeva.testforinterview.MediaBlock

import android.content.Context
import android.net.Uri

interface MusicControlls {
    fun play(context: Context, uri: Uri)
    fun stop()
    fun pause()
    fun reset(context: Context, uri: Uri)
}