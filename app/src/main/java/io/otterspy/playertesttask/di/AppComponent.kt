package io.otterspy.playertesttask.di

import dagger.Component
import io.otterspy.playertesttask.presentation.ui.activity.MainActivity
import io.otterspy.playertesttask.utils.ViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, ViewModelsModule::class])
interface AppComponent {

    val factory: ViewModelFactory

    fun inject(activity: MainActivity)
}