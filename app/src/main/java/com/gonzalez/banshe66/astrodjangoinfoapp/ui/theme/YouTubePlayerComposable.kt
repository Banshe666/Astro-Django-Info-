package com.gonzalez.banshe66.astrodjangoinfoapp.ui.theme

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

// A Jetpack Compose wrapper for embedding a YouTube video using the Android YouTube Player library.
// Loads and plays the specified video ID within the Composable layout.
@Composable
fun YouTubePlayerComposable(videoId: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val playerView = YouTubePlayerView(context).apply {
                setBackgroundColor(android.graphics.Color.BLACK)
                this.setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, null)
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                // Listener to load and play the video once the player is ready
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }

            playerView
        }
    )
}
