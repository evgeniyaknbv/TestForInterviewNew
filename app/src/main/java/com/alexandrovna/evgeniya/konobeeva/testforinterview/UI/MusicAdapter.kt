package com.alexandrovna.evgeniya.konobeeva.testforinterview.UI

import android.net.Uri
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexandrovna.evgeniya.konobeeva.testforinterview.R.layout
import kotlinx.android.synthetic.main.item_audio_play.view.pause_button
import kotlinx.android.synthetic.main.item_audio_play.view.play_button
import kotlinx.android.synthetic.main.item_audio_play.view.reset_button
import kotlinx.android.synthetic.main.item_audio_play.view.sound_name
import kotlinx.android.synthetic.main.item_audio_play.view.stop_button
import java.net.URI

class MusicAdapter(var musicListener: MusicListener?): Adapter<ViewHolder>(){

    private var musicList: MutableList<MusicModel> = mutableListOf()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return MusicViewHolder(LayoutInflater.from(p0.context).inflate(
                layout.item_audio_play, p0, false))
    }

    override fun getItemCount(): Int = musicList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        (p0 as MusicViewHolder).onBind(musicList.get(p1))
    }

    fun addAll( musicList: List<MusicModel> ){
        this.musicList.addAll(musicList)
        notifyDataSetChanged()
    }


    fun unRegisterListenerAndClearList(){
        musicListener = null
        musicList.clear()
    }

    inner class MusicViewHolder(itemView: View): ViewHolder(itemView){
        fun onBind(musicModel: MusicModel){
            itemView.sound_name.text = musicModel.name
            itemView.play_button.setOnClickListener { musicListener?.playPressed(musicModel.uri) }
            itemView.stop_button.setOnClickListener { musicListener?.stopPressed(musicModel.uri) }
            itemView.pause_button.setOnClickListener { musicListener?.pausePressed(musicModel.uri) }
            itemView.reset_button.setOnClickListener { musicListener?.resetPressed(musicModel.uri) }
        }
    }

    interface MusicListener{
        fun playPressed(uri: String)
        fun stopPressed(uri: String)
        fun pausePressed(uri: String)
        fun resetPressed(uri: String)
    }
}