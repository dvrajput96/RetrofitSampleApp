package com.example.sampleapp.data.utils

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Connectivity interceptor for retrofit
 */
class ConnectivityInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtils.isNetworkConnected(context)) {
            throw NoConnectivityException(context)
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}