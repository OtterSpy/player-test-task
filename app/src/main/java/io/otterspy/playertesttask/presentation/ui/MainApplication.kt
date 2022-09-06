package io.otterspy.playertesttask.presentation.ui

import android.app.Application
import io.otterspy.playertesttask.di.AppComponent
import io.otterspy.playertesttask.di.DaggerAppComponent

class MainApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }

//    companion object {
//        fun Activity.getAppComponent() = (application as MainApplication).appComponent
//    }
}