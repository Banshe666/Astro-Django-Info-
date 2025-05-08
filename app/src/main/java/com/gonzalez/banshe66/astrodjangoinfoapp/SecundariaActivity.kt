package com.gonzalez.banshe66.astrodjangoinfoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gonzalez.banshe66.astrodjangoinfoapp.ui.VideoListScreen
import com.gonzalez.banshe66.astrodjangoinfoapp.ui.theme.AstroDjangoInfoAppTheme

class SecondaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AstroDjangoInfoAppTheme(darkTheme = true) {
                VideoListScreen(
                    playlistId = "PL4cUxeGkcC9iqfAag3a_BKEX1N43uJutw",
                    apiKey = "AIzaSyDF7RMRcrUVeEVMMmn2t4Eue_RnpEZ69hg"
                )
            }
        }
    }
}
