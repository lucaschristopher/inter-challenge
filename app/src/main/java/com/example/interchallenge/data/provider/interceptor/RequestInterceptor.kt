package com.example.interchallenge.data.provider.interceptor

import com.example.interchallenge.core.constants.DEFAULT_TIME_VALUE
import com.example.interchallenge.core.constants.FIVE_VALUE
import com.example.interchallenge.data.provider.NetworkProvider
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        /*
        *  Leveraging the advantage of using Kotlin,
        *  we initialize the request and change its header depending on whether
        *  the device is connected to Internet or not.
        */
        request = if (NetworkProvider.hasNetwork() == true)
        /*
        *  If there is Internet, get the cache that was stored 5 seconds ago.
        *  If the cache is older than 5 seconds, then discard it,
        *  and indicate an error in fetching the response.
        *  The 'max-age' attribute is responsible for this behavior.
        */
            request.newBuilder().header("cache-control", "public, max-age=$FIVE_VALUE").build()
        else
        /*
        *  If there is no Internet, get the cache that was stored 1 hour ago.
        *  If the cache is older than 1 hour, then discard it,
        *  and indicate an error in fetching the response.
        *  The 'max-stale' attribute is responsible for this behavior.
        *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
        */
            request.newBuilder().header(
                "cache-control",
                "public, only-if-cached, max-stale=" + DEFAULT_TIME_VALUE * DEFAULT_TIME_VALUE
            ).build()

        return chain.proceed(request)
    }
}