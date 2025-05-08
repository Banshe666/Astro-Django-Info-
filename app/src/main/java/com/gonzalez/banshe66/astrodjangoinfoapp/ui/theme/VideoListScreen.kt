package com.gonzalez.banshe66.astrodjangoinfoapp.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.gonzalez.banshe66.astrodjangoinfoapp.VideoPlayerActivity
import com.gonzalez.banshe66.astrodjangoinfoapp.model.VideoItem
import com.gonzalez.banshe66.astrodjangoinfoapp.network.RetrofitClient
import androidx.compose.material3.Surface


@Composable
fun VideoListScreen(playlistId: String, apiKey: String) {
    var videos by remember { mutableStateOf<List<VideoItem>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.api.getPlaylistVideos(
                playlistId = playlistId,
                apiKey = apiKey
            )
            videos = response.items
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(videos) { video ->
                VideoCard(video) {
                    val videoId = video.snippet.resourceId.videoId
                    val intent = Intent(context, VideoPlayerActivity::class.java)
                    intent.putExtra("VIDEO_ID", videoId)
                    context.startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun VideoCard(video: VideoItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(video.snippet.thumbnails.medium.url),
                contentDescription = video.snippet.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = video.snippet.title,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
