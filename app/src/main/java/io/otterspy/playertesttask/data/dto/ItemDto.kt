package io.otterspy.playertesttask.data.dto

import io.otterspy.playertesttask.domain.model.Item
import io.otterspy.playertesttask.domain.model.Snippet

data class ItemDto(
    val contentDetails: ContentDetailsDto,
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet,
    val statistics: StatisticsDto
)