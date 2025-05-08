package com.gonzalez.banshe66.astrodjangoinfoapp.network

import com.gonzalez.banshe66.astrodjangoinfoapp.model.YouTubeResponse
import retrofit2.http.GET
import retrofit2.http.Query

// Defines a Retrofit interface for accessing YouTube playlist items
interface YouTubeApi {

    // Suspended function to fetch videos from a specific playlist
    @GET("playlistItems")
    suspend fun getPlaylistVideos(
        @Query("part") part: String = "snippet",        // Specifies which part of the video
        @Query("maxResults") maxResults: Int = 10,      // Maximum number of videos
        @Query("playlistId") playlistId: String,        // The playlist ID to fetch videos from
        @Query("key") apiKey: String                    // YouTube Data API key
    ): YouTubeResponse
}
