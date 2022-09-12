package io.otterspy.playertesttask.data.dto

data class ContentDetailsDto(
    val caption: String,
    val contentRating: ContentRatingDto,
    val definition: String,
    val dimension: String,
    val duration: String,
    val licensedContent: Boolean,
    val projection: String,
    val regionRestriction: RegionRestrictionDto
)