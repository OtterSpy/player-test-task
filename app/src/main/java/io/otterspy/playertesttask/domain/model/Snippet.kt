package io.otterspy.playertesttask.domain.model

data class Snippet(
    val channelId: String,
    val channelTitle: String,
    val thumbnails: Thumbnails,
    val title: String
)
