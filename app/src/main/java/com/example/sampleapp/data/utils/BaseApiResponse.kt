package com.example.sampleapp.data.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

/**
 * Base api response class
 */
abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        emit(Resource.loading(null))
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    emit(Resource.success(body))
                    return@flow
                }
            }
            emit(error("${response.code()} ${response.message()}"))
            return@flow
        } catch (e: NoConnectivityException) { // No internet connection
            emit(error(e.message))
            return@flow
        } catch (e: Exception) {
            emit(error(e.message ?: e.toString()))
            return@flow
        }
    }.flowOn(Dispatchers.IO) // This flow will be executed on io thread.

    private fun <T> error(errorMessage: String): Resource<T> =
        Resource.error("Error : $errorMessage")
}