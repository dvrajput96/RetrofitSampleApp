package com.example.sampleapp.di

import android.content.Context
import com.example.sampleapp.data.local.AppDatabase
import com.example.sampleapp.data.local.dao.PostsDao
import com.example.sampleapp.data.remote.AppRemoteDataSource
import com.example.sampleapp.data.remote.AppService
import com.example.sampleapp.data.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun providePostsDao(db: AppDatabase) = db.postsDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: AppRemoteDataSource,
        localDataSource: PostsDao
    ) = AppRepository(remoteDataSource, localDataSource)

}