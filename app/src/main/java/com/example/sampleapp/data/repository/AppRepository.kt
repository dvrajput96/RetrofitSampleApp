package com.example.sampleapp.data.repository

import androidx.lifecycle.LiveData
import com.example.sampleapp.data.local.dao.PostsDao
import com.example.sampleapp.data.local.entities.Posts
import com.example.sampleapp.data.remote.AppRemoteDataSource
import com.example.sampleapp.data.remote.model.GetPostResponse
import com.example.sampleapp.data.utils.BaseApiResponse
import com.example.sampleapp.data.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: AppRemoteDataSource,
    private val localDataSource: PostsDao,
) : BaseApiResponse() {

    suspend fun getPost(): Flow<Resource<GetPostResponse>> {
        return safeApiCall {
            remoteDataSource.getPosts()
        }.also {
            it.collect { response ->
                if (response.status == Resource.Status.SUCCESS) {
                    Timber.d(">>>>>>>> Response %s", response.data)
                    val posts: List<Posts>? = response.data?.map { postData ->
                        postData.run {
                            Posts(
                                id = id ?: 0,
                                body = body,
                                title = title,
                                userId = userId
                            )
                        }
                    }
                    posts?.let { data ->
                        data.forEachIndexed { _, posts ->
                            localDataSource.insert(posts)
                        }
                        //localDataSource.insertAll(data)
                    }
                }
            }
        }
    }

    suspend fun getPostsFromLocal(): LiveData<List<Posts>> {
        return localDataSource.getPosts()
    }


}