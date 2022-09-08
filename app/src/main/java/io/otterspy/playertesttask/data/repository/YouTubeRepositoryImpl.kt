package io.otterspy.playertesttask.data.repository

import io.otterspy.playertesttask.data.YouTubeApi
import io.otterspy.playertesttask.data.dto.toListVideos
import io.otterspy.playertesttask.domain.model.ListVideos
import io.otterspy.playertesttask.domain.repository.YouTubeRepository
import javax.inject.Inject

class YouTubeRepositoryImpl @Inject constructor(
    private val youTubeApi: YouTubeApi
) : YouTubeRepository {
    override suspend fun getMostPopularMusicVideos(
        apiKey: String,
        part: String,
        chart: String,
        regionCode: String,
        maxResults: Int,
        videoCategoryId: Int?
    ): ListVideos =
        youTubeApi.getVideosList(apiKey, part, chart, regionCode, maxResults, videoCategoryId)
            .toListVideos()
}