package io.otterspy.playertesttask.data.dto

data class ItemSearchDto(
    val etag: String,
    val id: SearchIdDto,
    val kind: String,
    val snippet: SnippetSearchDto
)