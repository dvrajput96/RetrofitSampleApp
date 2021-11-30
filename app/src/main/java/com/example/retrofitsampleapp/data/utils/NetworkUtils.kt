package com.example.retrofitsampleapp.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


object NetworkUtils {

    /**
     * To determine whether our device is connected to internet or not
     */
    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val hasConnectedToMobileData =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR
                ) ?: false

        val hasConnectedToWifi =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                ) ?: false

        return hasConnectedToMobileData || hasConnectedToWifi

    }

}