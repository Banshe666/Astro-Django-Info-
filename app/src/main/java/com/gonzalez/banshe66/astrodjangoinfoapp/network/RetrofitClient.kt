package com.gonzalez.banshe66.astrodjangoinfoapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton object that provides a Retrofit instance configured for the YouTube Data API
object RetrofitClient {

    // Base URL for YouTube Data API v3
    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

    // Lazily initialized API interface using Retrofit with Gson converter
    val api: YouTubeApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Sets the API base URL
            .addConverterFactory(GsonConverterFactory.create()) // Converts JSON to Kotlin objects
            .build()
            .create(YouTubeApi::class.java) // Creates the implementation of the API interface
    }
}
