package com.example.interchallenge.data.provider

import com.example.interchallenge.core.constants.DEFAULT_TIME_VALUE
import com.example.interchallenge.data.provider.NetworkProvider.cache
import com.example.interchallenge.data.provider.interceptor.RequestInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpClientFactory {

    fun build(): OkHttpClient {
        return OkHttpClient.Builder()
            .setupTimeout()
            .setupCache()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    private fun OkHttpClient.Builder.setupTimeout() = apply {
        readTimeout(DEFAULT_TIME_VALUE, TimeUnit.SECONDS)
        writeTimeout(DEFAULT_TIME_VALUE, TimeUnit.SECONDS)
        connectTimeout(DEFAULT_TIME_VALUE, TimeUnit.SECONDS)
    }

    private fun OkHttpClient.Builder.setupCache() = apply {
        cache(cache)
    }
}
