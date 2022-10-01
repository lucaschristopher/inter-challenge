package com.example.interchallenge.domain.usecase

import app.cash.turbine.test
import com.example.interchallenge.core.constants.EMPTY_STRING
import com.example.interchallenge.data.repository.AppRepositoryImpl
import com.example.interchallenge.data.util.pullRequestMock
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetPullRequestsUseCaseTest {

    @MockK
    private val repository: AppRepositoryImpl = mockk(relaxed = true)
    private val getPullRequestsUseCase = GetPullRequestsUseCase(repository)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should get pull requests from repository`() = runTest {
        every {
            runBlocking {
                repository.getPullRequests(owner = EMPTY_STRING, repo = EMPTY_STRING)
            }
        } answers {
            flow { listOf(pullRequestMock) }
        }

        getPullRequestsUseCase.invoke(owner = EMPTY_STRING, repo = EMPTY_STRING).test {
            assertEquals(listOf(pullRequestMock).first(), pullRequestMock)
            awaitComplete()
        }
    }
}