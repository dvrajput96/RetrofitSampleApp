package com.example.retrofitsampleapp.data.repository

import com.example.retrofitsampleapp.data.remote.AppRemoteDataSource
import com.example.retrofitsampleapp.data.remote.model.GetPostResponse
import com.example.retrofitsampleapp.data.utils.BaseApiResponse
import com.example.retrofitsampleapp.data.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: AppRemoteDataSource,
) : BaseApiResponse() {

    suspend fun getPost(): Flow<Resource<GetPostResponse>> {
        return safeApiCall {
            remoteDataSource.getPosts()
        }
    }

}