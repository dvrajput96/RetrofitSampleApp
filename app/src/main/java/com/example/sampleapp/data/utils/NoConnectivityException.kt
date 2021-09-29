package com.example.sampleapp.data.utils

import android.content.Context
import com.example.sampleapp.R
import java.io.IOException

class NoConnectivityException(private val context: Context) : IOException() {

    override val message: String
        get() = context.getString(R.string.please_check_your_internet_connection)

}