package com.example.retrofitsampleapp.di

import com.example.retrofitsampleapp.data.remote.AppRemoteDataSource
import com.example.retrofitsampleapp.data.remote.AppService
import com.example.retrofitsampleapp.data.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * For more detail regarding Hilt: https://developer.android.com/training/dependency-injection/hilt-android
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppRemoteDataSource(appService: AppService) = AppRemoteDataSource(appService)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: AppRemoteDataSource
    ) = AppRepository(remoteDataSource)

}