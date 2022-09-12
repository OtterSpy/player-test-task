package io.otterspy.playertesttask.di

import dagger.Binds
import dagger.Module
import io.otterspy.playertesttask.data.repository.YouTubeRepositoryImpl
import io.otterspy.playertesttask.domain.repository.YouTubeRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindMovieRepository(youTubeRepositoryImpl: YouTubeRepositoryImpl): YouTubeRepository
}