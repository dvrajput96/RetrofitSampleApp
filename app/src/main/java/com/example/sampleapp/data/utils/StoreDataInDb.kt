package com.example.sampleapp.data.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

/**
 * Base api response class
 */
abstract class StoreDataInDb {
    suspend fun <T> storeDataInDb(
        dispatcher: CoroutineDispatcher = Dispatchers.IO, // By default it's IO Dispatcher
        storeData: suspend () -> Response<T>
    ): Flow<Resource<T>> = flow {
        emit(Resource.loading(null))
        try {
            val response = storeData()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    emit(Resource.success(body))
                    return@flow
                }
            }
            emit(error("${response.code()} ${response.message()}"))
            return@flow
        } catch (e: NoConnectivityException) { // No internet connection exception
            emit(error(e.message))
            return@flow
        } catch (e: Exception) { // Other exceptions
            emit(error(e.message ?: e.toString()))
            return@flow
        }
    }.flowOn(dispatcher)

    private fun <T> error(errorMessage: String): Resource<T> =
        Resource.error("Error : $errorMessage")
}