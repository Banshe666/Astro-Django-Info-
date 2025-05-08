package com.gonzalez.banshe66.astrodjangoinfoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.gonzalez.banshe66.astrodjangoinfoapp.ui.theme.YouTubePlayerComposable
import com.gonzalez.banshe66.astrodjangoinfoapp.ui.theme.AstroDjangoInfoAppTheme
import androidx.compose.material3.MaterialTheme

// Activity that plays a YouTube video in fullscreen using a custom Composable.
class VideoPlayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val videoId = intent.getStringExtra("VIDEO_ID") ?: ""

        setContent {
            AstroDjangoInfoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.ui.graphics.Color.Black
                ) {
                    YouTubePlayerComposable(videoId = videoId)
                }
            }
        }
    }
}
