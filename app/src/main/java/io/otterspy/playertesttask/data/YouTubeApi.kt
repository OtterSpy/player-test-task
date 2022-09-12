package io.otterspy.playertesttask.data

import io.otterspy.playertesttask.data.dto.ListSearchVideosDto
import io.otterspy.playertesttask.data.dto.ListVideosDto
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {
    @GET("videos")
    suspend fun getVideosList(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("chart") chart: String,
        @Query("regionCode") regionCode: String,
        @Query("maxResults") maxResults: Int,
        @Query("videoCategoryId") videoCategoryId: Int?
    ): ListVideosDto

    @GET("search")
    suspend fun getSearchList(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("regionCode") regionCode: String,
        @Query("maxResults") maxResults: Int,
        @Query("q") searchText: String?
    ): ListSearchVideosDto
}