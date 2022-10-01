package com.example.interchallenge.data.service

import com.example.interchallenge.data.provider.ApiFactory
import com.example.interchallenge.data.provider.RetrofitFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit

const val TIMEOUT = 10L

abstract class ServiceBaseTests {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit
    protected lateinit var service: AppService

    @Before
    fun setup() {
        this.configureServer()
        this.configureRetrofit()
        this.configureApiService()
    }

    private fun configureApiService() {
        service = this.createServiceFactory()
    }

    private fun configureServer() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
    }

    private fun configureRetrofit() {
        retrofit = createRetrofit()
    }

    private fun createRetrofit() = RetrofitFactory.build(
        url = mockWebServer.url("/").toString(),
        client = getOkHttpClient(),
        factory = createAdapterFactory(),
    )

    private fun createAdapterFactory() = GsonConverterFactory.create(this.getGson())

    private fun createServiceFactory() = ApiFactory.build(
        retrofit = retrofit,
        AppService::class.java
    )

    private fun getGson() = GsonBuilder().create()

    fun <T> getExpectedResponse(path: String, response: Class<T>): T {
        val gson = Gson()
        return gson.fromJson(getJson(path), response)
    }

    fun getResponse(path: String, responseCode: Int) {
        mockWebServer.enqueue(MockResponse().setBody(getJson(path)).setResponseCode(responseCode))
    }

    fun getTimeout() {
        val mockResponse = MockResponse()
        mockResponse.socketPolicy = SocketPolicy.NO_RESPONSE
        mockWebServer.enqueue(mockResponse)
    }

    private fun getJson(path: String): String {
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(path))
        val content = reader.readText()
        reader.close()
        return content
    }

    private fun getOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.readTimeout(TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(TIMEOUT, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addNetworkInterceptor(loggingInterceptor)

        return okHttpClient.build()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}

