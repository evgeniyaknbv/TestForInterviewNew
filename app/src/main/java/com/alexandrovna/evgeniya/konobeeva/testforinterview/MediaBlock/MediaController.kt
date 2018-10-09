package com.alexandrovna.evgeniya.konobeeva.testforinterview.MediaBlock

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log

class MediaController : MusicControlls {
    val TAG = "MediaController"
    val mediaPlayer: MediaPlayer = MediaPlayer()
    var isPaused = false

    init {
        setListenersToMediaPlayer()
    }

    override fun play(context: Context, uri: Uri) {
        Log.d(TAG, "play: isPlaying = ${mediaPlayer.isPlaying}");
        if(isPaused) {
            mediaPlayer.start()
            isPaused = false
        }
        else {
            initializeAndPrepare(context, uri)
        }

    }

    override fun stop() {
        Log.d(TAG, "stop: isPlaying = ${mediaPlayer.isPlaying}");
        if(mediaPlayer.isPlaying || isPaused){
            mediaPlayer.stop()
        }
    }

    override fun pause() {
        Log.d(TAG, "pause: isPlaying = ${mediaPlayer.isPlaying}");
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
            isPaused = true
        }

    }

    override fun reset(context: Context, uri: Uri) {
        Log.d(TAG, "reset: isPlaying = ${mediaPlayer.isPlaying}");
        if(mediaPlayer.isPlaying){
            mediaPlayer.reset()
            initializeAndPrepare(context, uri)
        }
    }

    private fun initializeAndPrepare(context: Context, uri: Uri){
        mediaPlayer.setDataSource(context, uri)
        mediaPlayer.prepareAsync()
    }

    private fun setListenersToMediaPlayer(){
        with(mediaPlayer){

            setOnErrorListener { p0, p1, p2 ->
                Log.d(TAG, "OnError: what= $p1");
                Log.d(TAG, "OnError: extra= $p2");
                true }

            setOnCompletionListener { mp -> Log.d(TAG, "OnCompletion: "); }

            setOnPreparedListener { mp ->
                Log.d(TAG, "OnPrepare: ");
                mp.start()
            }
        }

    }
}