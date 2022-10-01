package com.example.interchallenge.core.util.error

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class Fail : Exception() {
    object NoConnectionError : Fail()
    object ServerError : Fail()
}

fun <T> Flow<T>.mapToCustomError(): Flow<T> = catch {
    throw it.getGenericOrDefaultThrowable()
}

private fun Throwable.getGenericOrDefaultThrowable() = when (this) {
    is SocketTimeoutException, is UnknownHostException -> Fail.NoConnectionError
    is HttpException -> Fail.ServerError
    else -> this
}
