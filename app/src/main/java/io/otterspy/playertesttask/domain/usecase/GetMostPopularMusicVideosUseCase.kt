package io.otterspy.playertesttask.domain.usecase

import io.otterspy.playertesttask.domain.model.Item
import io.otterspy.playertesttask.domain.repository.YouTubeRepository
import javax.inject.Inject

class GetMostPopularMusicVideosUseCase @Inject constructor(
    private val repository: YouTubeRepository
) {
    suspend operator fun invoke(
        apiKey: String,
        part: String,
        chart: String,
        regionCode: String,
        maxResults: Int,
        videoCategoryId: Int
    ): List<Item> {
        return try {
            repository.getMostPopularMusicVideos(apiKey, part, chart, regionCode, maxResults, videoCategoryId).items
        } catch (t: Throwable) {
            error(t.message!!)
        }
    }
}