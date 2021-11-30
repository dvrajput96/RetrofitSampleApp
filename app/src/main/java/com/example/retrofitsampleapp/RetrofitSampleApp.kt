package com.example.retrofitsampleapp

import android.app.Application
import com.example.retrofitsampleapp.utils.TimberDebugTree
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class RetrofitSampleApp : Application() {

    companion object {
        lateinit var instance: RetrofitSampleApp
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