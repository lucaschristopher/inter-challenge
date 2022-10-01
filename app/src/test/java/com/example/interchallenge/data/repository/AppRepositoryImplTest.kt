package com.example.interchallenge.data.repository

import androidx.paging.PagingData
import app.cash.turbine.test
import com.example.interchallenge.core.constants.EMPTY_STRING
import com.example.interchallenge.core.constants.TEST_ERROR_MESSAGE
import com.example.interchallenge.core.util.error.mapToCustomError
import com.example.interchallenge.data.datasource.remote.RemoteDataSource
import com.example.interchallenge.data.model.PullRequestResponse
import com.example.interchallenge.data.util.pullRequestResponseMock
import com.example.interchallenge.data.util.repositoryMock
import com.example.interchallenge.domain.model.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

@OptIn(ExperimentalCoroutinesApi::class)
class AppRepositoryImplTest {

    @MockK
    private val remoteDataSource: RemoteDataSource = mockk(relaxed = true)

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    private fun setupRepository() = AppRepositoryImpl(
        remoteDataSource,
        testDispatcher
    )

    @Test
    fun `getJavaRepositories should return a flow repo list when remoteDataSource getJavaRepositories return a flow repo list`() =
        runTest {
            // Given
            val pagingDataFlow = flowOf(PagingData.from(listOf(repositoryMock)))
            val repository = setupRepository()

            // When
            coEvery {
                remoteDataSource.getJavaRepositories()
            } returns pagingDataFlow
            val result = repository.getJavaRepositories()

            // Then
            assertSame(expected = pagingDataFlow.first(), actual = result.first())
        }

    @Test
    fun `getJavaRepositories should return a flow empty list when remoteDataSource getJavaRepositories return a flow empty list`() =
        runTest {
            // Given
            val pagingDataFlow = flowOf(PagingData.from(emptyList<Repository>()))
            val repository = setupRepository()

            // When
            coEvery {
                remoteDataSource.getJavaRepositories()
            } returns pagingDataFlow
            val result = repository.getJavaRepositories()

            // Then
            assertSame(expected = pagingDataFlow.first(), actual = result.first())
        }

    @Test
    fun `getPullRequests should return a flow of pull request list when remoteDataSource getPullRequests returns it`() =
        runTest {
            // Given
            val dataFlow = flowOf(listOf(pullRequestResponseMock))
            val repository = setupRepository()

            // When
            coEvery {
                remoteDataSource.getPullRequets(EMPTY_STRING, EMPTY_STRING)
            } returns dataFlow

            val result = repository.getPullRequests(EMPTY_STRING, EMPTY_STRING)

            // Then
            assertEquals(expected = dataFlow.first().size, actual = result.first().size)
        }

    @Test
    fun `getPullRequests should return a error when remoteDataSource getPullRequests return error`() =
        runTest {
            // Given
            val dataFlow = flow<List<PullRequestResponse>> {
                throw RuntimeException(TEST_ERROR_MESSAGE)
            }.mapToCustomError()

            val repository = setupRepository()

            // When
            coEvery {
                remoteDataSource.getPullRequets(EMPTY_STRING, EMPTY_STRING)
            } returns dataFlow

            // Then
            repository.getPullRequests(EMPTY_STRING, EMPTY_STRING).test {
                assertEquals(
                    TEST_ERROR_MESSAGE,
                    awaitError().message
                )
            }
        }
}