package com.gonzalez.banshe66.astrodjangoinfoapp.model

data class YouTubeResponse(
    val items: List<VideoItem>
)

data class VideoItem(
    val snippet: Snippet
)

data class Snippet(
    val title: String,
    val thumbnails: Thumbnails,
    val resourceId: ResourceId
)

data class Thumbnails(
    val medium: Thumbnail
)

data class Thumbnail(
    val url: String
)

data class ResourceId(
    val videoId: String
)
