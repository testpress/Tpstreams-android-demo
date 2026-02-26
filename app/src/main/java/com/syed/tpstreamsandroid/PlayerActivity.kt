package com.syed.tpstreamsandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tpstream.player.ui.TpStreamPlayerFragment
import com.tpstream.player.ui.InitializationListener
import com.tpstream.player.TpStreamPlayer
import com.tpstream.player.TpInitParams
import com.tpstream.player.TPStreamsSDK
import com.syed.tpstreamsandroid.R

import androidx.activity.enableEdgeToEdge

class PlayerActivity : AppCompatActivity() {
    private lateinit var playerFragment: TpStreamPlayerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val assetId = intent.getStringExtra("asset_id") ?: return
        val accessToken = intent.getStringExtra("access_token") ?: return
        val orgCode = intent.getStringExtra("org_code") ?: return

        TPStreamsSDK.initialize(TPStreamsSDK.Provider.TPStreams, orgCode)

        playerFragment = supportFragmentManager.findFragmentById(R.id.tpstream_player_fragment) as TpStreamPlayerFragment

        playerFragment.setOnInitializationListener(object : InitializationListener {
            override fun onInitializationSuccess(player: TpStreamPlayer) {
                val parameters = TpInitParams.Builder()
                    .setVideoId(assetId)
                    .setAccessToken(accessToken)
                    .build()
                player.load(parameters)
            }
        })
    }
}
