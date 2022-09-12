package io.otterspy.playertesttask.domain.usecase

import io.otterspy.playertesttask.domain.model.ItemSearch
import io.otterspy.playertesttask.domain.repository.YouTubeRepository
import javax.inject.Inject

class GetSearchVideoUseCase @Inject constructor(
    private val repository: YouTubeRepository
) {
    suspend operator fun invoke(
        apiKey: String,
        part: String,
        regionCode: String,
        maxResults: Int,
        searchText: String?
    ): List<ItemSearch> {
        return try {
            repository.getSearchVideoList(apiKey, part, regionCode, maxResults, searchText).items
        } catch (t: Throwable) {
            error(t.message!!)
        }
    }
}