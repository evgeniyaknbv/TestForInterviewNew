package com.alexandrovna.evgeniya.konobeeva.testforinterview.MediaBlock

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import com.alexandrovna.evgeniya.konobeeva.testforinterview.core_app.MusicApplication

class MediaController : MusicControlls {
    val TAG = "MUSIC_PATH"
    val mediaPlayer: MediaPlayer = MediaPlayer()
    var isPaused = false

    init {
        Log.d(TAG, "init: ");
        setListenersToMediaPlayer()
    }

    override fun play(uri: String) {
        Log.d(TAG, "play: isPlaying = ${mediaPlayer.isPlaying}");
        if(isPaused) {
            mediaPlayer.start()
            isPaused = false
        }
        else {
            initializeAndPrepare(uri)
        }

    }

    override fun stop() {
        Log.d(TAG, "stop: isPlaying = ${mediaPlayer.isPlaying}");
        if(mediaPlayer.isPlaying || isPaused){
            mediaPlayer.stop()
            isPaused = false
        }
    }

    override fun pause() {
        Log.d(TAG, "pause: isPlaying = ${mediaPlayer.isPlaying}");
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
            isPaused = true
        }

    }

    override fun reset( uri: String) {
        Log.d(TAG, "reset: isPlaying = ${mediaPlayer.isPlaying}");
        mediaPlayer.reset()
        initializeAndPrepare(uri)
    }

    private fun initializeAndPrepare(uri: String){
        Log.d(TAG, "initializeAndPrepare: uri = ${uri}");
        mediaPlayer.reset()
        mediaPlayer.setDataSource(uri)
//        mediaPlayer.setDataSource(context, uri)
        mediaPlayer.prepareAsync()
    }

    private fun setListenersToMediaPlayer(){
        with(mediaPlayer){

            setOnErrorListener { p0, p1, p2 ->
                Log.d(TAG, "OnError: what= $p1");
                Log.d(TAG, "OnError: extra= $p2");
                true }

            setOnCompletionListener { mp ->
                isPaused = false
                Log.d(TAG, "OnCompletion: "); }

            setOnPreparedListener { mp ->
                Log.d(TAG, "OnPrepare: ");
                mp.start()
            }
        }

    }
}