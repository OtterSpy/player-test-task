package io.otterspy.playertesttask.domain.model

import java.io.Serializable

data class Item(
    val id: String,
    val snippet: Snippet
) : Serializable

