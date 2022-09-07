package io.otterspy.playertesttask.data.dto

import io.otterspy.playertesttask.domain.model.Snippet
import io.otterspy.playertesttask.domain.model.Thumbnails

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
    val thumbnails: Thumbnails,
    val title: String
)

fun SnippetDto.toSnippet() {
    Snippet(
        channelTitle,
        thumbnails,
        title
    )
}