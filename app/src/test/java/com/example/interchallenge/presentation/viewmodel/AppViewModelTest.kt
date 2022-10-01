package com.example.interchallenge.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import app.cash.turbine.test
import com.example.interchallenge.data.repository.AppRepository
import com.example.interchallenge.data.util.pullRequestMock
import com.example.interchallenge.data.util.repositoryMock
import com.example.interchallenge.domain.model.PullRequest
import com.example.interchallenge.domain.model.Repository
import com.example.interchallenge.domain.usecase.AppUseCase
import com.example.interchallenge.domain.usecase.GetJavaRepositoriesUseCase
import com.example.interchallenge.domain.usecase.GetPullRequestsUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*

@OptIn(ExperimentalCoroutinesApi::class)
class AppViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private val repository = mockk<AppRepository>()

    private val getJavaRepositoriesUseCase = GetJavaRepositoriesUseCase(repository)
    private val getPullRequestsUseCase = GetPullRequestsUseCase(repository)
    private val useCase = AppUseCase(getJavaRepositoriesUseCase, getPullRequestsUseCase)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `repository should retuns not null content when viewModel getJavaRepositories is called`() =
        runTest(testDispatcher) {
            val viewModel = AppViewModel(useCase)
            val dataFlow = flowOf(PagingData.from(listOf(repositoryMock)))

            every {
                runBlocking {
                    repository.getJavaRepositories()
                }
            } answers { dataFlow }

            viewModel.getJavaRepositories().test {
                Assert.assertNotNull(awaitItem())
            }
        }
}

class FakeRepo : AppRepository {
    override fun getJavaRepositories(): Flow<PagingData<Repository>> = flow {
        emit(PagingData.from(listOf(repositoryMock)))
    }

    override suspend fun getPullRequests(owner: String, repo: String): Flow<List<PullRequest>> =
        flow {
            emit(listOf(pullRequestMock))
        }

}