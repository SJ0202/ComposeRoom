package com.seongju.composeroom

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComposeRoomApp:Application() {

    override fun onCreate() {
        super.onCreate()
    }

}