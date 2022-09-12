package io.otterspy.playertesttask.domain.model

import java.io.Serializable

data class Snippet(
    val channelId: String,
    val channelTitle: String,
    val thumbnails: Thumbnails,
    val title: String
) : Serializable
