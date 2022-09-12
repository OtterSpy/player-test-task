package io.otterspy.playertesttask.domain.model

import java.io.Serializable

data class ListVideos(
    val items: List<Item>,
    val nextPageToken: String
) : Serializable

