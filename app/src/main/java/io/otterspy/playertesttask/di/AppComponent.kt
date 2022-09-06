package io.otterspy.playertesttask.di

import dagger.Component
import io.otterspy.playertesttask.presentation.ui.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}