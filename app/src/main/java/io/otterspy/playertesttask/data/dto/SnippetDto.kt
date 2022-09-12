package io.otterspy.playertesttask.data.dto

data class SnippetDto(
    val categoryId: String,
    val channelId: String,
    val channelTitle: String,
    val defaultAudioLanguage: String,
    val defaultLanguage: String,
    val description: String,
    val liveBroadcastContent: String,
    val localized: LocalizedDto,
    val publishedAt: String,
    val tags: List<String>,
    val thumbnails: ThumbnailsDto,
    val title: String
)