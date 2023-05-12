package com.example.movieplayerapp

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class WatchActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch)
        val playerView = findViewById<PlayerView>(R.id.player)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val bt_fullscreen = findViewById<ImageView>(R.id.bt_fullscreen)
        val bt_lockscreen = findViewById<ImageView>(R.id.exo_lock)

        val simpleExoPlayer = SimpleExoPlayer.Builder(this)
            .setSeekBackIncrementMs(5000)
            .setSeekForwardIncrementMs(5000)
            .build()
        playerView.player = simpleExoPlayer
        playerView.keepScreenOn = true
        simpleExoPlayer.addListener(object: Player.Listener{
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == Player.STATE_BUFFERING) {
                    progressBar.visibility = View.VISIBLE
                }
                else if (playbackState == Player.STATE_READY) {
                    progressBar.visibility = View.GONE
                }
            }
        })
        val videoSource = Uri.parse("https://youtu.be/BS7kfGD1KYU")
        val mediaItem = MediaItem.fromUri(videoSource)
        simpleExoPlayer.setMediaItem(mediaItem)
        simpleExoPlayer.prepare()
        simpleExoPlayer.play()

    }
}