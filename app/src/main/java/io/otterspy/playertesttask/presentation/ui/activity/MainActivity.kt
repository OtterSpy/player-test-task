package io.otterspy.playertesttask.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.otterspy.playertesttask.R
import io.otterspy.playertesttask.presentation.ui.MainApplication

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MainApplication).appComponent.inject(this)
    }
}