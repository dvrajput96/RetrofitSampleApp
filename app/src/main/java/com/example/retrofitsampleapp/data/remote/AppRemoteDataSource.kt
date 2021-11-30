package com.example.retrofitsampleapp.data.remote

import javax.inject.Inject

class AppRemoteDataSource @Inject constructor(
    private val appService: AppService
) {

    suspend fun getPosts() = appService.getPosts()

}