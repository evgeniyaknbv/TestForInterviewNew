package com.alexandrovna.evgeniya.konobeeva.testforinterview.UI

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.alexandrovna.evgeniya.konobeeva.testforinterview.R.layout
import com.alexandrovna.evgeniya.konobeeva.testforinterview.UI.MusicAdapter.MusicListener
import kotlinx.android.synthetic.main.activity_main.activity_main_recycler
import java.net.URI
import android.content.ContentUris
import android.database.Cursor
import android.provider.MediaStore
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.widget.Toast
import java.io.File


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    private var adapter: MusicAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ");
        setContentView(layout.activity_main)
        initRecyclerView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy()
        adapter?.unRegisterListenerAndClearList()
    }

    private fun initRecyclerView(){
        adapter = MusicAdapter(
                object : MusicListener {
                    override fun playPressed(uri: String) {
                        MusicPresenter.play(this@MainActivity, uri)
                    }

                    override fun stopPressed(uri: String) {
                        MusicPresenter.stop(this@MainActivity)
                    }

                    override fun pausePressed(uri: String) {
                        MusicPresenter.pause(this@MainActivity)
                    }

                    override fun resetPressed(uri: String) {
                        MusicPresenter.reset(this@MainActivity, uri)
                    }
                })
        activity_main_recycler.adapter = adapter
        activity_main_recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        getMusicList(adapter)
    }

    private fun getMusicList(adapter: MusicAdapter?){

        Log.d(TAG, "getMusicList: ");

        val songs = ArrayList<MusicModel>()

        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"

        val projection = arrayOf(MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION)

        val cursorLoader = CursorLoader(applicationContext,
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
                projection,
                selection,
                null, null)

        val cursor1 = cursorLoader.loadInBackground()
        cursor1?.let {
            while (it.moveToNext()) {
                val uri = it.getString(3)
                val name = it.getString(2)
                if(!uri.isNullOrEmpty()){
                    songs.add(MusicModel(name, uri))
                }
            }
            it.close()
        }

        cursorLoader.stopLoading()

        if(songs.isNotEmpty()){
            adapter?.addAll(songs)
        }else{
            Toast.makeText(this, "songs is empty", Toast.LENGTH_SHORT).show()
        }
    }


}
