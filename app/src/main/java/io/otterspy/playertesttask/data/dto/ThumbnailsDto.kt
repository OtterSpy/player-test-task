package io.otterspy.playertesttask.data.dto

import io.otterspy.playertesttask.domain.model.High
import io.otterspy.playertesttask.domain.model.Thumbnails

data class ThumbnailsDto(
    val default: DefaultDto,
    val high: High,
    val maxres: MaxresDto,
    val medium: MediumDto,
    val standard: StandardDto
)
fun ThumbnailsDto.toThumbnails() {
    Thumbnails(
        high
    )
}