package io.otterspy.playertesttask.data.dto

import io.otterspy.playertesttask.domain.model.ItemSearch
import io.otterspy.playertesttask.domain.model.ListSearchVideos

data class ListSearchVideosDto(
    val etag: String,
    val items: List<ItemSearch>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfoSearchDto,
    val regionCode: String
)

fun ListSearchVideosDto.toListSearchVideos() =
    ListSearchVideos(
        items
    )