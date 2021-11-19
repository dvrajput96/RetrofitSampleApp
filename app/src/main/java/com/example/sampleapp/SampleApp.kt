package com.example.sampleapp

import android.app.Application
import com.example.sampleapp.utils.TimberDebugTree
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SampleApp : Application() {

    companion object {
        lateinit var instance: SampleApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(TimberDebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }
}