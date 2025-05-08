package com.gonzalez.banshe66.astrodjangoinfoapp.network

import com.gonzalez.banshe66.astrodjangoinfoapp.model.YouTubeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {
    @GET("playlistItems")
    suspend fun getPlaylistVideos(
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int = 10,
        @Query("playlistId") playlistId: String,
        @Query("key") apiKey: String
    ): YouTubeResponse
}
