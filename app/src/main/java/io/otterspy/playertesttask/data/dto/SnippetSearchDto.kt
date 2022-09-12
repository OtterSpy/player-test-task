package io.otterspy.playertesttask.data.dto

data class SnippetSearchDto(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val liveBroadcastContent: String,
    val publishTime: String,
    val publishedAt: String,
    val thumbnails: ThumbnailsSearchDto,
    val title: String
)