package io.otterspy.playertesttask.domain.model

import java.io.Serializable

data class ItemSearch(
    val id: ItemSearchId,
    val snippet: Snippet
) : Serializable