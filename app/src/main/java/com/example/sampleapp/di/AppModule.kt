package com.example.sampleapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * For more detail regarding Hilt: https://developer.android.com/training/dependency-injection/hilt-android
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule