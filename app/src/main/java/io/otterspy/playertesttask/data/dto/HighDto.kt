package io.otterspy.playertesttask.data.dto

import io.otterspy.playertesttask.domain.model.High

data class HighDto(
    val height: Int,
    val url: String,
    val width: Int
)

fun HighDto.toHigh() {
    High(
        url
    )
}