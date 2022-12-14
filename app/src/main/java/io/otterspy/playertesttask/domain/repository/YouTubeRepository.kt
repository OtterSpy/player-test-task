package io.otterspy.playertesttask.domain.repository

import io.otterspy.playertesttask.domain.model.ListSearchVideos
import io.otterspy.playertesttask.domain.model.ListVideos

interface YouTubeRepository {
    suspend fun getMostPopularMusicVideos(
        apiKey: String,
        part: String,
        chart: String,
        regionCode: String,
        maxResults: Int,
        videoCategoryId: Int?
    ): ListVideos

    suspend fun getSearchVideoList(
        apiKey: String,
        part: String,
        regionCode: String,
        maxResults: Int,
        searchText: String?
    ): ListSearchVideos
}