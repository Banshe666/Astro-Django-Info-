package com.gonzalez.banshe66.astrodjangoinfoapp.model

// Root response from the YouTube API containing a list of video items
data class YouTubeResponse(
    val items: List<VideoItem> // List of individual video entries
)

// Represents a single video item from the response
data class VideoItem(
    val snippet: Snippet // Contains video details
)

// Holds detailed information about the video
data class Snippet(
    val title: String, // Video title
    val thumbnails: Thumbnails, // Thumbnail images
    val resourceId: ResourceId // Unique video identifier
)

// Container for different thumbnail resolutions
data class Thumbnails(
    val medium: Thumbnail // Medium-sized thumbnail
)

// Holds the URL of a single thumbnail
data class Thumbnail(
    val url: String // Thumbnail image URL
)

// Contains the video ID for playback
data class ResourceId(
    val videoId: String // YouTube video ID
)
