package com.example.sampleapp.data.remote

import com.example.sampleapp.data.remote.model.GetPostResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit api services
 */
interface AppService {

    @GET("posts")
    suspend fun getPosts(): Response<GetPostResponse>

}