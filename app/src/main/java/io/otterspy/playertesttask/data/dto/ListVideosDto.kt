package io.otterspy.playertesttask.data.dto

import io.otterspy.playertesttask.domain.model.Item
import io.otterspy.playertesttask.domain.model.ListVideos

data class ListVideosDto(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfoDto
)

fun ListVideosDto.toListVideos() {
    ListVideos(
        items,
        nextPageToken
    )
}