package io.otterspy.playertesttask.domain.model

data class ListVideos(
    val items: List<Item>,
    val nextPageToken: String
)
