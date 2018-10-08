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

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        initRecyclerView()
    }


    private fun initRecyclerView(){
        val adapter = MusicAdapter(getMusicList(),
                object : MusicListener {
                    override fun playPressed(uri: Uri) {
                    }

                    override fun stopPressed(uri: Uri) {
                    }

                    override fun pausePressed(uri: Uri) {
                    }

                    override fun resetPressed(uri: Uri) {
                    }
                })
        activity_main_recycler.adapter = adapter
        activity_main_recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun getMusicList(): List<MusicModel>{
        return listOf(MusicModel("name", Uri.parse("")),
                MusicModel("name0", Uri.parse("")),
                MusicModel("name1", Uri.parse("")),
                MusicModel("name3", Uri.parse("")),
                MusicModel("name4", Uri.parse("")),
                MusicModel("name5", Uri.parse("")),
                MusicModel("name6", Uri.parse("")))
    }


}
