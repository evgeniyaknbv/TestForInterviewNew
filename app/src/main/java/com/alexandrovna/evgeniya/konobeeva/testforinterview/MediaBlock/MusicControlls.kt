package com.alexandrovna.evgeniya.konobeeva.testforinterview.MediaBlock

import android.content.Context
import android.net.Uri

interface MusicControlls {
    fun play( uri: String)
    fun stop()
    fun pause()
    fun reset(uri: String)
}