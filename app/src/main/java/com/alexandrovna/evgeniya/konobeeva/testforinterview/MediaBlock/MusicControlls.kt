package com.alexandrovna.evgeniya.konobeeva.testforinterview.MediaBlock

import android.net.Uri

interface MusicControlls {
    fun play(uri: Uri)
    fun stop()
    fun pause()
    fun reset()
}