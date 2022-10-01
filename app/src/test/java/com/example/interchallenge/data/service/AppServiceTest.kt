package com.example.interchallenge.data.service

import com.example.interchallenge.core.constants.ALL_FLAG
import com.example.interchallenge.core.constants.EMPTY_STRING
import com.example.interchallenge.core.constants.INITIAL_PAGE
import com.example.interchallenge.data.model.SearchResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

private const val pull_request_response = "get_pull_requests_success_response.json"
private const val repo_response = "get_repo_success_response.json"

class AppServiceTest : ServiceBaseTests() {

    @Test
    fun `should get repos when server gives success response`() = runBlocking {
        // Given
        val expectedResponse = getExpectedResponse(
            repo_response,
            SearchResponse::class.java
        )
        getResponse(repo_response, HttpURLConnection.HTTP_OK)

        // When
        val result =
            service.getJavaRepositories(EMPTY_STRING, EMPTY_STRING, INITIAL_PAGE, INITIAL_PAGE)

        // Then
        Assert.assertEquals(expectedResponse.items.size, result.items.size)
    }

    @Test
    fun `should get pull requests when server gives success response`() = runBlocking {
        // Given
        val expectedResponse = getExpectedResponse(pull_request_response, List::class.java)
        getResponse(pull_request_response, HttpURLConnection.HTTP_OK)

        // When
        val result = service.getPullRequestsFromRepo("owner", "repo", ALL_FLAG)

        // Then
        Assert.assertEquals(expectedResponse.size, result.size)
    }

    @Test
    fun `should throw client exception when server sends 4xx response`(): Unit = runBlocking {
        Assert.assertThrows(HttpException::class.java) {
            runBlocking {
                getResponse(
                    repo_response,
                    HttpURLConnection.HTTP_BAD_REQUEST
                )
                service.getJavaRepositories(EMPTY_STRING, EMPTY_STRING, INITIAL_PAGE, INITIAL_PAGE)
            }
        }
    }

    @Test
    fun `should throw no network exception in case of timeout`() {
        Assert.assertThrows(SocketTimeoutException::class.java) {
            runBlocking {
                getTimeout()
                service.getJavaRepositories(EMPTY_STRING, EMPTY_STRING, INITIAL_PAGE, INITIAL_PAGE)
            }
        }
    }
}