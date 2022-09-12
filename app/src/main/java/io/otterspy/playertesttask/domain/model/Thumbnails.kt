package io.otterspy.playertesttask.domain.model

import java.io.Serializable

data class Thumbnails(
    val high: High,
    val medium: Medium
) : Serializable
