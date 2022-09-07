package io.otterspy.playertesttask.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import io.otterspy.playertesttask.presentation.ui.fragments.videolistsfragment.VideoListsViewModel

@Module
interface ViewModelsModule {
    @Binds
    @[IntoMap ClassKey(VideoListsViewModel::class)]
    fun provideVideoListsViewModel(videoListsViewModel: VideoListsViewModel): ViewModel
}