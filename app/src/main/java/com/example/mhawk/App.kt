package com.example.mhawk

import android.app.Application

class App : Application() {

    /* */
    companion object {
        var instance: App ? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}