package io.otterspy.playertesttask.domain.model

import java.io.Serializable

data class ListSearchVideos(
    val items: List<ItemSearch>
) : Serializable