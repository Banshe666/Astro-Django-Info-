package com.gonzalez.banshe66.astrodjangoinfoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gonzalez.banshe66.astrodjangoinfoapp.ui.VideoListScreen
import com.gonzalez.banshe66.astrodjangoinfoapp.ui.theme.AstroDjangoInfoAppTheme

// Entry point activity that displays a themed list of YouTube videos
class AstroVideosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Apply dark theme and load the video list screen
            AstroDjangoInfoAppTheme(darkTheme = true) {
                VideoListScreen(
                    playlistId = "PLoqZcxvpWzzeRwF8TEpXHtO7KYY6cNJeF", // Astro playlist ID
                    apiKey = "AIzaSyDF7RMRcrUVeEVMMmn2t4Eue_RnpEZ69hg" // YouTube Data API key
                )
            }
        }
    }
}
