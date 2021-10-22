package com.example.sampleapp.data.repository

import com.example.sampleapp.data.remote.AppRemoteDataSource
import com.example.sampleapp.data.remote.model.GetPostResponse
import com.example.sampleapp.data.utils.BaseApiResponse
import com.example.sampleapp.data.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: AppRemoteDataSource
) : BaseApiResponse() {

    suspend fun getPost(): Flow<Resource<GetPostResponse>> {
        return safeApiCall { remoteDataSource.getPosts() }
    }

}