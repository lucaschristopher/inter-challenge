package com.example.interchallenge.data.provider.interceptor

import com.example.interchallenge.core.constants.DEFAULT_TIME_VALUE
import com.example.interchallenge.data.provider.NetworkProvider
import okhttp3.Interceptor
import okhttp3.Response

class OfflineInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (NetworkProvider.hasNetwork() == false) {
            val maxStale =
                DEFAULT_TIME_VALUE * DEFAULT_TIME_VALUE // Offline cache available for 1 hour

            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }

        return chain.proceed(request)
    }
}

class OnlineInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val maxAge = DEFAULT_TIME_VALUE

        return response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }
}
