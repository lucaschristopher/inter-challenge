package com.example.interchallenge.data.provider

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Cache
import java.io.File

object NetworkProvider {

    private val context = ContextProvider.currentContext

    fun hasNetwork(): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    val cache: Cache? by lazy {
        if (context != null) {
            Cache(
                directory = File(context.cacheDir, ""),
                maxSize = 100L * 1024L * 1024L // 100 MiB
            )
        } else {
            null
        }
    }
}