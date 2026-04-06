package com.syed.tpstreamsandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import com.tpstreams.player.TPStreamsSDK
import com.tpstreams.player.TPStreamsPlayer
import com.tpstreams.player.TPStreamsPlayerView
import androidx.media3.common.util.UnstableApi
import androidx.annotation.OptIn

@OptIn(UnstableApi::class)
class PlayerActivity : AppCompatActivity() {
    private var player: TPStreamsPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val assetId = intent.getStringExtra("asset_id") ?: return
        val accessToken = intent.getStringExtra("access_token") ?: return
        val orgCode = intent.getStringExtra("org_code") ?: return

        TPStreamsSDK.init(orgCode)

        player = TPStreamsPlayer.create(this, assetId, accessToken)
        val playerView = findViewById<TPStreamsPlayerView>(R.id.player_view)
        playerView.player = player
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }
}
